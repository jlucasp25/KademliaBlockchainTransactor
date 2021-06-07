package pt.groupG.core;

import java.nio.charset.StandardCharsets;
import java.util.Random;


public class KademliaKey {
    public static final boolean DEBUG = false;

    public final static int MAX_KEY_SIZE = 256;
    private byte[] key;
    private String hash;

    public byte[] getKey() {
        return this.key;
    }

    public KademliaKey() {
        // TODO
        //CHANGE TO UUID
        this.key = new byte[MAX_KEY_SIZE/8];
        new Random().nextBytes(this.key);
    }

    public KademliaKey(byte[] key) {
        this.key = key;
    }

    public KademliaKey(String key) {
       this.key = getByteByString(key);
    }

    /**
     * Generates key with all 1's set.
     * -- WORKING --
     */
    public static KademliaKey generateMaxDistanceKey() {
        byte[] keyArray = new byte[MAX_KEY_SIZE/8];
        for (int i = 0; i < (MAX_KEY_SIZE/8) ; i++) {
            keyArray[i] = (byte) 0xFF;
        }
        return new KademliaKey(keyArray);
    }

    /**
     * Calculates distance between 2 nodes.
     * -- WORKING --
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
     *  -- WORKING --
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
     *      * -- WORKING -- ????
     * @return int
     */
    public int calculateDistance(KademliaKey aux) {
        /*
         * new_key <- this XOR aux
         * distance <- KEY_SIZE - new_key.first_on_bit -> prefix
         * */
        return (MAX_KEY_SIZE - this.XOR(aux).getFirstBitOn());
    }

    public boolean checkRange(KademliaKey k) {
        /* errado - é preciso ver bit a bit em vez de byte a byte.*/
        for (int i = 0; i < MAX_KEY_SIZE ; i++) {
            if (this.key[i] != k.key[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if 2 keys are equal.
     *      * -- WORKING --
     */
    public boolean equals(KademliaKey aux) {

        for (int i = 0; i < this.key.length; i++) {
            String thisBit = Integer.toBinaryString((this.key[i] & 0xFF) + 0x100).substring(1);
            String auxBit = Integer.toBinaryString((aux.key[i] & 0xFF) + 0x100).substring(1);
            if (!thisBit.equals(auxBit)) {
                return false;
            }
        }
        return true;
    }

    /**
     *      * -- WORKING --
     *
     */
    public String toString() {
        String str = "";
        for (byte b : this.key) {
            str += Integer.toBinaryString((b & 0xFF) + 0x100).substring(1);
        }
        if (DEBUG)
            System.out.println( "Key Size -> " + str.length());
        return str;
    }

    public String toHexaString() {
        return bytesToHex(this.key);
    }

    /**
     * External function to convert byte[] into Hexadecimal Strings.
     * https://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java
     */
    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
    public static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }

    /**
     * External function to convert strings into byte[].
     * https://stackoverflow.com/questions/23664078/how-do-i-convert-a-large-binary-string-to-byte-array-java/40847030#40847030
     */
    public static byte[] getByteByString(String binaryString) {
        int splitSize = 8;

        if(binaryString.length() % splitSize == 0){
            int index = 0;
            int position = 0;

            byte[] resultByteArray = new byte[binaryString.length()/splitSize];
            StringBuilder text = new StringBuilder(binaryString);

            while (index < text.length()) {
                String binaryStringChunk = text.substring(index, Math.min(index + splitSize, text.length()));
                Integer byteAsInt = Integer.parseInt(binaryStringChunk, 2);
                resultByteArray[position] = byteAsInt.byteValue();
                index += splitSize;
                position ++;
            }
            return resultByteArray;
        }
        else{
            System.out.println("Cannot convert binary string to byte[], because of the input length. '" +binaryString+"' % 8 != 0");
            return null;
        }
    }
}
