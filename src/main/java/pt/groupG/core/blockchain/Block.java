package pt.groupG.core.blockchain;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Block {
        private String hash;
        private String previousHash;
        private String data;
        private long timeStamp; //timestamp of the creation of this block
        private int nonce;

        public Block(String data, String previousHash, long timeStamp) {
            this.data = data;
            this.previousHash = previousHash;
            this.timeStamp = timeStamp;
            this.hash = calculateBlockHash();
        }

        public String mineBlock(int prefix) {
            String prefixString = new String(new char[prefix]).replace('\0', '0');
            while (!hash.substring(0, prefix)
                    .equals(prefixString)) {
                nonce++;
                hash = calculateBlockHash();
            }
            return hash;
        }

        public String calculateBlockHash() {
            String dataToHash = previousHash
                    + Long.toString(timeStamp)
                    + Integer.toString(nonce)
                    + data; // concatenating different parts of the block to generate a hash from

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

        public String getHash() {
            return this.hash;
        }

        public String getPreviousHash() {
            return this.previousHash;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
