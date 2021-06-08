package pt.groupG.core.blockchain;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Block {
        public String hash;
        public String previousHash;
        public String merkleTree;
        private long timeStamp; //timestamp of the creation of this block
        private int nonce;

        public List<String> trans = new LinkedList<String>();

        public Block(String previousHash, long timeStamp) {
            this.previousHash = previousHash;
            this.timeStamp = timeStamp;
            this.hash = calculateBlockHash();
        }

        public Block (String hash, String previousHash, String merkelTree, List<String> trans, long timeStamp, int nonce) {
            this.hash = hash;
            this.previousHash = previousHash;
            this.merkleTree = merkelTree;
            this.trans = trans;
            this.timeStamp = timeStamp;
            this.nonce = nonce;
        }

        public Block (List<String> trans) {
            this.trans = trans;
        }

        public void mineBlock(int dif) {
            // merkleTree = getMerleRoot(ver funçao)
            String prefixString = new String(new char[dif]).replace('\0', '0');
            while (!hash.substring(0, dif)
                    .equals(prefixString)) {
                nonce++;
                hash = calculateBlockHash();
            }
            System.out.println("Block mined successfully:  " + hash);
        }

        public String calculateBlockHash() {
            String dataToHash = previousHash
                    + Long.toString(timeStamp)
                    + Integer.toString(nonce)
                    + merkleTree; // concatenating different parts of the block to generate a hash from

            MessageDigest digest = null;
            byte[] bytes = null;

            Logger logger = Logger.getLogger(Block.class.getName());

            try {
                digest = MessageDigest.getInstance("SHA-256"); //get an instance of the SHA-256 hash function from MessageDigest
                bytes = digest.digest(dataToHash.getBytes("UTF-8")); // generating the hash value of our input data, which is a byte array
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                logger.log(Level.SEVERE, ex.getMessage());
            }
            StringBuffer buffer = new StringBuffer();
            for (byte b : bytes) {
                buffer.append(String.format("%02x", b)); //transforming the byte array into a hex string, a hash is typically represented as a 32-digit hex number
            }
            return buffer.toString();
        }

        public void addTransaction (String newtrans) {
            //ver se a hash anterior corresponde
            if(Blockchain.HashValidator()) {
                trans.add(newtrans);
                System.out.println("New Transaction added to Block.");
            }
        }
    }

