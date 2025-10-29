package samsung01;

import java.io.*;
import java.util.*;

public class Sonjuyeong {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N + 1][M + 1];

		int r = 0;
		int c = 0;

		// 위치 찾기.
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (++cnt == K) {
					r = i;
					c = j;
					break;
				}
			}
		}

		dp[1][1] = 1;

		//시작점부터 r,c까지의 경로
		//K값이 0이면 안돌아감
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				if (i == 1 && j == 1) {
					continue;
				}
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}

		//r,c부터 시작점까지의 경로
		//K값이 0이면 그냥 처음부터 찾기.
		for (int i = r != 0 ? r : 1; i <= N; i++) {
			for (int j = c != 0 ? c : 1; j <= M; j++) {
				if (i == 1 && j == 1) {
					continue;
				}
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}

		System.out.println(dp[N][M]);
	}
}