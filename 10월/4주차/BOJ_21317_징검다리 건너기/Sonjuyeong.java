package samsung01;

import java.io.*;
import java.util.*;

public class Sonjuyeong {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// dp 배열
		int[][] dp = new int[N + 1][2];
		// 작은 점프
		int[] s = new int[N];
		// 큰 점프
		int[] b = new int[N];

		for (int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			s[i] = Integer.parseInt(st.nextToken());
			b[i] = Integer.parseInt(st.nextToken());
		}

		int K = Integer.parseInt(br.readLine());

		// 0열 -> 매우 큰 점프 X인 경우, 1열 -> 매우 큰 점프 한 경우
		dp[1][0] = 0;
		dp[1][1] = 100001;
		for (int i = 2; i <= N; i++) {
			// 2번째 돌의 경우
			if (i == 2) {
				// 작은 점프말곤 할 수 있는 경우가 없으므로 이전 점프 경우만 고려.
				dp[i][0] = dp[i - 1][0] + s[i - 1];
				// 매우 큰 점프는 할 수 없으므로 100001 넣어주기.
				dp[i][1] = 100001;
			} else if (i == 3) {
				// 작은 점프와 큰 점프 고려.
				dp[i][0] = Math.min(dp[i - 1][0] + s[i - 1], dp[i - 2][0] + b[i - 2]);
				// 마찬가지로 매우 큰 점프는 할 수 없으므로 100001 넣어주기.
				dp[i][1] = 100001;
			} else {
				// 매우 큰 점프를 하지 않은 경우의 작은 점프와 큰 점프 고려.
				dp[i][0] = Math.min(dp[i - 1][0] + s[i - 1], dp[i - 2][0] + b[i - 2]);
				// 큰 점프를 한 상태를 고려. 이미 큰 점프인 상태의 작은점프와 큰점프, 이제 막 매우 큰 점프를 한 상태의 값 비교.
				dp[i][1] = Math.min(Math.min(dp[i - 1][1] + s[i - 1], dp[i - 2][1] + b[i - 2]), dp[i - 3][0] + K);
			}
		}

		// 매우 큰 점프를 한 경우와 하지 않은 경우의 값 비교하여 answer 구하기
		int answer = Math.min(dp[N][0], dp[N][1]);
		System.out.println(answer);
	}
}