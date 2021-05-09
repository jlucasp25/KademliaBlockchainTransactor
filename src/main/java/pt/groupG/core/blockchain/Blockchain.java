package pt.groupG.core.blockchain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class Blockchain {
    public static List<Block> blocks = new ArrayList<Block>();
    public static int prefix = 4;
    public static String prefixString = new String(new char[prefix]).replace('\0', '0');

    @BeforeClass
    public static void setUp() {
        Block genesisBlock = new Block("Genesis Block.", "0", new Date().getTime());
        genesisBlock.mineBlock(prefix);
        blocks.add(genesisBlock);
        Block firstBlock = new Block("First Block.", genesisBlock.getHash(), new Date().getTime());
        firstBlock.mineBlock(prefix);
        blocks.add(firstBlock);
    }

    @Test
    public void givenBlockchain_whenNewBlockAdded_thenSuccess() {
        Block newBlock = new Block("New Block.", blocks.get(blocks.size() - 1).getHash(), new Date().getTime());
        newBlock.mineBlock(prefix);
        assertTrue(newBlock.getHash()
                .substring(0, prefix)
                .equals(prefixString));
        blocks.add(newBlock);
    }

    @Test
    public void givenBlockchain_whenValidated_thenSuccess() {
        boolean flag = true;
        for (int i = 0; i < blocks.size(); i++) {
            String previousHash = i == 0 ? "0"
                    : blocks.get(i - 1)
                    .getHash();
            flag = blocks.get(i)
                    .getHash()
                    .equals(blocks.get(i)
                            .calculateBlockHash())
                    && previousHash.equals(blocks.get(i)
                    .getPreviousHash())
                    && blocks.get(i)
                    .getHash()
                    .substring(0, prefix)
                    .equals(prefixString);
            if (!flag)
                break;
        }
        assertTrue(flag);
    }

    @AfterClass
    public static void tearDown() {
        System.out.println(blocks);
        blocks.clear();
    }

}
