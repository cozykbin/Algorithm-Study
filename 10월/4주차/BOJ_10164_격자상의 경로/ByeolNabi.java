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
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n, m, k;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		int[][] dp = new int[n + 1][m + 1];

		int mr, mc;
		if (k == 0) { // 0 일때를 체크하자
			mr = 1;
			mc = 1;
		} else {
			// ((k - 1) / m) 괄호로 안 묶어서 레전드 사태 발생;;
			mr = ((k - 1) / m) + 1;
			mc = ((k - 1) % m) + 1; // 0에서 시작하는 나머지를 1부터 시작하게 한다.
		}

		dp[1][1] = 1;

			// 시작점 -> 중간점 -> 도착점 으로 두 구간을 나눠서 dp테이블 채우기
		// 시작점 -> 중간점
		for (int r = 1; r <= mr; r++) {
			for (int c = 1; c <= mc; c++) {
				// 1,1일때 처리하기
				if (r == 1 && c == 1)
					continue;
				dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
			}
		}

		// 중간점 -> 도착점
		for (int r = mr; r <= n; r++) {
			for (int c = mc; c <= m; c++) {
				if (r == 1 && c == 1)
					continue;
				dp[r][c] = dp[r - 1][c] + dp[r][c - 1];
			}
		}

		// dp테이블 보기
//		for (int i = 0; i <= n; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}

		System.out.println(dp[n][m]);
	}
}

// 가로 세로 헷갈리지 말기.
// dp[1][1]을 설정해놨을 때 for문 순회로 값 덮어씌우지 말기. (패딩해둔 곳에 값을 기록해도 되겠다.)
// (k - 1 / m) -> ((k - 1) / m)
// 항상 엣지케이스 조심!! 1 < N < 100  이런 범위이면 1체크 100체크
