import java.io.*;
import java.util.*;

public class Sonjuyeong {

    static class Node{
        int weight;
        int pos;
        public Node(int weight, int pos){
            this.weight = weight;
            this.pos = pos;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int start = 0, end = 0;
        Node[] arr = new Node[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0;i<n;i++){
            int t = Integer.parseInt(st.nextToken());
            arr[i] = new Node(t, 0);
        }

        int sec = 1;
        int weight = arr[start].weight;
        arr[start].pos=1;

        while(start<n){
            ++sec;
            for(int i = start;i<=(end<n?end:n-1);i++){
                ++arr[i].pos;
            }
            if(arr[start].pos==w+1){
                weight-=arr[start].weight;
                ++start;
                if(start==n) break;
            }
            if(end<n-1&&arr[end].pos>1&&arr[end+1].weight+weight<=l){
                ++end;
                ++arr[end].pos;
                weight+=arr[end].weight;
            }
        }
        System.out.println(sec);
    }
}