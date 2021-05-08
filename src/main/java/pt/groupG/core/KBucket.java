package pt.groupG.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KBucket {
    public static int MAX_CONTACTS = 20;
    public static int ALPHA_VALUE = 3;
    private List<Contact> contacts = new LinkedList<Contact>();
    private int depth;

    public KBucket(int depth) {
        this.depth = depth;
    }

    /**
     * If the bucket doesnt contain the node and its not full, append the node.
     * (The Contact equals isnt fully working)
     * @param nd
     */
    public void addNode(Node nd) {
        Contact aux = new Contact(nd);
        if ( (!this.contacts.contains(aux)) && this.contacts.size() < MAX_CONTACTS ) {
            this.contacts.add(aux);
        }
        else if (this.contacts.size() >= MAX_CONTACTS) {
            //recepient pings the least recently seen node in the k bucket aka in the head of the list to decide what to do.
            // ping(contacts.first())
            // if ping fails -> remove contacts.first(); add aux to tail [ contacts.add(aux) ]
            // else
            // remove contacts.first() and add to end of list; new contact is discarded.
         }
        else {
            // Dont do anything;
        }
    }


//    public List<Node> findClosestNodes(Node target) {
//        List<Node> closestNodes = new ArrayList<Node>();
//        for (Node aux : this.contacts) {
//            if (closestNodes.size() < ALPHA_VALUE) {
//                closestNodes.add(aux);
//            }
//        }
//        return closestNodes;
//    }

}
