package solution.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S2_21736 {
	static StringTokenizer st;
	static int N, M;
	static int cnt;
	// 동 서 남 북
	static int[] dy = new int[] { 0, 0, 1, -1 };
	static int[] dx = new int[] { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[][] visited = new int[N][M];
		int[] start = new int[2];

		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = tmp.charAt(j);
				if (c == 'O') {
					map[i][j] = 0;
				} else if (c == 'X') {
					map[i][j] = 1;
				} else if (c == 'I') {
					map[i][j] = 2;
					start[0] = i;
					start[1] = j;
				} else if (c == 'P') {
					map[i][j] = 3;
				}
			}
		}

		cnt = 0;
		dfs(start[0], start[1], map, visited);

//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(visited[i]));
//		}

		System.out.println(cnt == 0 ? "TT" : cnt);
	}

	public static void dfs(int r, int c, int[][] map, int[][] visited) {
		visited[r][c] = 1;
		if (map[r][c] == 3) {
			cnt++;
		}

		for (int i = 0; i < 4; i++) {
			int nxtR, nxtC;
			nxtR = r + dy[i];
			nxtC = c + dx[i];
			if (0 <= nxtR && nxtR < N && 0 <= nxtC && nxtC < M) {
				if (visited[nxtR][nxtC] == 0) {
					if (map[nxtR][nxtC] != 1) {
						dfs(nxtR, nxtC, map, visited);
					}
				}
			}
		}
	}
}

// O : 0 (빈방)
// X : 1 (벽)
// I : 2 (도연)
// P : 3 (사람)