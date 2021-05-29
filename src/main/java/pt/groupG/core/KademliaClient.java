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

    KademliaClient(ManagedChannel channel) {
        this.channel = channel;
        stub = ServerServiceGrpc.newBlockingStub(channel);
    }

    public KademliaClient(String host, int port) {
        this.host = host;
        this.port = port;
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

    public JoinMessage JOIN(EmptyMessage req) {
        System.out.println("CLIENT: Sent JOIN");
        JoinMessage res = null;
        res = this.stub.join(req);
        return res;
    }

    public NodeDetailsListMessage FIND_NODE(NodeIdMessage req) {
        //List<Node> res = new LinkedList<Node>();
        NodeDetailsListMessage res = null;
        res = stub.findNode(req);
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
