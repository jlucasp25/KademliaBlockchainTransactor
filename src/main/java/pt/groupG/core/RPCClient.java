package pt.groupG.core;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import pt.groupG.grpc.BooleanMessage;
import pt.groupG.grpc.ClientServiceGrpc;
import pt.groupG.grpc.EmptyMessage;

/**
 * Defines RPC methods that send requests
 */
public class RPCClient {

    private ManagedChannel channel;
    // stub must be blockingstub variant so its synchronous.
    private ClientServiceGrpc.ClientServiceBlockingStub stub;

    public RPCClient(String host, int port) {
        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .build();
        stub = ClientServiceGrpc.newBlockingStub(channel);
    }

    public boolean pingRPC() {
        pt.groupG.grpc.EmptyMessage req = pt.groupG.grpc.EmptyMessage.newBuilder().build();
        BooleanMessage res = stub.ping(req);
        return true;
    }

}
