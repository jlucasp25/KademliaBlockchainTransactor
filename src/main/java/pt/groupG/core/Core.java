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

public class Core {
    // Server/Client Info
    private final static String SERVER_ADDRESS = "localhost";
    private final static int SERVER_PORT = 8000;
    private static int CLIENT_PORT = 8010;

    // Network/Kademlia Services
    private static KademliaServer server = null;
    private static KademliaClient client = null;
    public static RoutingTable routingTable = new RoutingTable();
    public static Node selfNode;

    public static void generateBootstrapNode(String address, int port) {
        KademliaKey kKey = new KademliaKey();
        selfNode = new Node(kKey, address, port);
    }

    // Blockchain
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
        else if(s.equals("2")) { // o bootstrap node tem de ser sempre o mesmo, n deixar o gajo criar
            setupNodeAsBootstrap();
        }
        else {
            System.out.println("Wrong Option! Exiting.");
            return;
        }
    }

    /**
     * Forces some computation/work to happen before joining the network
     * To prevent Eclipse Attack
     */
    public static String eclipseAttackPrevention() throws NoSuchAlgorithmException {
        return HashCash.mintCash(generateRandomString(), 10).toString();
    }

    public static void setupNodeAsRegular(String initialWork) {
        // ask for port to attach itself
        CLIENT_PORT = requestClientPort();
        client = new KademliaClient(SERVER_ADDRESS,CLIENT_PORT);
        //server = new KademliaServer();

        // sets an empty routing table.
        client.setRoutingTable(routingTable);
        client.initializeConnection();
        client.routingTable.setClient(client);

        // sends a JOIN request
        System.out.println("Regular Node - Sent Join Request!");
        JoinMessage j_req = JoinMessage.newBuilder()
                .setAddress(SERVER_ADDRESS)
                .setPort(CLIENT_PORT)
                .setInitialWork(initialWork)
                .build();
        NodeIdMessage j_res = client.JOIN(j_req);
        String bootstrap_id = j_res.getBootstrapnodeid();
        String regular_id = j_res.getNodeid();
        if(bootstrap_id.equals("")) { // invalid hashcash was sent
            System.out.println("Couldn't validate initial work. Leaving!");
            return;
        }

        // sets its own node with the id provided by the bootstrap.
        client.setNodefromJOIN(regular_id, SERVER_ADDRESS, CLIENT_PORT);

        // adds the bootstrap node to its routing table.
        client.routingTable.addNode(new Node(new KademliaKey(bootstrap_id), SERVER_ADDRESS, SERVER_PORT));
        System.out.println(routingTable);

        //send a FIND_NODE request
        NodeIdMessage f_req = NodeIdMessage.newBuilder().setNodeid(regular_id).setBootstrapnodeid(bootstrap_id).build();
        NodeDetailsListMessage f_res = client.FIND_NODE(f_req);
        /*THE INITIAL FIND NOTE REQUEST RETURNS 3 NODES NEAR ME. LETS SEND A FIND NODE TO THEM TO POPULATE MY TABLE.*/
        List<Contact> nearNodes = new LinkedList<>();

        for (NodeDetailsMessage auxMsg : f_res.getNodesList()) {
            Contact cAux = Contact.fromNodeDetailsMessage(auxMsg);
            nearNodes.add(cAux);
            routingTable.addNode(Node.fromNodeDetailsMessage(auxMsg));
            NodeIdMessage cReq = NodeIdMessage.newBuilder().setNodeid(cAux.nodeID.toString()).setBootstrapnodeid(bootstrap_id).build();
            NodeDetailsListMessage cRes = client.FIND_NODE(cReq);

        }

    }

    public static void setupNodeAsBootstrap() {
        generateBootstrapNode(SERVER_ADDRESS, SERVER_PORT);
        server = new KademliaServer();
        server.setRoutingTable(routingTable);

        server.routingTable.setSelfNode(server.self);
        try {
            server.initializeConnection();
            server.openConnectionChannel();
        }
        catch (IOException | InterruptedException e) {
            System.out.println("Bootstrap Node - Initialization Error!");
        }
    }

    public static int requestClientPort() {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Node Config");
        System.out.println("Enter port for node to attach:");
        return Integer.parseInt(stdin.next());
    }
}
