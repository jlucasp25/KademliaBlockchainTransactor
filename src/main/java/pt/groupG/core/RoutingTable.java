package pt.groupG.core;

import java.util.LinkedList;

public class RoutingTable {
    private KBucket[] buckets = new KBucket[KademliaKey.MAX_KEY_SIZE];

    public RoutingTable() {
        for (int i = 0; i < KademliaKey.MAX_KEY_SIZE; i++) {
            buckets[i] = new KBucket(i);
        }
    }

}
