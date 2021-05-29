package pt.groupG.core;

import pt.groupG.core.blockchain.Block;
import pt.groupG.grpc.EmptyMessage;
import pt.groupG.grpc.JoinMessage;
import pt.groupG.grpc.NodeIdMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Core {
    private static KademliaServer server = null;
    private static KademliaClient client = null;

    private static String SERVER_ADDRESS = "";
    private static String SELF_ADDRESS = "";
    private static String SERVER_PORT = "";
    private static String SELF_PORT = "";

    public static String nodeId;
    public static Node selfNode;
    public static List<Block> blockchain = new LinkedList<Block>();
    public static List<Node> KBuckets = new ArrayList<Node>();



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
        String host = requestHost();
        int port = requestPort();
        client = new KademliaClient(host, port);
        client.initializeConnection();
        JoinMessage req = JoinMessage.newBuilder().setAddress().setPort().build();
        NodeIdMessage res = client.JOIN(req);
        res.
    }

    public static void setupBootstrapNode() {
        server = new KademliaServer();
        try {
            server.initializeConnection();
            server.openConnectionChannel();
        }
        catch (IOException | InterruptedException e) {
            System.out.println("Bootstrap Node - Initialization Error!");
        }
    }

    public static String requestHost() {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Node Config");
        System.out.println("Enter bootstrap node host");
        return stdin.next();
    }

    public static int requestPort() {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Node Config");
        System.out.println("Enter bootstrap node host");
        return Integer.parseInt(stdin.next());
    }

}
