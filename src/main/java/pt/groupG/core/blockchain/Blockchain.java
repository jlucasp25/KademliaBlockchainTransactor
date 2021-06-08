package pt.groupG.core.blockchain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Blockchain {
    public static List<Block> blocks = new LinkedList<Block>();
    public static int dif = 4;
    public static String prefixString = new String(new char[dif]).replace('\0', '0');
    //public static Wallet wallet = new Wallet();


    public static Boolean HashValidator() {
        for(int i=1; i<blocks.size(); i++) {
            Block previous = blocks.get(i-1);
            Block current = blocks.get(i);
            if(!current.hash.equals(current.calculateBlockHash()) || !previous.hash.equals(current.previousHash)) {
                System.out.println("Hashes does not match.");
                return false;
            }
        }
        return true;
    }

    public void newBlock(Block newblock) {
        //if(HashValidator()) {
        newblock.mineBlock(dif);
        blocks.add(newblock);
        System.out.println("New Block added.");
        //}
    }

}


