import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static public class Coor {
		int r;
		int c;
		int dist;

		public Coor(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}

	// 동 서 남 북
	static int[] dy = new int[] { 0, 0, 1, -1 };
	static int[] dx = new int[] { 1, -1, 0, 0 };

	static char[][] map;
	static boolean[][] visited;
	static int N, M;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
//		for(int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}

		Queue<Coor> q = new ArrayDeque<Coor>();
		q.add(new Coor(0, 0, 1));
		visited[0][0] = true;

		while (!q.isEmpty()) {
			Coor crt = q.poll();

			if (crt.r == N - 1 && crt.c == M - 1) {
				System.out.println(crt.dist);
				return;
			}

			for (int i = 0; i < 4; i++) {
				Coor nxt = new Coor(crt.r + dy[i], crt.c + dx[i], crt.dist + 1);

				if (isIn(nxt) && !visited[nxt.r][nxt.c]) {
					if (map[nxt.r][nxt.c] == '1') {
						q.add(nxt);
						visited[nxt.r][nxt.c] = true;
					}
				}
			}
		}
	}

	public static boolean isIn(Coor coor) {
		int r = coor.r;
		int c = coor.c;
		if (0 <= r && r < N && 0 <= c && c < M) {
			return true;
		}

		return false;
	}
}
