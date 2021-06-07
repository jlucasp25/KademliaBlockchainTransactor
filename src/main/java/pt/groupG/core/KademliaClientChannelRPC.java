package pt.groupG.core;

import com.google.protobuf.ByteString;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import pt.groupG.grpc.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
Server instance for regular nodes.
* */
public class KademliaClientChannelRPC extends Thread {
    private Server server;
    private int serverPort = 0;
    private String serverHost = "localhost";
    RoutingTable selfTable = null;
    Node selfNode = null;

    public KademliaClientChannelRPC(String host, int port, RoutingTable table, Node nd) {
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
                    .addService(new KademliaClientChannelRPC.ServerServiceImpl())
                    .build()
                    .start();
        } catch (IOException e) {
            System.out.println("[KademliaClientChannelRPC] IOException when initializing server!");
        }
    }

    /**
     * Mantains the initialized connection opened until server is null.
     */
    public void openConnectionChannel() {
        try {
            if (this.server != null) {
                System.out.println("[KademliaClientChannelRPC] Open for connections!");
                server.awaitTermination();
            }
        } catch (InterruptedException e) {
            System.out.println("[KademliaClientChannelRPC] Execution Interrupted!");
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
            System.out.println("[ServerService] Received PING");
            res.onNext(BooleanMessage.newBuilder().setValue(true).build());
            res.onCompleted();
        }

        /**
         * FIND-NODE RPC Response Handler
         */
        public void findNode(NodeDetailsMessage req, StreamObserver<NodeDetailsListMessage> res) {
            System.out.println("[ClientService] Received FIND_NODE");

            System.out.println("[ClientService] Added request origin node to routing table!");
            selfTable.addNode(new Node(new KademliaKey(req.getNodeidBytes()),req.getAddress(), req.getPort()));

            System.out.println("[ClientService] Searching for closest nodes to " + new KademliaKey(req.getNodeidBytes()).toHexaString());

            List<NodeDetailsMessage> nodes = new LinkedList<>();

            List<Contact> closestNodes = selfTable.getClosestNodes(req.getNodeidBytes(), req.getBootstrapnodeidBytes());

            for (Contact aux : closestNodes) {
                nodes.add(NodeDetailsMessage.newBuilder().setNodeidBytes(ByteString.copyFrom(aux.nodeID.byteKey)).setAddress(aux.getAddress()).setPort(aux.getPort()).build());
            }

            NodeDetailsListMessage msg = NodeDetailsListMessage.newBuilder().addAllNodes(nodes).build();
            res.onNext(msg);
            res.onCompleted();
        }

        public void store() {

        }
    }


}
