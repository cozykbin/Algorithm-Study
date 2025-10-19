package samsung01;

import java.io.*;
import java.util.*;

public class Sonjuyeong {

	static class Node {
		int r;
		int c;
		int cnt;

		public Node(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		char[][] arr = new char[n][m];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j);
			}
		}

		System.out.println(bfs(arr, n, m));
	}

	static int bfs(char[][] arr, int n, int m) {
		Queue<Node> q = new ArrayDeque<>();
		int result = 1;
		q.offer(new Node(0, 0, 1));
		arr[0][0] = '0';

		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int r = cur.r + dr[i];
				int c = cur.c + dc[i];
				int cnt = cur.cnt + 1;
				if (r >= 0 && r < n && c >= 0 && c < m && arr[r][c] == '1') {
					if (r == n - 1 && c == m - 1)
						return cnt;
					arr[r][c] = '0';
					q.offer(new Node(r, c, cnt));
				}
			}
		}

		return result;
	}
}
