import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11403_경로찾기 {
    static class Node{
        int to;

        Node(int to){
            this.to = to;
        }
    }

    static int N;
    //static Node[] path;
    static ArrayList<ArrayList<Node>> path;
    static int map[][];
    static int result[][];
    static boolean visited[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        path = new ArrayList<>();
        for(int i = 0; i < N; i++){
            path.add(new ArrayList<>());
        }
        result = new int[N][N];


        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] ==  1) {
                    path.get(i).add(new Node(j));
                }
            }
        }

        for(int i = 0; i < N; i++) {
            visited = new boolean[N];
            dfs(i, i); // 123순서대로 모두 넣을 예정
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                bw.write(result[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int now, int start) {
        for (Node next : path.get(now)) {
            if (!visited[next.to]) {
                visited[next.to] = true;
                result[start][next.to] = 1;
                dfs(next.to, start);
            }
        }
    }
}