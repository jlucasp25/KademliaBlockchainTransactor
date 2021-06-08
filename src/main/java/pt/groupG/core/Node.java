package pt.groupG.core;

import pt.groupG.grpc.NodeDetailsMessage;

public class Node {
    /**
     * Node
     * Stores the network information, ID and the Routing Table information of a node.
     * The routing table contains the list of contacts.
     */
    public KademliaKey nodeID;
    private String address;
    private int port;
    private int wallet = 0;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setWallet(int wallet) { this.wallet = wallet; }

    public int getWallet() { return wallet; }

    /**
     * Calls calculate distance to determine position on the kademlia tree.
     */
    public int calculateDistance(Node aux) {
        return this.nodeID.calculateDistance(aux.nodeID);
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Node(KademliaKey nodeID, String address, int port) {
        this.nodeID = nodeID;
        this.address = address;
        this.port = port;
    }

    public static Node fromContact(Contact aux) {
        return new Node(aux.nodeID, aux.getAddress(), aux.getPort());
    }


    /**
     * Factory Constructor for Node using a NodeDetailsmessage object.
     */
    public static Node fromNodeDetailsMessage(NodeDetailsMessage msg) {
        KademliaKey kdk = new KademliaKey(msg.getNodeidBytes());
        return new Node(kdk, msg.getAddress(), msg.getPort());
    }

    @Override
    public String toString() {
        return "Node" + nodeID + "; " + address + "; " + port;
    }
}
