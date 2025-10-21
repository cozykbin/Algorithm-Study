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
	public static long[] dp = new long[101];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] tmp = new int[] { 0, 1, 1, 1, 2, 2, 3, 4, 5, 7, 9, 12 };
		for (int i = 0; i <= 11; i++) {
			dp[i] = tmp[i];
		}

		int tc = Integer.parseInt(br.readLine());
		for (int tt = 0; tt < tc; tt++) {
			int N = Integer.parseInt(br.readLine());

			if (N <= 11) {
				System.out.println(dp[N]);
			} else {
				if (dp[N] == 0) {
					for (int i = 12; i <= N; i++) {
						dp[i] = dp[i - 1] + dp[i - 5];
					}
					System.out.println(dp[N]);
				} else {
					System.out.println(dp[N]);
				}
			}
		}
	}
}
