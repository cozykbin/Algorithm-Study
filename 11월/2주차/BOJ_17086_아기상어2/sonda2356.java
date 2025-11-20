package Silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class S17086 {
    public static int N, M;
    public static int[][] map;
    public static int[][] dist;

    public static int[] dx = {-1,-1,-1, 0, 1, 1, 1, 0};
    public static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dist = new int[N][M];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(in.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) {
                    q.offer(new int[]{r, c});
                    dist[r][c] = 0;
                } else {
                    dist[r][c] = -1;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int d = 0; d < 8; d++) {
                int x = r + dx[d];
                int y = c + dy[d];

                if (x < 0 || x >= N || y < 0 || y >= M) continue;
                if (dist[x][y] != -1) continue; // 이미 더 가까운 상어에서 방문됨

                dist[x][y] = dist[r][c] + 1;
                q.offer(new int[]{x, y});
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dist[i][j] > ans) ans = dist[i][j];
            }
        }

        System.out.println(ans);
    }
}
