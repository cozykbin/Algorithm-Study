import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class ohwootaek {
    static int N, M, cnt;
    static String[][]map;
    static int start_location[]; // 시작위치 담은 배열
    static boolean visited[][];
    static int mx[] = {-1, 1, 0, 0};
    static int my[] = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new String[N][M];
        visited = new boolean[N][M];
        start_location = new int[2];

        for(int i = 0; i < N; i++) {
            String split[] = br.readLine().split("");
            for(int j = 0; j < M; j++) {
                map[i][j] = split[j];

                if(map[i][j].equals("I")) {
                    start_location[0] = i;
                    start_location[1] = j;
                }
            }
        }

        cnt = 0;
        bfs(start_location);

        if(cnt == 0){
            bw.write("TT");
        }
        else{
            bw.write(cnt + "");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int[] start) { // 시작지점 넘겨 받음
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;

        while(!queue.isEmpty()) {
            int now[] = queue.poll();
            int x = now[0], y = now[1];

            for(int i = 0; i < 4; i++){
                int nx = x + mx[i];
                int ny = y + my[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny].equals("X") || visited[nx][ny]) continue;

                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    if(map[nx][ny].equals("P")) cnt++;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}





