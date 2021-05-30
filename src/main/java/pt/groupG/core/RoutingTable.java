package pt.groupG.core;

import java.util.LinkedList;
import java.util.List;

public class RoutingTable {
    /**
     * Routing Table
     * Contains a list of KBuckets, one for each bit on the key.
     * Therefore the max size of the buckets list is MAX_KEY_SIZE.
     */

    private List<KBucket> buckets = new LinkedList<KBucket>();
    public Node self;
    // buckets cant be bigger than maxBucketsSize
    private static final int maxBucketsSize = KademliaKey.MAX_KEY_SIZE;

    public RoutingTable() {
        // Creates empty buckets.
        for (int i = 0; i < RoutingTable.maxBucketsSize; i++) {
            buckets.add(new KBucket(i));
        }
    }

    public void setSelfNode(Node self) {
        this.self = self;
    }

    /**
     * Adds a node to the routing table.
     * Finds the corresponding k-bucket and adds the node to its k-bucket.
     */
    void addNode(Node aux) {
        // the position on the tree is the distance (XOR) between them.
        // In the case of lists, is the calculateDistance return.
        // treePosition < 0 ? 0 : treePosition; if the distance is < 0, use 0.
        int treePosition = this.self.calculateDistance(aux) - 1;
        KBucket correspondingBucket = this.buckets.get(treePosition < 0 ? 0 : treePosition);
        correspondingBucket.addNode(aux);
    }

    public String toString() {
        String total = "";
        for (KBucket aux : this.buckets) {
            if (aux.toString() != null) {
                total += aux.toString();
            }
        }
        return total;
    }


}
