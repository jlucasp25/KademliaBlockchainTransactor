package pt.groupG.core;
import com.google.api.ClientProto;
import io.grpc.Server;
import io.grpc.ServerBuilder;
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
                .addService(new ServerServiceImpl())
                .build();

    }

    class ServerServiceImpl implements ClientProtoOuterClass {
        // Server Implementation needs handlers for client RPC's
        // implements ClientGRPC

        @Override
        public void join()
    }
}


