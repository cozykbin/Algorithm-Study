import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		if (n == 1) {
			System.out.println(0);
			return;
		}

		int k;
		int[] m_jump = new int[n + 1];
		int[] b_jump = new int[n + 1];

		for (int i = 1; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			m_jump[i] = Integer.parseInt(st.nextToken());
			b_jump[i] = Integer.parseInt(st.nextToken());
		}

		if (n == 2) {
			System.out.println(m_jump[1]);
			return;
		}

		k = Integer.parseInt(br.readLine());
		int[][] dp = new int[n + 1][n + 1];

		// 큰 점프 도착 위치 지정하기
		for (int b = 3; b <= n; b++) {
			for (int i = 1; i <= n; i++) {
				// 작은 점프
				if (i > 1) {
					dp[b][i] = dp[b][i - 1] + m_jump[i - 1];
				}
				// 큰점프가 가능하다면 큰점프
				if (i > 2) {
					dp[b][i] = Math.min(dp[b][i], dp[b][i - 2] + b_jump[i - 2]);
				}
				// 존나 큰 점프 (근데 큰 점프를 안 하는 경우의 수도 찾아야 함 *어차피 min값 저장인데 이거 추가한다고 87퍼에서 94퍼가 된게 말이
				// 안 되잖아!!!!!!*)
				if (b != 3 && i == b) {
					dp[b][i] = Math.min(dp[b][i], dp[b][i - 3] + k);
				}
			}
		}

		// 최솟값 찾기
		int sol = 1111111111;
		for (int i = 3; i <= n; i++) {
			sol = Math.min(sol, dp[i][n]);
		}

//		for (int i = 0; i <= n; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}

		System.out.println(sol);
	}
}

/* 실패코드 */
/*
 * -> dp구간이 잘렸는지 안 잘렸는지 어떻게 알아? // 겁나큰점프가 필요한 구간을 찾으면 되는거 아닌가???????? int
 * 제일비싼점프구간 = 0; for (int i = 1; i <= n - 3; i++) { int gap = dp[i + 3] - dp[i];
 * if (제일비싼점프구간 < gap) { 제일비싼점프구간 = gap; } }
 * 
 * // 제일비싼점프구간이 k보다 작으면 점프를 왜 해... if (제일비싼점프구간 < k) {
 * System.out.println(dp[n]); } else { System.out.println(dp[n] - 제일비싼점프구간 + k);
 * }
 */
