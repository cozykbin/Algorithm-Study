package 실버박살;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M_14494_다이나믹이뭐예요 {
	static int N, M;
	static long[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new long[N + 1][M + 1];

		for (int i = 0; i < N; i++) {
			map[i][0] = 1;
		}
		for (int i = 0; i < M; i++) {
			map[0][i] = 1;
		}

		// f(n,n) = f(n-1,n) + f(n, n-1) + f(n-1, n-1)
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				map[i][j] = (map[i - 1][j] % 1000000007 + map[i][j - 1] % 1000000007 + map[i - 1][j - 1] % 1000000007)
						% 1000000007;
			}
		}

		System.out.println(map[N - 1][M - 1]);
	}
}

