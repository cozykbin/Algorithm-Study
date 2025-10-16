import java.io.*;
import java.util.*;

public class Sonjuyeong {

    static void dfs(int node, List<Integer>[] arr, int[] parent, boolean[] visited){
        for(int i = 0;i<arr[node].size();i++){
            int cur = arr[node].get(i);
            if(!visited[cur]){
                visited[cur]=true;
                parent[cur]=node;
                dfs(cur, arr, parent, visited);
            }

        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer>[] arr = new ArrayList[n+1];
        int[] parent = new int[n + 1];

        for(int i = 1;i<=n;i++){
            parent[i] = i;
            arr[i] = new ArrayList<>();
        }

        for(int i = 0; i < n-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            arr[n1].add(n2);
            arr[n2].add(n1);
        }

        dfs(1, arr, parent, new boolean[n+1]);

        for(int i = 2;i<=n;i++){
            System.out.println(parent[i]);
        }

    }
}