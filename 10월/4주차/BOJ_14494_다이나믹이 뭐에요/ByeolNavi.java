import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n, m;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		long[][] dp = new long[n + 1][m + 1];
		for (int j = 1; j <= m; j++) {
			dp[1][j] = 1;
		}

		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				dp[i][j] = (dp[i - 1][j] + dp[i][j - 1] + dp[i - 1][j - 1]) % 1000000007;
			}
		}

//		for (int i = 0; i <= n; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}

		System.out.println(dp[n][m]);
	}
}
