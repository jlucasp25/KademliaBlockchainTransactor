package pt.groupG.core;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import pt.groupG.grpc.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*REPRESENTS A CLIENT CONNECTION - Volatile object just to make connections :) */
public class KademliaClientRPC {
    private String host;
    private int port;
    private ServerServiceGrpc.ServerServiceBlockingStub stub; // needs to be splitted into 2 protobufs.
    private ManagedChannel channel;

    KademliaClientRPC(ManagedChannel channel) {
        this.channel = channel;
        stub = ServerServiceGrpc.newBlockingStub(channel);
    }

    KademliaClientRPC(String host, int port) {
        this.host = host;
        this.port = port;
        this.initializeConnection();
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

    public BooleanMessage PING(EmptyMessage req) {
        System.out.println("[Client RPC]: Sent PING");
        BooleanMessage res = null;
        res = this.stub.ping(req);
        return res;
    }

    public NodeIdMessage JOIN(JoinMessage req) {
        System.out.println("[Client RPC]: Sent JOIN");
        NodeIdMessage res = null;
        res = this.stub.join(req);
        return res;
    }

    public NodeDetailsListMessage FIND_NODE(NodeDetailsMessage req) {
        System.out.println("[Client RPC]: Sent FIND_NODE");
        NodeDetailsListMessage res = null;
        res = this.stub.findNode(req);
        return res;
    }

    public BooleanMessage STORE(NodeIdMessage req) {
        BooleanMessage res = null;
        // res = stub.store(req);
        /*placeholder*/
        return res;
    }

    public List<Node> FIND_VALUE(NodeDetailsMessage req) {
        NodeDetailsListMessage res = null;
        res = stub.findValue(req);
        /*placeholder*/
        return new LinkedList<Node>();
    }

}
