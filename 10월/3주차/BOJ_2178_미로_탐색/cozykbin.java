package 실버박살;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class M_2178_미로탐색 {

	private static int N;
	private static int M;
	private static String[] input;
	private static int[][] arr;
	private static boolean[][] isVisited;
	private static int answer;
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		input = in.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		isVisited = new boolean[N][M];
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = in.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}

		answer = 0;
		answer = bfs(0, 0, 1);
		System.out.println(answer);

	}

	private static int bfs(int startX, int startY, int level) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { startX, startY, level });
		isVisited[startX][startY] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int currX = curr[0];
			int currY = curr[1];
			int currLevel = curr[2];

			if (currX == N - 1 && currY == M - 1) {
				return currLevel;
			}

			for (int i = 0; i < 4; i++) {
				int nextX = currX + dx[i];
				int nextY = currY + dy[i];

				if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M) {

					if (arr[nextX][nextY] == 1 && !isVisited[nextX][nextY]) {
						q.offer(new int[] { nextX, nextY, currLevel + 1 });
						isVisited[nextX][nextY] = true;
					}
				}

			}
		}
		return level;
	}
}
