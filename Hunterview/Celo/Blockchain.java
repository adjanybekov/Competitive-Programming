import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;

public class celo2 {


    public static String sha1(String text) {
        String sha1 = "";
        try
        {
            java.security.MessageDigest crypt = java.security.MessageDigest.getInstance("SHA-1");
            crypt.update(text.getBytes("UTF-8"));
            Formatter formatter = new Formatter();
            for (byte b : crypt.digest()) {
                formatter.format("%02x", b);
            }
            sha1 = formatter.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return sha1;
    }

    public static int[][] removeInvalidTransactions(int[][] pendingTransactions,int[] startBalances){
        ArrayList<Integer[]> list = new ArrayList<>();

        for(int i=0; i<pendingTransactions.length; i++){
            int from = pendingTransactions[i][0];
            int to = pendingTransactions[i][1];
            int val = pendingTransactions[i][2];

            if(startBalances[from]-val<0){//invalid
                continue;
            }else{
                startBalances[from]-=val;
                startBalances[to]+=val;
                Integer[] arr =new Integer[3];
                for(int j=0; j<3; j++){
                    arr[j] = pendingTransactions[i][j];
                }
                list.add(arr);
            }
        }

        int[][] res = new int[list.size()][3];

        for (int i=0; i<list.size(); i++){
            for (int j=0; j<3; j++){
                res[i][j] = list.get(i)[j];
            }
        }
        return res;

    }



    public static String getLatestBlock(int[] startBalances, int[][] pendingTransactions, int blockSize) {

        int[][] validTransactions = removeInvalidTransactions(pendingTransactions,startBalances);

        String prevHash = "0000000000000000000000000000000000000000";
        int nonce =0;
        String hash ="" ;
        int[][] transaction ;// =  Arrays.copyOfRange(validTransactions, 0, blockSize);

        int i=0;
        Block block = null;
        for(; i+blockSize<validTransactions.length; i+=blockSize){

            transaction =  Arrays.copyOfRange(validTransactions, i, i+blockSize);
            String transStr = Arrays.deepToString(transaction);

            for(int j=0; j<Integer.MAX_VALUE; j++){
                String s = sha1(prevHash+", "+ j +", " +transStr);
                if(s.substring(0,4).equals("0000")){
                    nonce = j;
                    hash = s;
                    break;
                }
            }

            block = new Block(nonce,hash,prevHash,transaction );
            prevHash = hash;
        }

        if(i<validTransactions.length){

            transaction =  Arrays.copyOfRange(validTransactions, i, validTransactions.length);
            String transStr = Arrays.deepToString(transaction);

            for(int j=0; j<Integer.MAX_VALUE; j++){
                String s = sha1(prevHash+", "+ j +", " +transStr);
                if(s.substring(0,4).equals("0000")){
                    nonce = j;
                    hash = s;
                    break;
                }
            }
            block = new Block(nonce,hash,prevHash,transaction);
        }

        String res = blockToString(block);

        return res;
    }

    private static String blockToString(Block first) {
        if(first==null) return "0000000000000000000000000000000000000000";
        String res = first.hash+", "+ first.prevHash+", "+first.nonce+", "+ Arrays.deepToString(first.transactions);
        return res;
    }

    public static void main(String[] args) {
            int[][] transactions = {{0,1,5},{1,2,5},{0,2,2},{1,0,4}};
            int[] startingBalances = {10,10,10};
            int blockSize = 2;

            String res = getLatestBlock(startingBalances,transactions,blockSize);
            System.out.println(res);

//00000d03a1ce56a06bfdbceb0249bbb2204a6f22
    }


    static class Block{
        int nonce;
        String hash;
        String prevHash;
        int[][] transactions;

        public Block(int nonce, String hash, String prevHash, int[][] transactions) {
            this.nonce = nonce;
            this.hash = hash;
            this.prevHash = prevHash;
            this.transactions = transactions;
        }

        public Block() {
        }
    }
//Took me extra hour to code, after overtime, but I finished it :)
}
