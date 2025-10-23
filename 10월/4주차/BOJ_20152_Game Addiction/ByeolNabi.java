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
		StringTokenizer st = new StringTokenizer(br.readLine());

		int H, N;
		H = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		int gap;
		gap = Math.abs(H - N) + 1;

		long[][] dp = new long[gap + 1][gap + 1];

		dp[1][1] = 1;

		// 수학 좌표평면처럼 x를 가로축으로 생각했습니다.
		for (int x = 2; x <= gap; x++) {
			for (int y = 1; y <= x; y++) {
				dp[x][y] = dp[x - 1][y] + dp[x][y - 1];
			}
		}

		System.out.println(dp[gap][gap]);

//		for (int i = 0; i < gap + 1; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
	}
}

// 삼각형 형태의 dp테이블 채우기
