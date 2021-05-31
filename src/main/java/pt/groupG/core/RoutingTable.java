package pt.groupG.core;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import pt.groupG.grpc.NodeDetailsListMessage;
import pt.groupG.grpc.NodeDetailsMessage;
import pt.groupG.grpc.NodeIdMessage;

import java.util.Collections;
import java.util.Comparator;
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
    public static final int ALPHA_VALUE = 3;
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

    void FIND_NODE(Node nd) {
        // MAX Close nodes == alpha
        KBucket closestBucket = this.fetchClosestNonEmptyBucket(nd.nodeID);
        List<Contact> closestNodes = new LinkedList<Contact>();
//        List<Contact> farAwayNodes = new LinkedList<Contact>();
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
        // temporary list to hold return contacts. (ret in example)
        List<Contact> returnNodes = new LinkedList<Contact>();
        dispatchFIND_NODE(0, nd, closestNodes, returnNodes);
    }

    /**
     * Aggregates all the communication functions to send a FIND_NODE RPC.
     */
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

    /**
     * Prepares and parses a FIND_NODE call.
     */
    List<NodeDetailsMessage> dispatchFIND_NODE(int i, Node target, List<Contact> closestNodes, List<Contact> finalNodes) {
        //needs to separate this block into alpha concurrent requests.
        Contact closestContact = closestNodes.get(0);
        // sends a FIND_NODE RPC communication
        List<NodeDetailsMessage> returnedNodes = FIND_NODE_communication(closestContact, target);

        for (NodeDetailsMessage msgAux : returnedNodes) {
            // creates Node from NdDetailsMessage using factory
            Contact ndAux = Contact.fromNode(Node.fromNodeDetailsMessage(msgAux));
            // TODO
            // comparator needs to be overriden; filtered to remove repeats.
            finalNodes.add(ndAux);
            // if it has found the node, return the query.
            if (ndAux.nodeID.equals(target.nodeID))
                return returnedNodes;
        }

        while (finalNodes.size() < 20/*K*/) {
            // removes queried node from the closest nodes list.
            closestNodes.remove(closestContact);
            // if there is still close nodes:
            List<Contact> newClosestNodes = new LinkedList<Contact>(closestNodes);
            if (!newClosestNodes.isEmpty()) {
                int nNodes = newClosestNodes.size() < ALPHA_VALUE ? newClosestNodes.size() : ALPHA_VALUE;
                // TODO
                // sort nodes again.
                closestContact = closestNodes.get(0);
                // sends a FIND_NODE RPC communication
                returnedNodes = FIND_NODE_communication(closestContact, target);

                for (NodeDetailsMessage msgAux : returnedNodes) {
                    // creates Node from NdDetailsMessage using factory
                    Contact ndAux = Contact.fromNode(Node.fromNodeDetailsMessage(msgAux));
                    // TODO
                    // comparator needs to be overriden; filtered to remove repeats.
                    finalNodes.add(ndAux);
                    // if it has found the node, return the query.
                    if (ndAux.nodeID.equals(target.nodeID))
                        return returnedNodes;
                }
                // (giveMeAll ? ret : ret.Take(Constants.K).OrderBy(c => c.ID ^ key).ToList()),
            }
        }
    }

    /**
     * Fetches closest non-empty K-Bucket.
     */
    KBucket fetchClosestNonEmptyBucket(KademliaKey key) {
        int closestDistance = KademliaKey.MAX_KEY_SIZE;
        KBucket closestBucket = null;
        for (KBucket aux : this.buckets) {
            if (!aux.isEmpty()) {
                // TODO
                // we are assuming each bucket has a key;
                // probably the XOR between our node and the buckets content.
                KademliaKey auxKey = aux.key;
                int auxDistance = auxKey.calculateDistance(key);
                if (auxDistance <= closestDistance) {
                    closestBucket = aux;
                    closestDistance = auxDistance;
                }
            }
        }
        return closestBucket;
    }


}
