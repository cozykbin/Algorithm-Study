import java.io.*;
import java.util.*;

public class Sonjuyeong {

	static List<Integer> cnts;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int n;

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		char[][] arr = new char[n][n];
		cnts = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == '1') {
					bfs(i, j, arr);
				}
			}
		}

		Collections.sort(cnts);

		sb.append(cnts.size()).append("\n");
		for (int i = 0; i < cnts.size(); i++) {
			sb.append(cnts.get(i)).append("\n");
		}

		System.out.println(sb);
	}

	public static void bfs(int r, int c, char[][] arr) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(r, c));
		arr[r][c] = '0';

		int cnt = 1;

		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nextR = cur.r + dr[i];
				int nextC = cur.c + dc[i];
				if (nextR >= 0 && nextR < n && nextC >= 0 && nextC < n && arr[nextR][nextC] == '1') {
					arr[nextR][nextC] = '0';
					++cnt;
					q.offer(new Node(nextR, nextC));
				}
			}
		}

		cnts.add(cnt);
	}
}
