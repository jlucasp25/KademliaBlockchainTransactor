package pt.groupG.core;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import pt.groupG.grpc.ClientServiceGrpc;
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

    public boolean sendPING() {
        pt.groupG.grpc.EmptyMessage req = pt.groupG.grpc.EmptyMessage.newBuilder().build();
        return true;
    }


}
