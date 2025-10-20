import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_7576_토마토 {
    static int tomato[][];
    static int N, M;
    static boolean visited[][];
    //static Queue<int[]> ripe_tomato;
    static Queue<Node> ripe_tomato;
    static int e_time = 0;
    static int mx[] = {-1, 1, 0, 0};
    static int my[] = {0, 0, -1, 1};

    static class Node{
        int x, y, time;

        Node(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        tomato = new int[N][M];
        ripe_tomato = new ArrayDeque<>();
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                tomato[i][j] = Integer.parseInt(st.nextToken());

                if(tomato[i][j] == 1){
                    ripe_tomato.offer(new Node(i, j, 0));
                }
            }
        }

        bfs(ripe_tomato);

        boolean flag = true;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(tomato[i][j] == 0){
                    flag = false;
                }
            }
        }

        if(!flag){
            bw.write("-1");
        }else{
            bw.write(e_time + "");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static void bfs(Queue<Node> ripeTomato) {
        Queue<Node> queue = new ArrayDeque<>();
        for(Node tmp : ripeTomato) {
            queue.offer(tmp);
            visited[tmp.x][tmp.y] = true;
        }//일단 모든 좌표를 넣어주고

        while(!queue.isEmpty()) {
            Node gets = queue.poll();
            int time = gets.time;

            e_time = Math.max(e_time, time);

            for(int i = 0; i < 4; i++){
                int nx = gets.x + mx[i];
                int ny = gets.y + my[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M
                        || visited[nx][ny] || tomato[nx][ny] == -1)
                    continue;

                if(tomato[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    //e_time = Math.max(e_time, time + 1);
                    tomato[nx][ny] = 1;
                    queue.offer(new Node(nx, ny, time + 1));
                }
            }
        }

    }
}
 