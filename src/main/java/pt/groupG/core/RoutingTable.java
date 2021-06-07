package pt.groupG.core;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import pt.groupG.grpc.NodeDetailsListMessage;
import pt.groupG.grpc.NodeDetailsMessage;
import pt.groupG.grpc.NodeIdMessage;

import java.util.*;

public class RoutingTable {
    /**
     * Routing Table
     * Contains a list of KBuckets, one for each bit on the key.
     * Therefore the max size of the buckets list is MAX_KEY_SIZE.
     */

    public List<KBucket> buckets = new LinkedList<KBucket>();
    public Node self;
    public static final int ALPHA_VALUE = 3;
    // buckets cant be bigger than maxBucketsSize
    private static final int maxBucketsSize = KademliaKey.MAX_KEY_SIZE;
    public RoutingTable(Node self) {
        // Creates empty buckets.
        this.self = self;
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

    /**
     * Fetches closest non-empty K-Bucket.
     */
    List<KBucket> fetchClosestNonEmptyBucket(KademliaKey targetKey) {
        int closestDistance = KademliaKey.MAX_KEY_SIZE;
        KBucket closestBucket = null;
        List<KBucket> orderedBuckets = new LinkedList<KBucket>();
        // copied buckets to not change order of this.buckets.
        orderedBuckets.addAll(this.buckets);
        // ordered by distance.
        Collections.sort(orderedBuckets, new Comparator<KBucket>() {
            @Override
            public int compare(KBucket k1, KBucket k2) {
                int d1 = k1.key.calculateDistance(targetKey);
                int d2 = k2.key.calculateDistance(targetKey);
                if (d1 == d2)
                    return 0;
                else if (d1 < d2)
                    return -1;
                else
                    return 1;
            }
        });
        List<KBucket> selectedBuckets = new LinkedList<>();
        int nNodesFound = 0;
        // pop bucket while nNodesFound < 3.
        for (KBucket aux : orderedBuckets) {
            selectedBuckets.add(aux);
            nNodesFound += aux.contacts.size();
            if (nNodesFound >= 3) {
                break;
            }
        }
        return selectedBuckets;
    }

    /* FIND_NODE:
     - Node P joined the network
     - bootstrap node gives P an ID
     - P sends FIND_NODE(P.ID) to bootstrap node
     - boostrap node search for 3 closest nodes to P
     - routing table of contacted nodes is continuous updated
     */
    /*
    * Bootstrap-Side - finds closest nodes to the node that joined and sends to him.
    * */
    public List<Contact> getClosestNodes(ByteString regularId, ByteString bootstrapId) {
        Set<Contact> closestNodes = new HashSet<Contact>();
        Set<KademliaKey> contactedNodes = new HashSet<KademliaKey>();
        List<KBucket> targetKBuckets = fetchClosestNonEmptyBucket(new KademliaKey(regularId));
        // for value in buckets list of bootstrap_id
        List<Contact> closestContacts = new LinkedList<>();

        for (KBucket auxBucket : targetKBuckets) {
            List<Contact> bucketContacts = new LinkedList<>();
            bucketContacts.addAll(auxBucket.contacts);
            bucketContacts.remove(new Contact(new KademliaKey(regularId),"",0));
            Collections.sort(bucketContacts, new Comparator<Contact>() {
                @Override
                public int compare(Contact c1, Contact c2) {
                    int d1 = c1.nodeID.calculateDistance(new KademliaKey(regularId));
                    int d2 = c2.nodeID.calculateDistance(new KademliaKey(regularId));
                    // TODO
                    // improve condition
                    if (d1 == d2) {
                        return 0;
                    } else if (d1 < d2) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });
            closestContacts.addAll(bucketContacts);
            if (closestContacts.size() >= 3 ) {
                break;
            }
        }

        if (closestContacts.size() >= 3) {
            return closestContacts.subList(0,2);
        }
        else {
            return closestContacts;
        }
    }
}
