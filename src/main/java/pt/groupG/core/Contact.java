package pt.groupG.core;

public class Contact extends Node {
    /**
     * Contact
     * Proxy class to represent contact information using Node base class.
     */
    public Contact(KademliaKey nodeID, String address, int port) {
        super(nodeID, address, port);
        this.routingTable = null;
    }

    /**
     * Factory Constructor for Contacts using Node.
     */
    public static Contact fromNode(Node nd) {
        return new Contact(nd.nodeID, nd.getAddress(), nd.getPort());
    }

    @Override
    public boolean equals(Object aux) {
        if (aux == this)
            return true;

        if (!(aux instanceof Contact)) {
            return false;
        }

        Contact cAux = (Contact) aux;

        // if both ids are equal, its the same node.
        return this.nodeID.equals(cAux.nodeID);
    }
}
