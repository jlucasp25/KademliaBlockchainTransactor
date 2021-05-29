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
        // generate an id to the node that requested it. -> DONE
        // add that node to its own list. -> NEEDS TO BE DONE
        KademliaKey key = new KademliaKey();
        NodeIdMessage msg = NodeIdMessage.newBuilder().setNodeid(key.toString()).build();
        res.onNext(msg);
        res.onCompleted();
    }

}


