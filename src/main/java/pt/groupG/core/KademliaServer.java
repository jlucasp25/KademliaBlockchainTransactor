package pt.groupG.core;
import com.google.api.ClientProto;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import pt.groupG.communication.BooleanMessage;
import pt.groupG.communication.EmptyMessage;
import pt.groupG.communication.NodeDetailsListMessage;
import pt.groupG.communication.NodeDetailsMessage;

import java.io.IOException;

public class KademliaServer {
    private Server server;
    private final int SERVER_PORT = 8000;

    public KademliaServer() throws IOException {
        this.init();
    }

    public void init() throws IOException {
        this.server = ServerBuilder
                .forPort(SERVER_PORT)
                .addService(new ServerServiceImpl()))
                .build()
                .start();

    }

    class ServerServiceImpl implements ClientProtoOuterClass {
        // Server Implementation needs handlers for client RPC's
        // implements ClientGRPC


        @Override
        public void pingImpl(EmptyMessage req, StreamObserver<BooleanMessage> res) {
            res.onNext(BooleanMessage.newBuilder().setValue(true).build());
            res.onCompleted();
        }

        public void findNodeImpl(NodeDetailsMessage req, StreamObserver<NodeDetailsListMessage> res) {
            res.onNext(/*FIND DONE ROUTINES*/);
            res.onCompleted();
        }
    }
}


