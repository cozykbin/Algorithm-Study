package 실버박살;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Doyeon {
	int x, y, m;

	Doyeon(int x, int y, int m) {
		this.x = x;
		this.y = y;
		this.m = m;
	}
}
public class M_21736_헌내기는친구가필요해 {

	private static int N, M, answer;
	private static int[] x = { 0, 0, 1, -1 };
	private static int[] y = { 1, -1, 0, 0 };
	private static int[][] map;
	private static boolean[][] isVisited;
	static Doyeon doyeon = new Doyeon(0, 0, 0);

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		answer = 0;

		map = new int[M + 1][N + 1];
		isVisited = new boolean[M + 1][N + 1];

		int startX = 0;
		int startY = 0;

		for (int i = 0; i < M; i++) {
			String input = in.readLine();
			char[] dot = new char[N];
			for (int j = 0; j < N; j++) { // O:빈공간(0) / X:벽(2) / I:도연(1) / P 사람(3)
				dot[j] = input.charAt(j);
				// System.out.println(dot[j]);
				if (dot[j] == 'O') {
					map[i][j] = 0;
				} else if (dot[j] == 'I') { // 도연
					map[i][j] = 1;
					doyeon.x = i;
					doyeon.y = j;
					startX = doyeon.x;
					startY = doyeon.y;
				} else if (dot[j] == 'X') {
					map[i][j] = 2;
				} else if (dot[j] == 'P') {
					map[i][j] = 3;
				}
			}
		}

		dfs(startX, startY);
		if (doyeon.m == 0) {
			System.out.println("TT");
		} else {
			answer = doyeon.m;
			System.out.println(answer);

		}
	}

	private static void dfs(int currX, int currY) {
		isVisited[currX][currY] = true;

		for (int i = 0; i < 4; i++) {
			int nextX = currX + x[i];
			int nextY = currY + y[i];

			if (nextX >= 0 && nextY >= 0 && nextX < M && nextY < N && !isVisited[nextX][nextY]) {
				if (map[nextX][nextY] == 0) {
					dfs(nextX, nextY);
				} else if (map[nextX][nextY] == 2) {
					continue;
				}
				if (map[nextX][nextY] == 3) {
					doyeon.m++;
					dfs(nextX, nextY);
				} else {
					continue;
				}
			} else {
				continue;
			}
		}
	}
}

// 저도 알고 있어요 
// 도연이는 쓸모없었다는걸
// 그렇지만 그거 아세요?
// 그래도 도연이는 기뻤을겁니다.
// 감사합니다.
