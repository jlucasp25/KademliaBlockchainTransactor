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
public class KademliaBootstrapRPC {
    private Server server;
    private final int SERVER_PORT = 8000;

    public void initializeConnection() throws IOException {
        this.server = ServerBuilder
                .forPort(SERVER_PORT)
                .addService(new ServerServiceImpl())
                .build()
                .start();
    }



    /**
     * Mantains the initialized connection opened until server is null.
     * @throws InterruptedException
     */
    public void openConnectionChannel() throws InterruptedException {
        if (this.server != null) {
            System.out.println("Bootstrap Node - Open for connections!");
            server.awaitTermination();
        }
    }

    /**
     * Defines RPC methods that send responses.
     */
    class ServerServiceImpl extends ServerServiceGrpc.ServerServiceImplBase {

        public void ping(EmptyMessage req, StreamObserver<BooleanMessage> res) {
            System.out.println("SERVER: Received PING");
            res.onNext(BooleanMessage.newBuilder().setValue(true).build());
            res.onCompleted();
        }

        public void join(JoinMessage req, StreamObserver<NodeIdMessage> res) {
            System.out.println("SERVER: Received JOIN");

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

            System.out.println("SERVER: Generating key for joined node.");
            KademliaKey key = new KademliaKey();
            NodeIdMessage msg = msgBuilder.setNodeid(key.toString()).setBootstrapnodeid(self.nodeID.toString()).build();
            routingTable.addNode(new Node(key, req.getAddress(), req.getPort()));
            System.out.println(routingTable);
            res.onNext(msg);
            res.onCompleted();
        }

        public void findNode(NodeIdMessage req, StreamObserver<NodeDetailsListMessage> res) {
            System.out.println("SERVER: Received FIND_NODE");
            System.out.println("SERVER: Searching for closests nodes to " + req.getNodeid());
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
