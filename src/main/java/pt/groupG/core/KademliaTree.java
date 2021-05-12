package pt.groupG.core;

import pt.groupG.core.blockchain.Blockchain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KademliaTree {
    List<KBucket> buckets = new ArrayList<KBucket>();
    public static int MAX_CONTACTS = 20;
    public static int ALPHA_VALUE = 3;
    Blockchain blockchain = null;
    Node currentNode = null;

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

            for ( int i = 0; i < splitSize; i++) {
                sendFIND_NODE(i, parentNodes, closestNodes);
            }
        }
    }

    public void sendFIND_NODE(int i, List<Node> parentNodes, List<Node> closestNodes) {
            // send alpha FIND NODES
            Node nd = parentNodes.get(i);
            //client = new P2PClient(ManagedChannelBuilder.forTarget(inode.ip + ":" + inode.port).usePlaintext().build());
            Iterator<NodeInfo> round1 = client.FIND_NODE(inode.toNodeID(key).build());
            listenKeys.add(inode.nodeID);
            while ( round1 != null && round1.hasNext()) {
                NodeInfo info = round1.next();
                Node node = new Node(info.getNode());
                inserts(node);
                if (node.nodeID.compareTo(idistance) < 0) {
                    idistance = node.nodeID;
                    closest.add(node);
                    if ((!ikbucket.inRange(iaddress) && BigInteger.valueOf(Integer.valueOf(closest.size())).compareTo(ikbucket.k) == 0) || (ikbucket.inRange(iaddress) && closest.size() == 10))
                        return closest;
                }
                // round2 send findnode to k triples
                System.out.println("round2(" + i + ") id:" + new BigInteger(1, node.nodeID.key) + " port: " + node.port);
                client = new P2PClient(ManagedChannelBuilder.forTarget(node.ip + ":" + node.port).usePlaintext().build());
                Iterator<NodeInfo> round2 = client.FIND_NODE(node.toNodeID(key).build());
                listenKeys.add(node.nodeID);
                int counter = 0;
                while ( (round2 != null && round2.hasNext() && counter <= alpha)){
                    NodeInfo info2 = round2.next();
                    Node node2 = new Node(info2.getNode());
                    inserts(node2);
                    if (node2.nodeID.compareTo(idistance) < 0) {
                        idistance = node2.nodeID;
                        closest.add(node2);
                        if ((!ikbucket.inRange(iaddress) && BigInteger.valueOf(Integer.valueOf(closest.size())).compareTo(ikbucket.k) == 0) || (ikbucket.inRange(iaddress) && closest.size() == 10))
                            return closest;
                    }
                    for (int u=0; u<listenKeys.size(); u++){
                        Key ikey = listenKeys.get(u);
                        if (counter == alpha)
                            break;
                        if (node2.nodeID.compareTo(ikey) != 0) {
                            System.out.println("round3(" + i + ") id:" + new BigInteger(1, node2.nodeID.key) + " port: " + node2.port);
                            client = new P2PClient(ManagedChannelBuilder.forTarget(node2.ip + ":" + node2.port).usePlaintext().build());
                            Iterator<NodeInfo> round3 = client.FIND_NODE(node2.toNodeID(key).build());
                            counter++;
                            while ( round3 != null && round3.hasNext()) {
                                NodeInfo info3 = round3.next();
                                Node node3 = new Node(info3.getNode());
                                inserts(node3);
                                if (node3.nodeID.compareTo(idistance) < 0) {
                                    idistance = node3.nodeID;
                                    closest.add(node3);
                                    if ((!ikbucket.inRange(iaddress) && BigInteger.valueOf(Integer.valueOf(closest.size())).compareTo(ikbucket.k) == 0) || (ikbucket.inRange(iaddress) && closest.size() == 10))
                                        return closest;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
    } else {
        System.out.println("null");
    }
    ////logger.info("ended lookup");
        return closest;
    }
}
