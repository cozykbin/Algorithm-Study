import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class 파핑파핑지뢰찾기 {

    private static int T;
    private static int N;
    private static char[][] map;
    private static boolean[][] visited;
    private static int[][] count;
    private static int ans;

    // 8방향
    private static final int[] dx = {1, 1, 1, 0, 0, -1, -1, -1};
    private static final int[] dy = {1, 0, -1, 1, -1, 1, 0, -1};

    // 지뢰가 없는 칸의 근처에 있는 지뢰 개수 계산
    private static void countMine() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
            	
                if (map[x][y] == '*') continue;
                int cnt = 0;
                
                for (int i = 0; i < 8; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    
                    if (map[nx][ny] == '*') cnt++;
                }
                
                count[x][y] = cnt;
            }
        }
    }

    // 지뢰가 아닌 덩어리 한번에 열기
    private static void bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        visited[x][y] = true;
        q.offer(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            if (count[cx][cy] == 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];
                    
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if (visited[nx][ny] || map[nx][ny] == '*') continue;

                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            map = new char[N][N];
            visited = new boolean[N][N];
            count = new int[N][N];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            // 각 칸별로 근처에 지뢰가 몇개 있는지 계싼
            countMine();

            ans = 0;

            // 근처에 지뢰가 없다면 덩어리 계산하여 열기
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (map[x][y] == '.' && !visited[x][y] && count[x][y] == 0) {
                        bfs(x, y);
                        ans++;
                    }
                }
            }

            // 아직 남아있는 지뢰 없는 칸 확인
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (map[x][y] == '.' && !visited[x][y]) {
                        ans++;
                        visited[x][y] = true;
                    }
                }
            }

            sb.append("#" + tc + " " + ans + "\n");
        }

        System.out.print(sb);
    }
}
