package pt.groupG.core;

import com.google.protobuf.ByteString;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import pt.groupG.core.blockchain.Blockchain;
import pt.groupG.grpc.*;

import java.util.*;

public class KademliaTree {
    List<KBucket> buckets = new ArrayList<KBucket>();
    public static int MAX_CONTACTS = 20;
    public static int ALPHA_VALUE = 3;
    Blockchain blockchain = null;
    Node currentNode = null;
    KademliaClient client = null;
    KademliaKey myKey = new KademliaKey();

    public List<Node> FIND_NODE(KademliaKey key) {

        // Gera chave com distancia maxima.
        KademliaKey k = KademliaKey.generateMaxDistanceKey();
        int lessDistance = Integer.MAX_VALUE;
        KBucket closest = null;
        /* Search all non-empty kbuckets for the closest one. */
        for (KBucket aux : buckets) {
            if (!aux.isContactsEmpty()) {
                KademliaKey i = aux.key;
                int calculatedDistance = k.calculateDistance(i);
                if (calculatedDistance < lessDistance) {
                    lessDistance = calculatedDistance;
                    closest = aux;
                }
            }
        }

        /* closest contains closest non empty k bucket */
        List<Node> closestNodes = new ArrayList<Node>();
        List<Node> parentNodes = new ArrayList<Node>();
        List<KademliaKey> keys = new LinkedList<KademliaKey>();

        if (closest != null) {
            Collections.sort(closest.contacts, new Comparator<Node>() {
                @Override
                public int compare(Node nd1, Node nd2) {
                    int d1 = nd1.nodeID.calculateDistance(key);
                    int d2 = nd2.nodeID.calculateDistance(key);
                    return (d2 - d1);
                }
            });

            int splitSize = this.ALPHA_VALUE;
            if (closest.contacts.size() < this.ALPHA_VALUE) {
                splitSize = closest.contacts.size();
            }

            parentNodes.addAll(closest.contacts.subList(0, splitSize));
            closestNodes.addAll(closest.contacts.subList(0, splitSize));

            for (int i = 0; i < splitSize; i++) {
                sendFIND_NODE(i, lessDistance, parentNodes, closestNodes, keys);
            }
        }
    }

    public void sendFIND_NODE(int i, int lessDistance, List<Node> parentNodes, List<Node> closestNodes, List<KademliaKey> keys) {
        /////////////
        // Round 1 //
        /////////////

        // gets i-th parent node.
        Node nd = parentNodes.get(i);
        // stores this key as watched.
        keys.add(nd.nodeID);

        //builds node address for endpoint, and channel for communication.
        String nodeAddress = nd.getAddress() + ':' + nd.getPort();
        Channel nodeChannel = ManagedChannelBuilder.forTarget(nodeAddress).usePlaintext().build();
        this.client = new KademliaClient(nodeChannel);

        // prepares FIND_NODE message
        NodeIdMessage req = NodeIdMessage.newBuilder().setNodeidBytes(ByteString.copyFrom(nd.nodeID.getKey())).build();
        // sends FIND_NODE Message
        NodeDetailsListMessage res = this.client.FIND_NODE(req);
        // retrieves list of NodeDetail.
        List<NodeDetailsMessage> returnedNodes = res.getNodesList();

        for (NodeDetailsMessage aux : returnedNodes) {
            // creates Node from NdDetailsMessage using factory
            Node ndAux = Node.fromNodeDetailsMessage(aux);
            this.addToBucket(ndAux);
            if (ndAux.nodeID.calculateDistance(myKey) < 0) {
                // if distance between this node and me is less than 0 -->
                closestNodes.add(ndAux);
                //idistance = node.nodeId
                //?
                //if ((!ikbucket.inRange(iaddress) && BigInteger.valueOf(Integer.valueOf(closest.size())).compareTo(ikbucket.k) == 0) || (ikbucket.inRange(iaddress) && closest.size() == 10))
                // if we got our result:
                //    return closestNodes;
            }

            /////////////
            // Round 2 //
            /////////////
            // Another round of search:
            this.sendFIND_NODERound2(ndAux, closestNodes, keys);
        }
    }


    public void sendFIND_NODERound2(Node nd, List<Node> closestNodes, List<KademliaKey> keys) {
        int currentIteration = 0; // must be less than ALPHA_VALUE

        //builds node address for endpoint, and channel for communication.
        String nodeAddress = nd.getAddress() + ':' + nd.getPort();
        Channel nodeChannel = ManagedChannelBuilder.forTarget(nodeAddress).usePlaintext().build();
        this.client = new KademliaClient(nodeChannel);

        // prepares FIND_NODE message
        NodeIdMessage req = NodeIdMessage.newBuilder().setNodeidBytes(ByteString.copyFrom(nd.nodeID.getKey())).build();
        // sends FIND_NODE Message
        NodeDetailsListMessage res = this.client.FIND_NODE(req);
        // retrieves list of NodeDetail.
        List<NodeDetailsMessage> returnedNodes = res.getNodesList();

        for (NodeDetailsMessage aux : returnedNodes) {
            // must be less than ALPHA_VALUE (max number of searches)
            if (currentIteration > ALPHA_VALUE) {
                break;
            }
            // creates Node from NdDetailsMessage using factory
            Node ndAux = Node.fromNodeDetailsMessage(aux);
            this.addToBucket(ndAux);

            if (ndAux.nodeID.calculateDistance(myKey) < 0) {
                // if distance between this node and me is less than 0 -->
                closestNodes.add(ndAux);
                //idistance = node.nodeId
                //?
                //if ((!ikbucket.inRange(iaddress) && BigInteger.valueOf(Integer.valueOf(closest.size())).compareTo(ikbucket.k) == 0) || (ikbucket.inRange(iaddress) && closest.size() == 10))
                // if we got our result:
                //    return closestNodes;
            }

            /////////////
            // Round 3 //
            /////////////
            // Last round of search:
            this.sendFIND_NODERound3(ndAux, closestNodes, keys, currentIteration);

        }

    }

    public void sendFIND_NODERound3(Node nd, List<Node> closestNodes, List<KademliaKey> keys, int currentIteration) {

        for (KademliaKey auxK : keys) {
            if (currentIteration >= ALPHA_VALUE) {
                break;
            }
            // if the node we are checking doesnt have the same key as the aux key:
            if (!nd.nodeID.equals(auxK)) {
                // increases iteration.
                currentIteration++;

                //builds node address for endpoint, and channel for communication.
                String nodeAddress = nd.getAddress() + ':' + nd.getPort();
                Channel nodeChannel = ManagedChannelBuilder.forTarget(nodeAddress).usePlaintext().build();
                this.client = new KademliaClient(nodeChannel);

                // prepares FIND_NODE message
                NodeIdMessage req = NodeIdMessage.newBuilder().setNodeidBytes(ByteString.copyFrom(auxK.getKey())).build();
                // sends FIND_NODE Message
                NodeDetailsListMessage res = this.client.FIND_NODE(req);
                // retrieves list of NodeDetail.
                List<NodeDetailsMessage> returnedNodes = res.getNodesList();

                for (NodeDetailsMessage aux : returnedNodes) {
                    // creates Node from NdDetailsMessage using factory
                    Node ndAux = Node.fromNodeDetailsMessage(aux);
                    this.addToBucket(ndAux);
                    //idistance = node.nodeId
                    //?
                    //if ((!ikbucket.inRange(iaddress) && BigInteger.valueOf(Integer.valueOf(closest.size())).compareTo(ikbucket.k) == 0) || (ikbucket.inRange(iaddress) && closest.size() == 10))
                    // if we got our result:
                    //    return closestNodes;
                }
            }
        }
    }

    public void addToBucket(Node nd) {
        // se os buckets estiverem vazios, criar novo com e adicionar lá o no.
        if (this.buckets.isEmpty()) {
            KBucket newBucket = new KBucket(new KademliaKey());
            this.buckets.add(newBucket);
            nd.bucket = newBucket;
            // ??? adds itself? ; ver melhor isto
            nd.addNodeToBucket(nd);
            return;
        }

        for (KBucket aux : this.buckets) {
            if ( aux.key.checkRange(nd.nodeID) ) { /*Dentro do alcance definido?*/

                // Check if the node is inside the bucket.
                Node ndx = aux.fetchNode(nd.nodeID);
                if (ndx != null) {
                    // removes the fetched node.
                    boolean ignore = aux.removeNode(ndx);
                    // adds the new one
                    aux.addNode(nd);
                    // assigns bucket to the new node.
                    nd.bucket = aux;
                }
                else if (/*verifica se o no nao esta cheio*/) {
                    aux.addNode(nd);
                    nd.bucket = aux;
                }
                else if (/*se esta cheio*/) {
                    //in range; less than max buckets
                    if (aux.key.checkRange(this.myKey)) {
                        aux.addNode(nd);
                        nd.bucket = aux;
                        // splitBucket(aux):
                        break;
                    }
                    else {
                        // not in range, ping to decide:
                        Node firstNode = aux.getFirstNode();
                        EmptyMessage req = EmptyMessage.newBuilder().build();
                        Channel channel = ManagedChannelBuilder.forAddress(firstNode.getAddress(), firstNode.getPort()).usePlaintext().build();
                        this.client = new KademliaClient(channel);
                        BooleanMessage res = this.client.PING(req);
                        if (res.getValue()) {
                            // received ping -> adds node to end of list
                            aux.removeNode(firstNode);
                            aux.addNode(firstNode);
                        }
                        else {
                            // ping no received -> new node to end of list
                            aux.removeNode(firstNode);
                            aux.addNode(nd);
                            nd.bucket = aux;
                        }
                    }
                    // has done changes so
                    break;
                }

            }
        }
        
    }
}
