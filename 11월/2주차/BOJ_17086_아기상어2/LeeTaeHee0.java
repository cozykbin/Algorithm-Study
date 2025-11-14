import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17086_아기상어2 {

    private static int N;  // 공간의 크기
    private static int M;  // 공간의 크기
    private static int[][] map;  // 공간 저장 배열
    private static int[][] dist;  // 거리 저장 배열
    private static int ans;  // 안전거리의 최대값

    // 탐색 방향 (8방향)
    private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    
    // 상어부터 출발해서 거리 저장
    private static void bfs(Queue<int[]> q) {
        boolean[][] visited = new boolean[N][M];

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            if (visited[x][y]) continue;
            visited[x][y] = true;

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;  // 범위 체크
                if (visited[nx][ny]) continue;  // 방문여부 체크
                
                // 빈칸이고 아무 상어에게도 도달된 적 업으면 거리 + 1
                if (map[nx][ny] == 0 && dist[nx][ny] == 0) {
                    dist[nx][ny] = dist[x][y] + 1;
                }
                // 다음칸이 상어 위치라면 0 저장인데, 지금 보니 굳이 필요 없을 듯?
//                else if (map[nx][ny] == 1 && dist[nx][ny] == 0) {
//                    dist[nx][ny] = 0;
//                }
                q.offer(new int[]{nx, ny});
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dist = new int[N][M];

        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {  // 상어 위치 큐에 담아두기
                    q.offer(new int[]{i, j});
                    dist[i][j] = 0;
                }
            }
        }

        bfs(q);

        // 안전거리 최대값 갱신
        ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, dist[i][j]);
            }
        }

        System.out.println(ans);
    }
}
