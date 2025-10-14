import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ohwootaek {
    static boolean visited[];
    static Node tree[];
    static int result[];

    static class Node{
        int to;
        Node next;
        Node(int to, Node next){
            this.to = to;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        tree = new Node[N + 1];
        visited = new boolean[N + 1];
        result = new int[N + 1];

        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            tree[from] = new Node(to, tree[from]);
            tree[to] = new Node(from, tree[to]);
        }

        visited[1] = true;
        dfs(1);

        for(int i = 2; i <= N; i++){
            bw.write(result[i] + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static void dfs(int now) {
        for(Node temp = tree[now]; temp != null; temp = temp.next){
            if(!visited[temp.to]){
                visited[temp.to] = true;
                result[temp.to] = now; //여기서 자식 자리에 부모 노드의 번호를 넣어준다
                dfs(temp.to);
            }
        }
    }
}
