package pt.groupG.core;

import Utils.HashCash;
import pt.groupG.core.blockchain.Block;
import pt.groupG.grpc.JoinMessage;
import pt.groupG.grpc.NodeDetailsListMessage;
import pt.groupG.grpc.NodeDetailsMessage;
import pt.groupG.grpc.NodeIdMessage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static Utils.StringUtils.generateRandomString;

/**
 * Main/Core class
 * Contains the starting instructions and manages most of the application.
 * */
public class Core {
    // Network Properties
    private final static String SERVER_ADDRESS = "localhost";
    private final static int SERVER_PORT = 8000;
    private static int CLIENT_PORT = 8010;
    /*
    KademliaClientRPC must be called as independently like an HTTP request.
    KademliaBootstrapRPC must be used as a WebSocket, always open for connections.
    */
    private static KademliaBootstrapRPC serverRPC = null;

    // Kademlia Properties
    public static RoutingTable routingTable = new RoutingTable();
    public static Node selfNode;

    // Blockchain Properties
    public static List<Block> blockchain = new LinkedList<Block>();


    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Select 1 for regular node, 2 for bootstrap node");
        String s = stdin.next();
        if (s.equals("1")) {
            String initialWork;
            try {
                initialWork = eclipseAttackPrevention();
            } catch (NoSuchAlgorithmException e) {
                System.out.println("Initial Work failed. Couldn't enter the network!");
                return;
            }
            // send initialWork to bootstrap node for validation
            // only after this validation, can the node join the network
            setupNodeAsRegular(initialWork);
        }
        else if(s.equals("2")) {
            // TODO
            // o bootstrap node tem de ser sempre o mesmo, passar a estatico.
            setupNodeAsBootstrap();
        }
        else {
            System.out.println("Wrong Option! Exiting.");
            return;
        }
    }

    /**
     * Generates and sets the self node as a Bootstrap one.
     * */
    public static void generateBootstrapNode(String address, int port) {
        KademliaKey kKey = new KademliaKey();
        selfNode = new Node(kKey, address, port);
    }

    /**
     * Forces some computation/work to happen before joining the network
     * To prevent Eclipse Attack
     */
    public static String eclipseAttackPrevention() throws NoSuchAlgorithmException {
        return HashCash.mintCash(generateRandomString(), 10).toString();
    }

    /**
     * Setups the Node as a regular Peer.
     */
    public static void setupNodeAsRegular(String initialWork) {
        // TEMPORARY: ask for port to attach itself
        CLIENT_PORT = requestClientPort();

        // sends a JOIN request to the Bootstrap Node
        // to get its own key.
        System.out.println("[Regular Node #NONE] - Sending Join Request...");
        KademliaClientRPC rpc = new KademliaClientRPC(SERVER_ADDRESS, SERVER_PORT);
        JoinMessage joinReq = JoinMessage.newBuilder().setAddress(SERVER_ADDRESS).setPort(CLIENT_PORT).setInitialWork(initialWork).build();
        NodeIdMessage joinRes = rpc.JOIN(joinReq);
        String bootstrapNodeKey = joinRes.getBootstrapnodeid();
        String regularNodeKey = joinRes.getNodeid();

        // Checks if the initial work is valid.
        if (bootstrapNodeKey.equals("")) {
            System.out.println("Invalid Initial Work found when performing JOIN! Exiting.");
            return;
        }

        // adds the bootstrap node to its routing table.
        routingTable.addNode(new Node(new KademliaKey(bootstrapNodeKey), SERVER_ADDRESS, SERVER_PORT));

        // sets its own node ("self") with the ID provided by the JOIN process.
        setSelfNode(regularNodeKey, SERVER_ADDRESS, CLIENT_PORT);

        // send a FIND_NODE request to the bootstrap node
        // so that it finds the closest 3 nodes to itself.
        System.out.println("[Regular Node 0x" + selfNode.nodeID.toHexaString() + "] - Sending Join Request...");
        rpc = new KademliaClientRPC(SERVER_ADDRESS, SERVER_PORT);
        NodeIdMessage findNodeReq = NodeIdMessage.newBuilder().setNodeid(regularNodeKey).setBootstrapnodeid(bootstrapNodeKey).build();
        NodeDetailsListMessage findNodeRes = rpc.FIND_NODE(findNodeReq);

        // Lets add the closest nodes to my routing table
        // and send FIND_NODE's to them to populate my table and theirs.
        List<Contact> nearNodes = new LinkedList<Contact>();

        for (NodeDetailsMessage auxMsg : findNodeRes.getNodesList()) {
            Contact cAux = Contact.fromNodeDetailsMessage(auxMsg);
            nearNodes.add(cAux);
            routingTable.addNode(Node.fromNodeDetailsMessage(auxMsg));
            // lets send a find node to the closest node, so the address and port are extracted from the contact. :)
            rpc = new KademliaClientRPC(cAux.getAddress(), cAux.getPort());
            // which is the regular key here? ours or the node's one?
            NodeIdMessage cReq = NodeIdMessage.newBuilder().setNodeid(cAux.nodeID.toString()).setBootstrapnodeid(bootstrap_id).build();
            NodeDetailsListMessage cRes = rpc.FIND_NODE(cReq);

        }

    }

    /**
     * Setups the Node as a bootstrap/master Peer.
     */
    public static void setupNodeAsBootstrap() {
        generateBootstrapNode(SERVER_ADDRESS, SERVER_PORT);
        serverRPC = new KademliaBootstrapRPC(SERVER_ADDRESS, SERVER_PORT);
        serverRPC.initializeConnection();
        serverRPC.openConnectionChannel();
    }

    /**
     * Sets self node using a string key, address and port.
     * */
    public static void setSelfNode(String key, String address, int port) {
        KademliaKey kKey = new KademliaKey(key);
        selfNode = new Node(kKey, address, port);
    }

    /**
     * Prompt to request the port for the client node.
     */
    public static int requestClientPort() {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Node Config");
        System.out.println("Enter port for node to attach:");
        return Integer.parseInt(stdin.next());
    }

}
