package pt.groupG.core;

import pt.groupG.core.blockchain.Block;
import pt.groupG.grpc.JoinMessage;
import pt.groupG.grpc.NodeIdMessage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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

    // Blockchain
    public static List<Block> blockchain = new LinkedList<Block>();

    public static void main(String[] args) {
        // Stupid java
        Scanner stdin = new Scanner(System.in);
        System.out.println("Select 1 for regular node, 2 for bootstrap node");
        String s = stdin.next();
        if (s.equals("1")) {
            setupRegularNode();
        }
        else if(s.equals("2")) {
            setupBootstrapNode();
        }
        else {
            System.out.println("Wrong Option! Exiting.");
            return;
        }
    }

    public static void setupRegularNode() {
        // ask for port to attach itself
        CLIENT_PORT = requestClientPort();
        client = new KademliaClient(SERVER_ADDRESS,SERVER_PORT);
        // sets an empty routing table.
        client.setRoutingTable(routingTable);
        client.initializeConnection();
        // sends a JOIN request
        System.out.println("Regular Node - Sent Join Request!");
        JoinMessage req = JoinMessage.newBuilder().setAddress(SERVER_ADDRESS).setPort(CLIENT_PORT).build();
        NodeIdMessage res = client.JOIN(req);
        // sets its own node with the id provided by the bootstrap.
        client.setNodefromJOIN(res.getNodeid(), SERVER_ADDRESS, CLIENT_PORT);
        // adds the bootstrap node to its routing table.
        client.routingTable.addNode(new Node(new KademliaKey(res.getBootstrapnodeid()), SERVER_ADDRESS, SERVER_PORT));
        System.out.println(routingTable);
    }

    public static void setupBootstrapNode() {
        server = new KademliaServer();
        server.setRoutingTable(routingTable);
        server.generateBootstrapNode(SERVER_ADDRESS, SERVER_PORT);
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
