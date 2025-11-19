import java.io.*;
import java.util.*;

public class Sonjuyeong {

    static int[] dr = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dc = {1, 1, 0, -1, -1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            int N = Integer.parseInt(br.readLine());
            char[][] arr = new char[N][N];
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    arr[i][j] = str.charAt(j);
                }
            }
            int answer = 0;
            boolean[][] visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == '.' && !visited[i][j]) {
                        boolean hasBomb = false;
                        for (int k = 0; k < 8; k++) {
                            int r = i + dr[k];
                            int c = j + dc[k];
                            if (r >= 0 && r < N && c >= 0 && c < N && arr[r][c] == '*') {
                                hasBomb = true;
                                break;
                            }
                        }
                        if (!hasBomb) {
                            ++answer;
                            visited[i][j] = true;
                            Queue<int[]> q = new ArrayDeque<>();
                            q.offer(new int[]{i, j});
                            while (!q.isEmpty()) {
                                int[] cur = q.poll();
                                for (int k = 0; k < 8; k++) {
                                    int r = cur[0] + dr[k];
                                    int c = cur[1] + dc[k];
                                    if (r < 0 || r >= N || c < 0 || c >= N) continue;
                                    if (visited[r][c]) continue;
                                    hasBomb = false;
                                    for (int l = 0; l < 8; l++) {
                                        int newR = r + dr[l];
                                        int newC = c + dc[l];
                                        if (newR >= 0 && newR < N && newC >= 0 && newC < N && !visited[newR][newC]) {
                                            if (arr[newR][newC] == '*') {
                                                hasBomb = true;
                                                break;
                                            }
                                        }
                                    }
                                    visited[r][c] = true;
                                    if (!hasBomb) {
                                        q.offer(new int[]{r, c});
                                    }
                                }
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && arr[i][j] == '.')
                        ++answer;
                }
            }

            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
