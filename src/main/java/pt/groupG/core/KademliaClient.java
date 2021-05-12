package pt.groupG.core;


import io.grpc.Channel;
import pt.groupG.grpc.*;

import java.util.LinkedList;
import java.util.List;

public class KademliaClient {
    private final ClientServiceGrpc.ClientServiceBlockingStub stub;
    private Channel channel;

    KademliaClient(Channel channel) {
        this.channel = channel;
        stub = ClientServiceGrpc.newBlockingStub(channel);
    }

    public BooleanMessage PING(EmptyMessage req) {
        BooleanMessage res = null;
        res = this.stub.ping(req);
        return null;
    }

    public List<Node> FIND_NODE(NodeIdMessage req) {
        //List<Node> res = new LinkedList<Node>();
        NodeDetailsListMessage res = null;
        res = stub.findNode(req);
        // convert details list into list of nodes.
    }

    public BooleanMessage STORE(NodeIdMessage req) {
        BooleanMessage res = null;
        res = stub.store(req);
    }

    public List<Node> FIND_VALUE(NodeIdMessage req) {
        NodeDetailsListMessage res = null;
        res = stub.findValue(req);
    }

}