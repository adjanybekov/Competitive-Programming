#include <iostream>
#include <stdio.h>
using namespace std;
int main() {
  
        int n,m;

        cin>>n>>m;
        long Days[n] ;
        //int len=0;
        long Res[n];
        long prev = 0;
        string out = "";
        for (int i=0; i<n; i++){
            cin>>Days[i];
            Res[i] = (Days[i]+prev)/m;
            prev=(Days[i]+prev)%m;
            printf("%d ",Res[i]);
            //len+=Names[i];
        }
        printf("\n");
}