//Initial Template for Java
import java.util.*;
import java.io.*;
import java.lang.*;
class Main
{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-- > 0)
        {
            int N = sc.nextInt();
            int M = sc.nextInt();

            ArrayList<ArrayList<Integer>> list = new ArrayList<>(N);

            // creating arraylist of arraylist
            for(int i = 0; i < N; i++)
            {
                ArrayList<Integer> temp = new ArrayList<>(M);
                list.add(i, temp);
            }

            // adding elements to the arraylist of arraylist
            for(int i = 0; i < N; i++)
            {
                for(int j = 0; j < M; j++)
                {
                    int val = sc.nextInt();
                    list.get(i).add(j, val);
                }
            }


            System.out.println(new Islands().findIslands(list, N, M));

        }
    }


    static class Islands
    {

        static void print(ArrayList<ArrayList<Integer>> list){
            for(int i=0; i<list.size(); i++){
                for(int j=0; j<list.get(0).size(); j++){
                    System.out.printf(" %d ",list.get(i).get(j));
                }
                System.out.println();
            }
            System.out.println();
        }

        // Function to find the number of island in the given list
        // N, M: size of list row and column respectively
        static int findIslands(ArrayList<ArrayList<Integer>> list, int N, int M)
        {
            int res=0;

            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(list.get(i).get(j)==1){
                        bfs(i,j,list);//set visited to -1
                        res++;
                    }
                }
            }
            return res;
        }


        static void bfs(int i, int j, ArrayList<ArrayList<Integer>> list){
            //replace 1 with -1 which are neighbour
            int N = list.size(),M = list.get(0).size();

            Queue<Point> q = new LinkedList<>();
            Point first = new Point(i,j);
            q.add(first);


            while(!q.isEmpty()){

                Point p = q.poll();
                list.get(p.x).set(p.y,-1);
                //look at its nbrs and add to q if they are also 1

                //up
                if(p.x-1>=0 && (list.get(p.x-1).get(p.y)==1)){
                    Point n = new Point(p.x-1,p.y);
                    list.get(n.x).set(n.y,-1);
                    q.add(n);
                }//down
                if(p.x+1<N && (list.get(p.x+1).get(p.y)==1)){
                    Point n = new Point(p.x+1,p.y);
                    list.get(n.x).set(n.y,-1);
                    q.add(n);
                }//left
                if(p.y-1>=0 && (list.get(p.x).get(p.y-1)==1)){
                    Point n = new Point(p.x,p.y-1);
                    list.get(n.x).set(n.y,-1);
                    q.add(n);
                }//right
                if(p.y+1<M && (list.get(p.x).get(p.y+1)==1)){
                    Point n = new Point(p.x,p.y+1);
                    list.get(n.x).set(n.y,-1);
                    q.add(n);
                }//left up
                if(p.x-1>=0 && p.y-1>=0  && (list.get(p.x-1).get(p.y-1)==1)){
                    Point n = new Point(p.x-1,p.y-1);
                    list.get(n.x).set(n.y,-1);
                    q.add(n);
                }//right up
                if(p.x-1>=0 && p.y+1<M  && (list.get(p.x-1).get(p.y+1)==1)){
                    Point n = new Point(p.x-1,p.y+1);
                    list.get(n.x).set(n.y,-1);
                    q.add(n);
                }//left down
                if(p.x+1<N && p.y-1>=0  && (list.get(p.x+1).get(p.y-1)==1)){
                    Point n = new Point(p.x+1,p.y-1);
                    list.get(n.x).set(n.y,-1);
                    q.add(n);
                }//right down
                if(p.x+1<N && p.y+1<M  && (list.get(p.x+1).get(p.y+1)==1)){
                    Point n = new Point(p.x+1,p.y+1);
                    list.get(n.x).set(n.y,-1);
                    q.add(n);
                }
                print(list);
            }
        }

        static class Point{
            int x;
            int y;
            Point(int X,int Y){
                x=X;
                y=Y;
            }
        }
    }
}


/*This is a function problem.You only need to complete the function given below*/
//User function Template for Java https://practice.geeksforgeeks.org/problems/find-the-number-of-islands/1

