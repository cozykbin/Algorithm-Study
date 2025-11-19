import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static int[][] visited;

	public static class Node {
		int r, c, dist;

		public Node(int r, int c, int dist) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, M;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new int[N][M];
		ArrayDeque<Node> q = new ArrayDeque<Node>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < M; c++) {
				int in = Integer.parseInt(st.nextToken());
				if (in == 1) {
					q.add(new Node(r, c, 1));
					visited[r][c] = 1;
				}
			}

		}

		int max = 0;
		while (!q.isEmpty()) {
			Node crt = q.poll();

			// 다음 탐색할 곳 찾기
			for (int i = 0; i < 8; i++) {
				int nxt_r = crt.r + dy[i];
				int nxt_c = crt.c + dx[i];

				if (0 > nxt_r || nxt_r >= N || 0 > nxt_c || nxt_c >= M)
					continue;

				if (visited[nxt_r][nxt_c] == 0) {
					visited[nxt_r][nxt_c] = crt.dist + 1;
					q.offer(new Node(nxt_r, nxt_c, visited[nxt_r][nxt_c]));
					
					max = Math.max(max, visited[nxt_r][nxt_c]);
				}
			}
		}
		
		System.out.println(max-1);

	}
}
