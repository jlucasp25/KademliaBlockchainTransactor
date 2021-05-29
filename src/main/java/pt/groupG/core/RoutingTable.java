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
    // buckets cant be bigger than maxBucketsSize;
    private static final int maxBucketsSize = KademliaKey.MAX_KEY_SIZE;

}
