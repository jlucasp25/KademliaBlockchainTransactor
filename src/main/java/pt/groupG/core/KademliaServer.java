package pt.groupG.core;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import pt.groupG.grpc.*;


import java.io.IOException;

public class KademliaServer {
    private Server server;
    private final int SERVER_PORT = 8000;
    public static int alpha = 3; // parallelism in network calls
    public Node self;
    public RoutingTable routingTable;

    public void setRoutingTable(RoutingTable routingTable) {
        this.routingTable = routingTable;
    }

    public void initializeConnection() throws IOException {
        this.server = ServerBuilder
                .forPort(SERVER_PORT)
                .addService(new ServerServiceImpl())
                .build()
                .start();
    }

    public void generateBootstrapNode(String address, int port) {
        KademliaKey kKey = new KademliaKey();
        this.self = new Node(kKey, address, port);
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
            System.out.println("SERVER: Generating key for joined node.");
            KademliaKey key = new KademliaKey();
            NodeIdMessage msg = NodeIdMessage.newBuilder().setNodeid(key.toString()).setBootstrapnodeid(self.nodeID.toString()).build();
            routingTable.addNode(new Node(key, req.getAddress(), req.getPort()));
            System.out.println(routingTable);
            res.onNext(msg);
            res.onCompleted();
        }

    }
}




