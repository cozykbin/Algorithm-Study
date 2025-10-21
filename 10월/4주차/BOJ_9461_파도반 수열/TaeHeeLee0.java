import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9461_파도반수열 {

	private static int T;
	private static int N;
	private static long[] DP;
	
//	private static long P(int num) {
//		if (DP[num] == 0) {
//			return P(num - 2) + P(num - 3);
//		}
//		
//		return DP[num];
//	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		DP = new long[101];
		DP[1] = 1; DP[2] = 1; DP[3] = 1;
		DP[4] = 2; DP[5] = 2; DP[6] = 3;
		DP[7] = 4; DP[8] = 5; DP[9] = 7;
		for (int i = 10; i < 101; i++) {
			DP[i] = DP[i - 2] + DP[i - 3];
		}
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
					
			sb.append(DP[N] + "\n");
		}
		
		System.out.println(sb);
	}
}
