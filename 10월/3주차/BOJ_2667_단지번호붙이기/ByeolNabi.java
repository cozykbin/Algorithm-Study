package playground;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static class Node {
		public Node(int r2, int c2, int me2) {
			r = r2;
			c = c2;
			me = me2;
		}

		int r;
		int c;
		int me;
	}

	static int[][] map;
	static List<Integer> answer;
	static int N;
	// 동 서 남 북
	static int[] dy = new int[] { 0, 0, 1, -1 };
	static int[] dx = new int[] { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		answer = new ArrayList<Integer>();

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) { // 아파트 단지 찾았으면 탐색
					int size = bfs(i, j, map[i][j]);

					if (size != 0) {
						answer.add(size);
					}
				}
			}
		}

		Collections.sort(answer);
		System.out.println(answer.size());
		for (int a : answer) {
			System.out.println(a);
		}
	}

	public static int bfs(int r, int c, int me) {
		int cnt = 1;

		Queue<Node> q = new ArrayDeque<Node>();
		q.offer(new Node(r, c, me));
		map[r][c] = 0;

		while (!q.isEmpty()) {
			Node crt = q.poll();
			int cr = crt.r;
			int cc = crt.c;

			for (int i = 0; i < 4; i++) {
				int nr = cr + dy[i];
				int nc = cc + dx[i];

				if ((0 <= nr && nr < N && 0 <= nc && nc < N) && map[nr][nc] != 0 && map[nr][nc] == crt.me) {
					q.offer(new Node(nr, nc, me));
					map[nr][nc] = 0;
					cnt++;
				}
			}
		}

		return cnt;
	}
}
