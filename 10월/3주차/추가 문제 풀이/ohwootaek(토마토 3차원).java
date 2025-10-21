import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569_토마토 {
    static int N, M, H;
    static int[][][]tomato_box;
    static int mx[] = {-1, 1, 0, 0, 0, 0};
    static int my[] = {0, 0, -1, 1, 0, 0};
    static int mh[] = {0, 0, 0, 0, -1, 1};
    static int result = 0;

    static class Node{
        int x, y, h, time;

        Node(int x, int y, int h, int time){
            this.x = x;
            this.y = y;
            this.h = h;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        Queue<Node> q = new ArrayDeque<>();

        tomato_box = new int[N][M][H];

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    //int v = Integer.parseInt(st.nextToken());
                    tomato_box[n][m][h] = Integer.parseInt(st.nextToken());

                    if(tomato_box[n][m][h] == 1){
                        q.offer(new Node(n, m, h, 0));
                    }
                }
            }
        }

        bfs(q);

        boolean flag = true;
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if(tomato_box[n][m][h] == 0){
                        flag = false;
                    }
                }
            }
        }

        if(!flag){
            bw.write("-1");
        }else{
            bw.write(result + "");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static void bfs(Queue<Node> ripe) {
        Queue<Node> queue = new ArrayDeque<>();
        for(Node now : ripe){
            queue.offer(now);
        }

        while(!queue.isEmpty()){
            Node cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            int h = cur.h;
            int chtime = cur.time;

            result = Math.max(result, chtime);

            for(int i = 0; i < 6; i++){
                int nx = x + mx[i];
                int ny = y + my[i];
                int nh = h + mh[i];
                int time = cur.time;

                if(nx < 0 || ny < 0 || nh < 0
                        || nx >= N || ny >= M || nh >= H
                        || tomato_box[nx][ny][nh] == 1 || tomato_box[nx][ny][nh] == -1){
                    continue;
                }

                if(tomato_box[nx][ny][nh] == 0){
                    tomato_box[nx][ny][nh] = 1;
                    queue.offer(new Node(nx, ny, nh, time + 1));
                }
            }
        }
    }
}
