package pt.groupG.core;

import Utils.HashCash;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import pt.groupG.grpc.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/* BOOTSTRAP NODE -> RESPONSE HANDLERS */

/**
 * Works as the response handler for all the Bootstrap RPC channels.
 */
public class KademliaBootstrapRPC {
    private Server server;
    private int serverPort = 0;
    private String serverHost = "";

    public KademliaBootstrapRPC(String host, int port) {
        this.serverHost = host;
        this.serverPort = port;
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
        }
        catch (InterruptedException e) {
            System.out.println("[KademliaBootstrapRPC] Execution Interrupted!");
        }
    }

    /**
     * Inner class that includes Handlers for the Server communication Services.
     */
    class ServerServiceImpl extends ServerServiceGrpc.ServerServiceImplBase {

        public void ping(EmptyMessage req, StreamObserver<BooleanMessage> res) {
            System.out.println("[ServerService] Received PING");
            res.onNext(BooleanMessage.newBuilder().setValue(true).build());
            res.onCompleted();
        }

        public void join(JoinMessage req, StreamObserver<NodeIdMessage> res) {
            System.out.println("[ServerService] Received JOIN");

            /*
            validate received initial work
             */
            NodeIdMessage.Builder msgBuilder = NodeIdMessage.newBuilder();
            try {
                String initialWork = req.getInitialWork();
                new HashCash(initialWork);
            } catch (Exception ignored) {
                System.out.println("Captured invalid initial work.");
                res.onNext(msgBuilder.build());
                res.onCompleted();
                return;
            }

            System.out.println("[ServerService] Generating key for joined node.");
            KademliaKey key = new KademliaKey();
            NodeIdMessage msg = msgBuilder.setNodeid(key.toString()).setBootstrapnodeid(self.nodeID.toString()).build();
            routingTable.addNode(new Node(key, req.getAddress(), req.getPort()));
            System.out.println(routingTable);
            res.onNext(msg);
            res.onCompleted();
        }

        public void findNode(NodeIdMessage req, StreamObserver<NodeDetailsListMessage> res) {
            System.out.println("[ServerService] Received FIND_NODE");
            System.out.println("[ServerService] Searching for closests nodes to " + req.getNodeid());
            List<NodeDetailsMessage> nodes = new LinkedList<>();
            List<Contact> closestNodes = routingTable.getClosestNodes(req.getNodeid(), req.getBootstrapnodeid());
            for (Contact aux : closestNodes) {
                nodes.add(NodeDetailsMessage.newBuilder().setNodeid(aux.nodeID.toString()).setAddress(aux.getAddress()).setPort(aux.getPort()).build());
            }
            NodeDetailsListMessage msg = NodeDetailsListMessage.newBuilder().addAllNodes(nodes).build();
            res.onNext(msg);
            res.onCompleted();
        }
    }

}
