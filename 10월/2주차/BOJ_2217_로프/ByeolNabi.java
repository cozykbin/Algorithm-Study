package solution.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S4_2217 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] numbs = new int[n];

		for (int i = 0; i < n; i++) {
			numbs[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(numbs);

		int maxLen = 0;
		for (int i = 0; i < n; i++) {
			maxLen = Math.max(numbs[i] * (n - i), maxLen);
		}

		System.out.println(maxLen);
	}
}
