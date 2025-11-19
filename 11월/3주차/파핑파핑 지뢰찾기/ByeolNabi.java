import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Solution {
	public static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static char[][] map;
	static int[][] cnts;
	static boolean[][] visited;
	static int clicked;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc;
		tc = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= tc; testcase++) {

			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			cnts = new int[N][N];
			visited = new boolean[N][N];
			clicked = 0;

			for (int r = 0; r < N; r++) {
				String s = br.readLine();
				for (int c = 0; c < N; c++) {
					char ch = s.charAt(c);
					if (ch == '.') {
						map[r][c] = '.';
					} else {
						map[r][c] = '*';
						for (int i = 0; i < 8; i++) {
							int nxtR = r + dy[i];
							int nxtC = c + dx[i];

							if (0 <= nxtR && nxtR < N && 0 <= nxtC && nxtC < N) {
								++cnts[nxtR][nxtC];
							}
						}
					}
				}
			}

			// 0인 부분 탐색하기
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (visited[r][c] == false && map[r][c] == '.' && cnts[r][c] == 0) { // 하.... 폭탄이면서 cnt가 0인 곳 찾고있었어 띠
						bfs(r, c);
						++clicked;
					}
				}
			}
			
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(visited[i]));
//			}
			
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if(visited[r][c] == false && map[r][c] == '.') {
						++clicked;
						
					}
				}
			}
			


			//			for (int i = 0; i < N; i++) {
			//				System.out.println(Arrays.toString(visited[i]));
			//			}
			//			for (int i = 0; i < N; i++) {
			//				System.out.println(Arrays.toString(cnts[i]));
			//			}

			sb.append("#" + testcase + " " + clicked + "\n");

		}
		System.out.println(sb);
	}

	static public int bfs(int r, int c) {
		ArrayDeque<Node> q = new ArrayDeque<Node>();
		q.offer(new Node(r, c));
		visited[r][c] = true;

		while (!q.isEmpty()) {
			Node crt = q.poll();

			for (int i = 0; i < 8; i++) {
				int nxtR = crt.r + dy[i];
				int nxtC = crt.c + dx[i];

				// 경기장 안이면서
				if (0 <= nxtR && nxtR < N && 0 <= nxtC && nxtC < N && visited[nxtR][nxtC] == false) {
					// 0인 부분이면
					if (cnts[nxtR][nxtC] == 0) {
						q.offer(new Node(nxtR, nxtC));
					}

					visited[nxtR][nxtC] = true;
				}
			}
		}

		return c;
	}
}

/*
 
2
3
..*
..*
**.
5
..*..
..*..
.*..*
.*...
.*...

*/
