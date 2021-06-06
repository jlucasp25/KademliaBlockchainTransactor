package pt.groupG.core;


import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import pt.groupG.grpc.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class KademliaClient {
    private String host;
    private int port;
    private ServerServiceGrpc.ServerServiceBlockingStub stub;
    private ManagedChannel channel;
    public Node self;
    public RoutingTable routingTable;

    KademliaClient(ManagedChannel channel) {
        this.channel = channel;
        stub = ServerServiceGrpc.newBlockingStub(channel);
    }

    public KademliaClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void setRoutingTable(RoutingTable routingTable) {
        this.routingTable = routingTable;
    }

    public void setNodefromJOIN(String key, String address, int port) {
        KademliaKey kKey = new KademliaKey(key);
        this.self = new Node(kKey, address, port);
        this.routingTable.setSelfNode(this.self);
    }





}
