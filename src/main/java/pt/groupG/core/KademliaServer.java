package pt.groupG.core;

import Utils.HashCash;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import pt.groupG.grpc.*;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class KademliaServer {
    private Server server;
    private final int SERVER_PORT = 8000;
    public static int alpha = 3; // parallelism in network calls
    public Node self;
    public RoutingTable routingTable;

    public void setRoutingTable(RoutingTable routingTable) {
        this.routingTable = routingTable;
    }


}




