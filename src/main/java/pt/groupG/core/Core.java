package pt.groupG.core;

import pt.groupG.core.blockchain.Block;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Core {
    private static String SERVER_ADDRESS = "";
    private static String SELF_ADDRESS = "";
    private static String SERVER_PORT = "";
    private static String SELF_PORT = "";

    public static String nodeId;
    public static List<Block> blockchain = new LinkedList<Block>();
    public static List<Node> KBuckets = new ArrayList<Node>();

}
