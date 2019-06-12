import java.util.*;
import java.lang.*;
/*
  Now I am getting time limit on 5th test case
  probably because of remove operation
  maybe if I will use frequency array this problem will be solved))
  but if remove operation is costy then add operation is also costy, isn't it??  
  At least I implemented correct solution :)
  Now it is a matter of improving time cost from here and there :)

  Finally 12.06.2019 
  Code passed all test cases and as I undestood it was about how I check vowels :)
  I should check only characters which are vowels
  and ignore the ones which are not
  And I think, Queue has size in O(1)
  While stack has size in O(n)?
  https://codeforces.com/contest/1182/problem/C
*/
public  class Main {
  public static class Pairs{
    String s1;
    String s2;
  }

  static Scanner in;  
  static final char[] vowels = {'a','e','i','o','u'};
  private static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

  public static int vowelCount(String s){
    int count=0;
    for(int i=0;i<s.length();i++){
      if(isVowel(s.charAt(i))){
          count++;
      }
    }
    return count;
  }

  public static int lastVowel(String s){
    int lastV=0;
    for(int i=s.length()-1;i>=0;i--){      
      if(isVowel(s.charAt(i))){
        for(int j=0; j<5; j++){
          if(s.charAt(i)==vowels[j]){
            lastV=j;
            return lastV;
          }          
        }
        
      }
    }
    return lastV;
  }
  static final int LEN = 1000001;

  public static void solve(){
    //System.out.println("here");
    //int[][] vowelFrequency = new int[LEN][6];    
    HashMap<Integer,HashMap<Integer,Queue<String>>> wordsByVowels = new HashMap<>();
    

    int n = in.nextInt();
    //String[] words = new String[n];
    for(int i=0; i<n;i++){
      String s = in.next();
      //words[i] = s;
      int vowelCount = vowelCount(s);
      int lastV = lastVowel(s);//a=1,e=2,i=3,o=4,u=5
      //vowelFrequency[vowelCount][lastV]++;
      //store index of a word
      if(!wordsByVowels.containsKey(vowelCount)){
        HashMap<Integer,Queue<String>> hm  =new HashMap<>();      
        wordsByVowels.put(vowelCount,hm);
      }
      if(!wordsByVowels.get(vowelCount).containsKey(lastV)){
        wordsByVowels.get(vowelCount).put(lastV,new LinkedList<String>());
      }
      //if(wordsByVowels.get(vowelCount))
      wordsByVowels.get(vowelCount).get(lastV).add(s);
    }

    //now separate as complete 
    Queue<Pairs> complete = new LinkedList<>();
    Queue<Pairs> incomplete = new LinkedList<>();
    for(Map.Entry<Integer , HashMap<Integer,Queue<String>> > entry: wordsByVowels.entrySet()){
      HashMap<Integer,Queue<String>> lastMap = entry.getValue();
      //iterate over Strings with same number of vowels
      for(Map.Entry<Integer,Queue<String>> e: lastMap.entrySet()){
            //take stack which contains elements with similar endVowel
            Queue<String> st = e.getValue();
            while(st.size()>=2){
              Pairs p = new Pairs();
              p.s1 = st.poll();              
              p.s2 = st.poll();              
              complete.add(p); 
            }
      }
      Queue<String> remainings = new LinkedList<>();

      for(Map.Entry<Integer,Queue<String>> e: lastMap.entrySet()){
            //take stack which contains elements with similar endVowel
            Queue<String> st = e.getValue();
            if(st.size()>0){
              remainings.add(st.poll());
            }
      }
      while(remainings.size()>=2){
        Pairs p = new Pairs();
        p.s1 = remainings.poll();
        p.s2 =  remainings.poll();
        incomplete.add(p);    
      }
    }
    
    //System.out.println("here");
//now we have both complete and incomplete and we can mix them well first mix completes with incompletes then mix completes if left any
  //System.out.printf("com %d inc %d\n",complete.size(),incomplete.size());
  //System.out.printf("com %d inc %d\n",complete.size(),incomplete.size());
  int lc = 0;
  if(complete.size()>incomplete.size()){
      lc=incomplete.size()+ (complete.size()-incomplete.size())/2;
  }else{
    lc = complete.size();
  }

  System.out.println(lc);
  

  while(complete.size()>0 && incomplete.size()>0){
    Pairs inc =incomplete.poll();
    Pairs com =complete.poll();
    System.out.printf("%s %s\n%s %s\n",inc.s1,com.s1,inc.s2,com.s2);
  }

  while(complete.size()>=2){
    Pairs com =complete.poll();
    Pairs com2 =complete.poll();
    System.out.printf("%s %s\n%s %s\n",com2.s1,com.s1,com2.s2,com.s2);
  }
    



  }


  public static void main(String[] args) {
    in = new Scanner(System.in);
    solve();
  }
}
