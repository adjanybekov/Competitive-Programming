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

      //for width == 1
      //int lineFlag=0;
      for(int i=1; i<n-1; i++){
        //char ch[] = in.next().toCharArray();
        for(int j=0; j<m && i<n-1; j++){
          if(arr[i][j]!=arr[i-1][j]){//if cur is different than above then find l of cur length
          //System.out.println(arr[i][j]+","+arr[i-1][j]);
            int midL=0;

            while(i+midL<n && arr[i+midL][j]==arr[i][j]){
              midL++;
              //System.out.println("Mid");
            }

            //now we have midL find upL and lowL
            int upL = 0;

            while(i-upL>0 && arr[i-upL-1][j]==arr[i-1][j]){
              upL++;
              //System.out.println("Up");
            }
            int lowL=0;

            while(i+midL+lowL<n && arr[i+midL+lowL][j]==arr[i+midL][j]){
              lowL++;
              //System.out.println("Low");
            }

            if(midL<=lowL && midL<=upL){//if valid width 1 flag is found search for its maximum width
            //r,c == r,c-1 in the given range from top to bottom yani from current i-l above and i+2l below
              int w=1;
              boolean flag=false;
              //measuring the width of the found flag

              for(int c=j+1; c<m; c++){
                for(int r=i-midL; r<i+midL*2;r++ ){
                  System.out.println(r+":r,c:"+c);
                  if(arr[r][c]!=arr[r][c-1]){
                    flag=true;
                    break;
                  }
                }
                if(flag)break;
                else{ w++;}
              }
            
               System.out.println(w+":width r,c="+(i-1)+","+j);
              res+=(w*(w+1))/2;//all possible ways of dividing it into pieces
              j+=w-1;
            }

          }
        }
      }
/*
6 1
a
b
c
d
c
e

6 6
aaaadd
bbbbdd
ccccdb
ddacdb
ddaddd
dddddd

6 4
aaaa
bbbb
cccc
ddac
ddad
dddd

ok the line of len 1 is handled now we need to extend this to higer width if possible
HOw can we do that?
Ok I can count number of rows similar to curent one,
BUt I will need to mark them as visited right? Though they may later be used by other flags, so what will I do then?
*/



      return res;
  }

  public static void main(String[] args) {
    

    in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    
    System.out.println(solve(n,m));
    
  }
}
