import java.util.*;
import java.lang.*;

public class Main {
  public static Scanner in ;

  public static int solve(int n,int m){
      int res=0;
      int arr[][] = new int[n][m];

      for(int i=0; i<n; i++){
        char ch[] = in.next().toCharArray();
        for(int j=0; j<m; j++){
          arr[i][j]=ch[j]-'a'+1;
        }
      }

      return res;
  }

  public static void main(String[] args) {
    

    in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    
    System.out.println(solve(n,m));
    
  }
}
