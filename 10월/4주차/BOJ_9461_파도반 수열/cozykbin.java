package 실버박살;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class M_9461_파도반수열 {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(in.readLine());
			long[] nums = new long[101];

			nums[1] = 1;
			nums[2] = 1;
			nums[3] = 1;
			nums[4] = 2;
			nums[5] = 2;
			
			if (N > 5) {
				for (int i = 6; i <= N; i++) {
					nums[i] = nums[i - 1] + nums[i - 5];
				}
			}sb.append(nums[N]).append("\n");
		}System.out.println(sb);
	}
}


// 넘나리 쉬운 DP 기초문제
