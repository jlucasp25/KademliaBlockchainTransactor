package pt.groupG.core;

import Utils.HashCash;
import com.google.protobuf.ByteString;
import pt.groupG.core.blockchain.Block;
import pt.groupG.grpc.JoinMessage;
import pt.groupG.grpc.NodeDetailsListMessage;
import pt.groupG.grpc.NodeDetailsMessage;
import pt.groupG.grpc.NodeIdMessage;

import java.security.NoSuchAlgorithmException;
import java.util.*;

import static Utils.StringUtils.generateRandomString;

/**
 * Main/Core class
 * Contains the starting instructions and manages most of the application.
 */
public class Core {
    // Network Properties
    private final static String SERVER_ADDRESS = "localhost";
    private final static int SERVER_PORT = 8000;
    private static int CLIENT_PORT = 8010;
    /*
    KademliaClientRPC must be called as independently like an HTTP request.
    KademliaBootstrapRPC & KademliaClientChannelRPC must be used as a WebSocket, always open for connections.
    */
    private static KademliaBootstrapChannelRPC serverRPC = null;
    private static KademliaClientChannelRPC clientRPC = null;

    // Kademlia Properties
    public static RoutingTable routingTable = null;
    public static Node selfNode;

    // Blockchain Properties
    public static List<Block> blockchain = new LinkedList<Block>();


    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Select 1 for regular node, 2 for bootstrap node");
        String s = stdin.next();
        if (s.equals("1")) {
            // send initialWork to bootstrap node for validation
            // only after this validation, can the node join the network
            setupNodeAsRegular();
        } else if (s.equals("2")) {
            // TODO
            // o bootstrap node tem de ser sempre o mesmo, passar a estatico.
            setupNodeAsBootstrap();
        } else {
            System.out.println("Wrong Option! Exiting.");
            return;
        }
    }

    /**
     * Generates and sets the self node as a Bootstrap one.
     */
    public static void generateBootstrapNode(String address, int port) {
        KademliaKey kKey = new KademliaKey();
        System.out.println("[Bootstrap Key] 0x" + kKey.toHexaString());
        selfNode = new Node(kKey, address, port);
    }

    /**
     * Forces some computation/work to happen before joining the network
     * To prevent Sybil Attack
     */
    public static String sybilAttackPrevention() throws NoSuchAlgorithmException {
        return HashCash.mintCash(generateRandomString(), 10).toString();
    }

    /**
     * Setups the Node as a regular Peer.
     */
    public static void setupNodeAsRegular() {
        // TEMPORARY: ask for port to attach itself
        CLIENT_PORT = requestClientPort();

        // Generate initial work for POW
        String initialWork = null;
        try {
            initialWork = sybilAttackPrevention();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("[Regular Node #NONE] Initial Work failed. Exiting!");
            return;
        }

        // sends a JOIN request to the Bootstrap Node
        // to get its own key.
        System.out.println("[Regular Node #NONE] - Sending Join Request...");
        KademliaClientRPC rpc = new KademliaClientRPC(SERVER_ADDRESS, SERVER_PORT);
        JoinMessage joinReq = JoinMessage.newBuilder().setAddress(SERVER_ADDRESS).setPort(CLIENT_PORT).setInitialWork(initialWork).build();
        NodeIdMessage joinRes = rpc.JOIN(joinReq);
        ByteString bootstrapNodeKey = joinRes.getBootstrapnodeidBytes();
        ByteString regularNodeKey = joinRes.getNodeidBytes();
        System.out.println("[My Key] 0x" + new KademliaKey(regularNodeKey).toHexaString());

        // Checks if the initial work is valid.
        if (bootstrapNodeKey.equals("")) {
            System.out.println("Invalid Initial Work found when performing JOIN! Exiting.");
            return;
        }

        // sets its own node ("self") with the ID provided by the JOIN process.
        setSelfNode(regularNodeKey, SERVER_ADDRESS, CLIENT_PORT);

        // generates empty routing table
        routingTable = new RoutingTable(selfNode);

        // start regular node connection channel
        // KademliaClientChannelRPC extends Thread therefore runs in parallel
        clientRPC = new KademliaClientChannelRPC(SERVER_ADDRESS, CLIENT_PORT, routingTable, selfNode);
        clientRPC.start();

        // adds the bootstrap node to its routing table.
        routingTable.addNode(new Node(new KademliaKey(bootstrapNodeKey), SERVER_ADDRESS, SERVER_PORT));



        // send a FIND_NODE request to the bootstrap node
        // so that it finds the closest 3 nodes to itself.
        System.out.println("[Regular Node] - Sending Find Node Request to bootstrap.");
        rpc = new KademliaClientRPC(SERVER_ADDRESS, SERVER_PORT);
        NodeDetailsMessage findNodeReq = NodeDetailsMessage.newBuilder().setNodeidBytes(regularNodeKey).setAddress(SERVER_ADDRESS).setPort(CLIENT_PORT).setBootstrapnodeidBytes(bootstrapNodeKey).build();
        NodeDetailsListMessage findNodeRes = rpc.FIND_NODE(findNodeReq);

        // Lets add the closest nodes to my routing table
        // and send FIND_NODE's to them to populate my table and theirs.
        Set<Contact> totalNearNodes = new HashSet<Contact>();
        Set<Contact> contactedNodes = new HashSet<Contact>();
        for (NodeDetailsMessage auxMsg : findNodeRes.getNodesList()) {
            totalNearNodes.add(Contact.fromNodeDetailsMessage(auxMsg));
        }

        while (totalNearNodes.size() != 0) {
            // hacky stuff
            Contact nearNode = (Contact) totalNearNodes.toArray()[0];

            System.out.println("[FIND_NODE result (from bootstrap)] 0x" + nearNode.nodeID.toHexaString());
            // add this Node to my routing table.
            routingTable.addNode(Node.fromContact(nearNode));

            // lets add the node to the contacted ones.
            contactedNodes.add(nearNode);
            System.out.println("contacted size: " + contactedNodes.size());
            for (Contact aux : contactedNodes) {

                System.out.println("contacted nodes : " + aux);
            }

            // lets send a find node to the closest node, so the address and port are extracted from the contact. :)
            System.out.println("[Regular Node] - Sending Find Node Request to 0x" + nearNode.nodeID.toHexaString());
            rpc = new KademliaClientRPC(nearNode.getAddress(), nearNode.getPort());
            NodeDetailsMessage cReq = NodeDetailsMessage.newBuilder().setNodeidBytes(ByteString.copyFrom(selfNode.nodeID.byteKey)).setAddress(SERVER_ADDRESS).setPort(CLIENT_PORT).setBootstrapnodeidBytes(bootstrapNodeKey).build();
            NodeDetailsListMessage cRes = rpc.FIND_NODE(cReq);

            // lets check the new near nodes.
            for (NodeDetailsMessage aux : cRes.getNodesList()) {
                Contact cAux = Contact.fromNodeDetailsMessage(aux);
                System.out.println("[FIND_NODE result (from regular node) 0x" + cAux.nodeID.toHexaString());
                // if this node is new aka hasnt been contacted, add it to the set.
                // if the node hasnt been contacted but its a repeat, it wont be added because its a set.
                if (!contactedNodes.contains(cAux)) {

                    totalNearNodes.add(cAux);
                }
            }
            // lets remove the current node from the list.
            totalNearNodes.remove(nearNode);

        }

    }

    /**
     * Setups the Node as a bootstrap/master Peer.
     */
    public static void setupNodeAsBootstrap() {
        generateBootstrapNode(SERVER_ADDRESS, SERVER_PORT);
        routingTable = new RoutingTable(selfNode);
        serverRPC = new KademliaBootstrapChannelRPC(SERVER_ADDRESS, SERVER_PORT, routingTable, selfNode);
        serverRPC.start();
    }

    /**
     * Sets self node using a string key, address and port.
     */
    public static void setSelfNode(ByteString key, String address, int port) {
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
