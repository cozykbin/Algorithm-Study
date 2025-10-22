import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14494_다이나믹이뭐예요 {

	// 도착 지점 좌표
	private static int N;
	private static int M;
	
	// 경우의 수 저장 배열
	private static Long[][] dp;
	
	private static Long DP(int n, int m) {
		if (n < 1 || m < 1) return 0L;  // 범위 체크
		
		if (dp[n][m] == null) {
			// 왼쪽에서 오는 것 + 위에서 오는 것 + 좌상단 대각선에서 오는 것
			dp[n][m] = (DP(n, m - 1) + DP(n - 1, m) + DP(n - 1, m - 1)) % 1000000007;
		}
		
		return dp[n][m];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		dp = new Long[N + 1][M + 1];
		
		// 초기 상태 설정
        dp[1][1] = 1L;
	
        System.out.println(DP(N, M));
	}
}
