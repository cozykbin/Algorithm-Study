package samsung01;

import java.io.*;
import java.util.*;

public class Sonjuyeong {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[] dp = new long[n + 3];
		dp[1] = dp[2] = dp[3] = 1;
		for (int i = 4; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 3];
		}
		System.out.println(dp[n]);
	}
}