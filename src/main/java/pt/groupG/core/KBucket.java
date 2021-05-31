package pt.groupG.core;

import pt.groupG.grpc.EmptyMessage;

import java.util.LinkedList;
import java.util.List;

public class KBucket {
    /**
     * KBucket
     * Contains a list of contacts for the current bit of the key space.
     */
    public static int MAX_CONTACTS = 20;
    public static int ALPHA_VALUE = 3;
    public List<Contact> contacts = new LinkedList<Contact>();
    public KademliaKey key = new KademliaKey();
    private int depth = 0;

    public KBucket(int depth) {
        this.depth = depth;
    }

    public KBucket(KademliaKey key, int depth) {
        this.key = key;
        this.depth = depth;
    }

    public KBucket(KademliaKey key) {
        this.key = key;
    }


    /**
     * Returns true if the k-bucket is empty.
     * @return
     */
    public boolean isEmpty() {
        return this.contacts.isEmpty();
    }

    /**
     * Adds a node to K-Bucket.
     * If the bucket doesnt contain the node and its not full, append the node.
     * Else discard it after popping the 1st element and pushing to the end of the list.
     */
    public void addNode(Node aux) {
        Contact c = Contact.fromNode(aux);
        if ( (!this.contacts.contains(c)) && this.contacts.size() < MAX_CONTACTS ) {
            this.contacts.add(c);
        }
        else if (this.contacts.size() >= MAX_CONTACTS) {
            // recepient pings the least recently seen node in the k bucket aka
            // in the head of the list to decide what to do. "ping(contacts.first())"
            Contact leastRecentlySeen = this.contacts.get(0);
            KademliaClient kc = new KademliaClient(leastRecentlySeen.getAddress(), leastRecentlySeen.getPort());
            boolean response = kc.PING(EmptyMessage.newBuilder().build());
            if (response) {
                // remove contacts.first() and add to end of list; new contact is discarded.
                this.contacts.remove(leastRecentlySeen);
                this.contacts.add(leastRecentlySeen);
            }
            else {
                // if ping fails -> remove contacts.first(); add new to tail [ contacts.add(aux) ]
                this.contacts.remove(leastRecentlySeen);
                this.contacts.add(c);
            }
         }
    }

    public String toString() {
        if (this.contacts.size() != 0) {
            return "[+] K-Bucket w/Depth " + this.depth + " | Number of Contacts: " + this.contacts.size();
        }
        return null;
    }
}
