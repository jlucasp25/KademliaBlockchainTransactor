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

    public void initializeConnection() {
        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        this.stub = ServerServiceGrpc.newBlockingStub(channel);
    }

    public void terminateConnection() throws InterruptedException {
        channel.shutdown().awaitTermination(2, TimeUnit.SECONDS);
    }

    public boolean PING(EmptyMessage req) {
        System.out.println("CLIENT: Sent PING");
        BooleanMessage res = null;
        res = this.stub.ping(req);
        return res.getValue();
    }

    public NodeIdMessage JOIN(JoinMessage req) {
        System.out.println("CLIENT: Sent JOIN");
        NodeIdMessage res = null;
        res = this.stub.join(req);
        return res;
    }

    public NodeDetailsListMessage FIND_NODE(NodeIdMessage req) {
        System.out.println("CLIENT: Sent FIND_NODE");
        NodeDetailsListMessage res = null;
        res = this.stub.findNode(req);
        return res;
        // convert details list into list of nodes.
    }

    public BooleanMessage STORE(NodeIdMessage req) {
        BooleanMessage res = null;
       // res = stub.store(req);
        /*placeholder*/
        return res;
    }

    public List<Node> FIND_VALUE(NodeIdMessage req) {
        NodeDetailsListMessage res = null;
        res = stub.findValue(req);
        /*placeholder*/
        return new LinkedList<Node>();
    }

}
