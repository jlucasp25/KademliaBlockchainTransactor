package pt.groupG.core;

import Utils.HashCash;
import com.google.protobuf.ByteString;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import pt.groupG.core.blockchain.Block;
import pt.groupG.core.blockchain.Blockchain;
import pt.groupG.grpc.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/* BOOTSTRAP NODE -> RESPONSE HANDLERS */

/**
 * Works as the response handler for all the Bootstrap RPC channels.
 */
public class KademliaBootstrapChannelRPC extends Thread {
    private Server server;
    private int serverPort = 0;
    private String serverHost = "";
    // References to Core
    RoutingTable selfTable = null;
    Node selfNode = null;

    public KademliaBootstrapChannelRPC(String host, int port, RoutingTable table, Node nd) {
        this.serverHost = host;
        this.serverPort = port;
        this.selfTable = table;
        this.selfNode = nd;
    }

    public void run() {
        this.initializeConnection();
        this.openConnectionChannel();
    }

    /**
     * Initializes the server connection.
     */
    public void initializeConnection() {
        try {
            this.server = ServerBuilder
                    .forPort(this.serverPort)
                    .addService(new ServerServiceImpl())
                    .build()
                    .start();
        } catch (IOException e) {
            System.out.println("[KademliaBootstrapRPC] IOException when initializing server!");
        }
    }


    /**
     * Mantains the initialized connection opened until server is null.
     */
    public void openConnectionChannel() {
        try {
            if (this.server != null) {
                System.out.println("[KademliaBootstrapRPC] Open for connections!");
                server.awaitTermination();
            }
        } catch (InterruptedException e) {
            System.out.println("[KademliaBootstrapRPC] Execution Interrupted!");
        }
    }

    /**
     * Inner class that includes Handlers for the Server communication Services.
     */
    class ServerServiceImpl extends ServerServiceGrpc.ServerServiceImplBase {

        /**
         * PING RPC Response Handler
         */
        public void ping(EmptyMessage req, StreamObserver<BooleanMessage> res) {
            System.out.println("[BootstrapService] Received PING");
            res.onNext(BooleanMessage.newBuilder().setValue(true).build());
            res.onCompleted();
        }

        /**
         * JOIN RPC Response Handler
         */
        public void join(JoinMessage req, StreamObserver<NodeIdMessage> res) {
            System.out.println("[BootstrapService] Received JOIN");
            HashCash initialWorkHC = null;

            // validate initial work.
            try {
                String initialWork = req.getInitialWork();
                initialWorkHC = new HashCash(initialWork);
            } catch (Exception e) {
                System.out.println("[BootstrapService] Generated Invalid Initial Work! (Sending empty message)");
                res.onNext(NodeIdMessage.newBuilder().build());
                res.onCompleted();
                return;
            }

            System.out.println("[BootstrapService] Generating key for joined node.");
            KademliaKey key = new KademliaKey();
            NodeIdMessage.Builder builder = NodeIdMessage.newBuilder()
                    .setNodeidBytes(ByteString.copyFrom(key.byteKey))
                    .setBootstrapnodeidBytes(ByteString.copyFrom(selfNode.nodeID.byteKey));

            List<BlockData> blocks = new LinkedList<>();
            for(Block aux : Blockchain.blocks) {
                BlockData block = BlockData.newBuilder().addAllT(aux.trans).build();
                blocks.add(block);
            }

            pt.groupG.grpc.Blockchain bc = pt.groupG.grpc.Blockchain.newBuilder()
                    .addAllBlock(blocks).build();

            builder.setBlockchain(bc);
            NodeIdMessage msg = builder.build();

            // add created node to routing table.
            selfTable.addNode(new Node(key, req.getAddress(), req.getPort()));

            res.onNext(msg);
            res.onCompleted();
        }
        /**
         * FIND-NODE RPC Response Handler
         */
        public void findNode(NodeDetailsMessage req, StreamObserver<NodeDetailsListMessage> res) {
            System.out.println("[BootstrapService] Received FIND_NODE");

            System.out.println("[BootstrapService] Added request origin node to routing table!");
            selfTable.addNode(new Node(new KademliaKey(req.getNodeidBytes()),req.getAddress(), req.getPort()));

            System.out.println("[BootstrapService] Searching for closest nodes to " + new KademliaKey(req.getNodeidBytes()).toHexaString());

            List<NodeDetailsMessage> nodes = new LinkedList<>();

            List<Contact> closestNodes = selfTable.getClosestNodes(req.getNodeidBytes(), req.getBootstrapnodeidBytes());

            System.out.println(closestNodes.size());

            for (Contact aux : closestNodes) {
                nodes.add(NodeDetailsMessage.newBuilder().setNodeidBytes(ByteString.copyFrom(aux.nodeID.byteKey)).setAddress(aux.getAddress()).setPort(aux.getPort()).build());
            }

            NodeDetailsListMessage msg = NodeDetailsListMessage.newBuilder().addAllNodes(nodes).build();
            res.onNext(msg);
            res.onCompleted();
        }

        public void pay(MoneyMessage req, StreamObserver<EmptyMessage> res) {
            System.out.println("[BootstrapService] Received PAY");
            int amount = req.getValue();
            selfNode.setWallet(selfNode.getWallet()+amount);
            res.onNext(EmptyMessage.newBuilder().build());
            res.onCompleted();
        }

        public void store(StoreMessage req, StreamObserver<EmptyMessage> res) {
            System.out.println("[BootstrapService] Received STORE");
            Block block = new Block(req.getTransactionList());
            Core.blockchain.newBlock(block);
            System.out.println("[BootstrapService] Blockchain is now with " + Blockchain.blocks.size() + " blocks.");
            res.onNext(EmptyMessage.newBuilder().build());
            res.onCompleted();
        }
    }

}
