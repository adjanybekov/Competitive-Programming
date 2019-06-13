import java.util.*;

public class Block {

    public String hash;

    public String previousHash;

    private String data;

    private long timeStamp;

    //Block Constructor.

    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        data
        );
        return calculatedhash;
    }

    public Block(String data,String previousHash ) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();

    }
}
