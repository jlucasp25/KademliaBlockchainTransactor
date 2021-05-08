package pt.groupG.core;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
//import pt.groupG.grpc.ServerServiceImpl;

import java.io.IOException;

public class KademliaServer {
    private Server server;
    private final int SERVER_PORT = 8000;
    public static int alpha = 3; // parallelism in network calls

/*    public KademliaServer() throws IOException {
       // this.init();
    }

    public void init() throws IOException {
        this.server = ServerBuilder
                .forPort(SERVER_PORT)
                .addService(new ServerServiceImpl())
                .build()
                .start();

    }

    public void findNode() {

    }*/

}

//
//class ServerServiceImpl extends ServerServiceGrpc.ServerServiceImplBase {
//// Server Implementation needs handlers for client RPC's
//// implements ClientGRPC
//
//
//public void pingImpl(EmptyMessage req,StreamObserver<BooleanMessage> res){
//        res.onNext(BooleanMessage.newBuilder().setValue(true).build());
//        res.onCompleted();
//        }
//
//public void findNodeImpl(NodeDetailsMessage req,StreamObserver<NodeDetailsListMessage> res){
//        res.onNext(*/
///*FIND NODE ROUTINES*//*
//);
//        res.onCompleted();
//        }
//        }
//
//

