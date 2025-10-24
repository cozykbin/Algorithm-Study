import java.io.*;
import java.util.*;

public class Sonjuyeong {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long[][] dp = new long[32][32];

		int H = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		// pc방에서 출발하나 집에서 출발하는 갈 수 있는 경로는 똑같으므로 더 작은 수를 start, 큰 수를 end로 둠
		int start = H < N ? H : N;
		int end = (start == H) ? N : H;

		// start가 0일 때에도 i-1값 처리하고자 padding 줌
		++start;
		++end;

		for (int i = start; i <= end; i++) {
			for (int j = i; j <= end; j++) {
				if (i == start && j == start) {
					// 초기값 설정
					dp[i][j] = 1;
					continue;
				}
				// 왼쪽에서 온 경우의 수 + 위에서 온 경우의 수
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
			}
		}
		System.out.println(dp[end][end]);
	}
}
