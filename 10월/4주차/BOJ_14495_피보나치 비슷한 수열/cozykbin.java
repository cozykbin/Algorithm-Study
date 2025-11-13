package 실버박살;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class M_14495_피보나치비스무리한수열 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		long[] nums = new long[117];

		nums[1] = 1;
		nums[2] = 1;
		nums[3] = 1;
		// f(n) = f(n-1) + f(n-3)
		if (N > 3) {
			for (int i = 4; i <= N; i++) {
				nums[i] = nums[i - 1] + nums[i - 3];
			}
		}
		System.out.println(nums[N]);
	}
}

//EASY,,
