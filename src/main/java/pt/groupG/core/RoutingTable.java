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

    private List<KBucket> buckets = new LinkedList<KBucket>();
    public Node self;
    public static final int ALPHA_VALUE = 3;
    // buckets cant be bigger than maxBucketsSize
    private static final int maxBucketsSize = KademliaKey.MAX_KEY_SIZE;
    public KademliaClient client = null;

    public void setClient(KademliaClient client) {
        this.client = client;
    }

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

    // TODO
    // Replace argument to Key
/*
    void FIND_NODE(Node nd) {
        // MAX Close nodes == alpha
        Integer minDistance = 0;
        KBucket closestBucket = this.fetchClosestNonEmptyBucket(nd.nodeID);
        List<Contact> closestNodes = new LinkedList<Contact>();
        List<Contact> farAwayNodes = new LinkedList<Contact>();
//        for (Contact aux : closestBucket.contacts) {
//            int distanceBetweenNodeAndTarget = aux.calculateDistance(nd);
//            int distanceBetweenSelfAndTarget = self.calculateDistance(nd);
//            if (distanceBetweenNodeAndTarget < distanceBetweenSelfAndTarget) {
//                closestNodes.add(aux);
//            }
//            else {
//                farAwayNodes.add(aux);
//            }
//        }
        Collections.sort(closestBucket.contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                int d1 = c1.calculateDistance(nd);
                int d2 = c2.calculateDistance(nd);
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

        //  closerContacts.AddRange(nodesToQuery.Where(
        //    n => (n.ID ^ key) < (node.OurContact.ID ^ key)));

        // Number of closest nodes
        // Limit closest contacts to alpha value or less if theres less contacts than alpha value.
        int nNodes = closestBucket.contacts.size() < ALPHA_VALUE ? closestBucket.contacts.size() : ALPHA_VALUE;
        closestNodes.addAll(closestBucket.contacts.subList(0, nNodes - 1));
        farAwayNodes.addAll(closestBucket.contacts.subList(nNodes, closestBucket.contacts.size() - 1));
        // temporary list to hold return contacts. (ret in example)
        List<Contact> returnNodes = new LinkedList<Contact>();
        dispatchFIND_NODE(nNodes, nd, closestNodes, farAwayNodes);
    }

    */
/**
 * Aggregates all the communication functions to send a FIND_NODE RPC.
 *//*

    List<NodeDetailsMessage> FIND_NODE_communication(Contact contactedNode, Node targetNode) {
        String nodeAddress = contactedNode.getAddress() + ':' + contactedNode.getPort();
        ManagedChannel nodeChannel = ManagedChannelBuilder.forTarget(nodeAddress).usePlaintext().build();
        KademliaClient kc = new KademliaClient(nodeChannel);
        // prepares FIND_NODE message
        NodeIdMessage req = NodeIdMessage.newBuilder().setNodeid(targetNode.nodeID.toString()).build();
        // sends FIND_NODE Message
        NodeDetailsListMessage res = kc.FIND_NODE(req);
        // retrieves list of NodeDetail.
        return res.getNodesList();
    }

    */
/**
 * Prepares and parses a FIND_NODE call.
 *//*

    List<NodeDetailsMessage> dispatchFIND_NODE(int nConcurrent, Node target, List<Contact> closestNodes, List<Contact> farAwayNodes) {
        // list to remove duplicates.
        List<KademliaKey> contactedKeys = new LinkedList<KademliaKey>();

        // current bucket key.
        //KademliaKey closestDistanceKey = closestBucket.key;

        */
    /*NOT ASYNCHRONOUS FOR NOW*//*

        for (int i = 0; i < nConcurrent; i++) {
            Contact closestContact = closestNodes.get(i);
            System.out.println("[!] Sending FIND_NODE RPC to " + closestContact);
            contactedKeys.add(closestContact.nodeID);
            List<NodeDetailsMessage> returnedNodes = FIND_NODE_communication(closestContact, target);
            for (NodeDetailsMessage msgAux : returnedNodes) {
                Node returnedNode = Node.fromNodeDetailsMessage(msgAux);
                Contact returnedContact = Contact.fromNode(returnedNode);
                // add received node to buckets.
                this.addNode(returnedNode);
                // if node distance is less than current bucket -> minDistance
                if (returnedNode.nodeID.calculateDistance(closestDistanceKey) <= 0) {
                    closestDistanceKey = returnedNode.nodeID;
                    closestNodes.add(returnedContact);
                    // if blabla bla 
                    // return 
                }
                
                // For each returned node, lets send a FIND_NODE
                // lets add first our key to the contacted keys.
                contactedKeys.add(returnedContact.nodeID);
                List<NodeDetailsMessage> newReturnedNodes = FIND_NODE_communication(returnedContact, target);
                int nRequests = 0;
                for (NodeDetailsMessage msgAux2 : newReturnedNodes) {
                    // cant let it the make more than ALPHA requests
                    if (nRequests >= ALPHA_VALUE) {
                        break;
                    }
                    Node returnedNode2 = Node.fromNodeDetailsMessage(msgAux2);
                    Contact returnedContact2 = Contact.fromNode(returnedNode2);
                    // add received node to buckets.
                    this.addNode(returnedNode2);
                    // if node distance is less than current bucket -> minDistance
                    if (returnedNode.nodeID.calculateDistance(closestDistanceKey) <= 0) {
                        closestDistanceKey = returnedNode.nodeID;
                        closestNodes.add(returnedContact);
                        // if blabla bla
                        // return
                    }

                }
            }
        }
*/

//
//
//
//
//        if (!returnedNodes.isEmpty()) {
//            boolean found = false;
//            for (NodeDetailsMessage msgAux : returnedNodes) {
//                // creates Node from NdDetailsMessage using factory
//                Node ndAux = Node.fromNodeDetailsMessage(msgAux);
//                // TODO
//                // comparator needs to be overriden; filtered to remove repeats.
//
//                // Insert found contact into kbuckets. (Contact converted to node, lost info)
//                this.addNode(ndAux);
//
//                /* The distance between me and me is <= 0 */
//                if (ndAux.nodeID.calculateDistance(closestDistance) <= 0) {
//                    // this node has the less distance, therefore is closer.
//                    closestDistance = ndAux.nodeID;
//                    closestNodes.add(Contact.fromNode(ndAux));
//                }
//            }
//            if (closestNodes.size() >= 20) {
//                return closestNodes.subList(0, 19);
//            }
//            else {
//                //do smth
//            }
//        }
//        else {
//            // do smth
//
//        }
//
//        while (finalNodes.size() < 20/*K*/) {
//            // removes queried node from the closest nodes list.
//            closestNodes.remove(closestContact);
//            // if there is still close nodes:
//            List<Contact> newClosestNodes = new LinkedList<Contact>(closestNodes);
//            if (!newClosestNodes.isEmpty()) {
//                int nNodes = newClosestNodes.size() < ALPHA_VALUE ? newClosestNodes.size() : ALPHA_VALUE;
//                // TODO
//                // sort nodes again.
//                closestContact = closestNodes.get(0);
//                // sends a FIND_NODE RPC communication
//                returnedNodes = FIND_NODE_communication(closestContact, target);
//
//                for (NodeDetailsMessage msgAux : returnedNodes) {
//                    // creates Node from NdDetailsMessage using factory
//                    Contact ndAux = Contact.fromNode(Node.fromNodeDetailsMessage(msgAux));
//                    // TODO
//                    // comparator needs to be overriden; filtered to remove repeats.
//                    finalNodes.add(ndAux);
//                    // if it has found the node, return the query.
//                    if (ndAux.nodeID.equals(target.nodeID))
//                        return returnedNodes;
//                }
//                // (giveMeAll ? ret : ret.Take(Constants.K).OrderBy(c => c.ID ^ key).ToList()),
//            }
//        }


    /**
     * Fetches closest non-empty K-Bucket.
     */
    List<KBucket> fetchClosestNonEmptyBucket(KademliaKey targetKey) {
        int closestDistance = KademliaKey.MAX_KEY_SIZE;
        KBucket closestBucket = null;
        List<KBucket> orderedBuckets = new LinkedList<>();
        // copied buckets to not change order of this.buckets.
        Collections.copy(orderedBuckets, this.buckets);
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
    public List<Contact> getClosestNodes(String regular_id, String bootstrap_id) {
        Set<Contact> closestNodes = new HashSet<Contact>();
        Set<KademliaKey> contactedNodes = new HashSet<KademliaKey>();
        List<KBucket> targetKBuckets = fetchClosestNonEmptyBucket(new KademliaKey(regular_id));
        // for value in buckets list of bootstrap_id
        List<Contact> closestContacts = new LinkedList<>();

        for (KBucket auxBucket : targetKBuckets) {
            List<Contact> bucketContacts = new LinkedList<>();
            Collections.copy(bucketContacts, auxBucket.contacts);
            Collections.sort(bucketContacts, new Comparator<Contact>() {
                @Override
                public int compare(Contact c1, Contact c2) {
                    int d1 = c1.nodeID.calculateDistance(new KademliaKey(regular_id));
                    int d2 = c2.nodeID.calculateDistance(new KademliaKey(regular_id));
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
        return closestContacts.subList(0,2);
    }
}
