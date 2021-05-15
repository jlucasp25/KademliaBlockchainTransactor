package pt.groupG.core;

import java.util.Random;

public class KademliaKey {
    public final static int MAX_KEY_SIZE = 256;
    private byte[] key;
    private String hash;

    public byte[] getKey() {
        return this.key;
    }

    public KademliaKey() {
        this.key = new byte[MAX_KEY_SIZE/8];
        new Random().nextBytes(this.key);
    }

    public KademliaKey(byte[] key) {
        this.key = key;
    }

    public static KademliaKey generateMaxDistanceKey() {
        byte[] keyArray = new byte[MAX_KEY_SIZE/8];
        for (int i = 0; i < (MAX_KEY_SIZE/8) - 1 ; i++) {
            keyArray[i] = (byte) 1;
        }
        return new KademliaKey(keyArray);
    }

    /**
     * Calculates distance between 2 nodes.
     * @param aux
     * @return KademliaKey
     */
    public KademliaKey XOR(KademliaKey aux) {
        byte[] result = new byte[MAX_KEY_SIZE/8];
        byte[] auxBytes = aux.getKey();
        for (int i = 0; i < (MAX_KEY_SIZE/8); i++) {
            result[i] = (byte) (this.key[i] ^ auxBytes[i]);
        }

        return new KademliaKey(result);
    }

    /**
     * Calculates the position of first bit on the key as 1.
     * |OR|
     * The number of leading zeros on the key.
     * Useful to calculate distances.
     * @return int
     */
    public int getFirstBitOn() {
        int prefix = 0;

        for (byte aux : this.key) {
            if (aux == 0) {
                prefix += 8;
            }
            else {
                int onBits = 0;
                for (int i = 7; i >= 0; i--) {
                    /* This condition returns true if the bit is 0. */
                    boolean a = (aux & (1 << i)) == 0;
                    if (a) {
                        onBits++;
                    }
                    else {
                        break;
                    }
                }

                prefix += onBits;
                break; // because there's a 1 inside, we need to exit the outside loop.
            }
        }
        return prefix;
    }

    /**
     * Calculates the distance between two keys and returns it as integer.
     * @return int
     */
    public int calculateDistance(KademliaKey aux) {
        /*
        * new_key <- this XOR aux
        * distance <- KEY_SIZE - new_key.first_on_bit -> prefix
        * */
        return (MAX_KEY_SIZE - XOR(aux).getFirstBitOn());
    }

    public boolean equals(KademliaKey aux) {
        for (int i = 0; i < this.key.length; i++) {
            if (this.key[i] != aux.key[i])
                return true;
        }
        return false;
    }
}
