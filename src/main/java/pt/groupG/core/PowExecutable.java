package pt.groupG.core;

import Utils.HashCash;
import com.google.protobuf.ByteString;
import pt.groupG.core.Contact;
import pt.groupG.core.KademliaClientRPC;
import pt.groupG.core.Node;
import pt.groupG.core.RoutingTable;
import pt.groupG.core.blockchain.Block;
import pt.groupG.core.blockchain.Blockchain;
import pt.groupG.grpc.EmptyMessage;
import pt.groupG.grpc.StoreMessage;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static Utils.StringUtils.generateRandomString;

public class PowExecutable extends Thread {
    public List<String> trans = null;
    public Node selfNode = null;
    public ByteString bootstrapId = null;
    public RoutingTable routingTable = null;
    public Blockchain blockchain = null;

    public PowExecutable(List<String> trans, Node selfNode, ByteString bootstrapId, RoutingTable routingTable, Blockchain blockchain) {
        this.trans = trans;
        this.selfNode = selfNode;
        this.bootstrapId = bootstrapId;
        this.routingTable = routingTable;
        this.blockchain = blockchain;
    }

    private void createBlockPow() {
        while(true) {
            if (trans.size() != 0) {
                try {
                    String cash = HashCash.mintCash(generateRandomString(), 10).toString();
                    Block newBlock = new Block(trans);
                    blockchain.newBlock(newBlock);
                    trans.clear();

                    List<Contact> closestNodes = routingTable.getClosestNodes(ByteString.copyFrom(selfNode.nodeID.byteKey), bootstrapId);

                    for (Contact aux : closestNodes) {
                        KademliaClientRPC rpc = new KademliaClientRPC(aux.getAddress(), aux.getPort());
                        StoreMessage req = StoreMessage.newBuilder().addAllTransaction(newBlock.trans).setCash(cash).setId(newBlock.hash).build();
                        EmptyMessage res = rpc.STORE(req);
                    }

                    TimeUnit.SECONDS.sleep(10);
                } catch (NoSuchAlgorithmException | InterruptedException e) {
                    System.out.println("[Regular Node] Error during POW. ");
                    return;
                }
            }
        }
    }

    public void run() {
        this.createBlockPow();
    }
}
