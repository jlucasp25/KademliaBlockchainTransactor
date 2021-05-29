package pt.groupG.core;

import pt.groupG.grpc.NodeDetailsMessage;

import java.nio.charset.StandardCharsets;

public class Node {
    /**
     * Node
     * Stores the network information, ID and the Routing Table information of a node.
     * The routing table contains the list of contacts.
     */
    public KademliaKey nodeID;
    private String address;
    private int port;
    public RoutingTable routingTable;
    // public KBucket bucket;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Node(KademliaKey nodeID, String address, int port) {
        this.nodeID = nodeID;
        this.address = address;
        this.port = port;
    }

    public void findNode(Node target) {

    }



    /**
     * Factory Constructor for Node using a NodeDetailsmessage object.
     * @param msg
     */
    public static Node fromNodeDetailsMessage(NodeDetailsMessage msg) {
        /*CASTS STRING TO BYTES[]; Maybe protobuf needs to be changed to bytes.*/
        KademliaKey kdk = new KademliaKey(msg.getNodeid().getBytes(StandardCharsets.UTF_8));
        return new Node(kdk, msg.getAddress(), msg.getPort());
    }

    /**
     * Converts a Node into a Protobuf message.
     */
  /*  public static NodeDetailsMessage toMessageFormat(Node node) {
        return NodeDetailsMessage
                .newBuilder()
                .setAddress(node.address)
                .setPort(node.port)
                .setNodeid(node.nodeID)
                .build();
    }
*/
    /**
     * Converts a Node Details Protobuf message into a Node.
     */
/*
    public static Node fromMessageFormat(NodeDetailsMessage msg) {
        return new Node(msg.getNodeid(),msg.getAddress(),msg.getPort());
    }
*/

    /**
     * Converts a Node Details List Protobuf message into a List of Nodes.
     */
/*
    public static LinkedList<Node> fromMessageListFormat(NodeDetailsListMessage msg) {
        LinkedList<Node> l = new LinkedList<Node>();
        for (NodeDetailsMessage ndMsg : msg.getNodesList()) {
            l.add(Node.fromMessageFormat(ndMsg));
        }
        return l;
    }
*/

    /**
     * Converts a list of Nodes into a Node Details List Protobuf message.
     */
//    public static NodeDetailsListMessage toMessageListFormat(LinkedList<Node> nodes) {
//        LinkedList<NodeDetailsMessage> l = new LinkedList<NodeDetailsMessage>();
//        for (Node aux : nodes) {
//            l.add(Node.toMessageFormat(aux));
//        }
//        return NodeDetailsListMessage.newBuilder().addAllNodes(l).build();
//    }


    @Override
    public String toString() {
        return "Node" + nodeID + "; " + address + "; " + port;
    }
}
