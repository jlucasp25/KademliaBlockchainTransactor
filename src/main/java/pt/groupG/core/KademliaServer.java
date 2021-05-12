package pt.groupG.core;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import pt.groupG.grpc.*;
//import pt.groupG.grpc.ServerServiceImpl;

import java.io.IOException;

public class KademliaServer {
    private Server server;
    private final int SERVER_PORT = 8000;
    public static int alpha = 3; // parallelism in network calls

    public KademliaServer() throws IOException {
        this.init();
    }

    public void init() throws IOException {
        this.server = ServerBuilder
                .forPort(SERVER_PORT)
                .addService(new ServerServiceImpl())
                .build()
                .start();
    }


}

/**
 * Defines RPC methods that send responses.
 */
class ServerServiceImpl extends ServerServiceGrpc.ServerServiceImplBase {
// Server Implementation needs handlers for client RPC's
// implements ClientGRPC


    public void ping(EmptyMessage req, StreamObserver<BooleanMessage> res) {
        res.onNext(BooleanMessage.newBuilder().setValue(true).build());
        res.onCompleted();
    }

    public void findNode(NodeDetailsMessage req, StreamObserver<NodeDetailsListMessage> res) {

        int closestKBucket = Core.

        NodeDetailsListMessage.Builder messageBuilder = NodeDetailsListMessage.newBuilder();


    }
}

    public void findNodeImpl() {
        res.onNext( */
        /*FIND NODE ROUTINES*//*
);
        res.onCompleted();
        }
        }



