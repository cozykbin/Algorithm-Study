import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10164_격자상의경로 {

    private static int N;  // 격자판 행의 수
    private static int M;  // 격자판 열의 수
    private static int K;  // 동그라미 칸의 번호
    private static long[][] dp;  // 경로 개수 저장 배열
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        // 굉장히 쉬움, 어제 풀었던 game addiction 문제와 동일
        if (K == 0) {
            dp = new long[N + 1][M + 1];
            
            dp[1][1] = 1;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (i > 1) dp[i][j] += dp[i-1][j];
                    if (j > 1) dp[i][j] += dp[i][j-1];
                }
            }
            
            System.out.println(dp[N][M]);
            return;
        }
        
        // 동그라미가 표시되어 있는 곳의 좌표를 구한 후,
        // 동그라미가 표시되어 있는 곳까지의 경로 계산 값과
        // 동그라미가 표시되어 있는 곳에서 도착지점까지의 경로 계산 값을 곱해준다
        else {
        	// M이 5일때
        	// K가 1이면 (1, 1)
        	// K가 5이면 (1, 5)
        	// K가 8이면 (2, 3)
        	// K가 9이면 (2, 4)
        	// K가 10이면 (2, 5)
        	// K가 11이면 (3, 1)
        	// K가 15이면 (3, 5)
//        	int x = K / M + 1;
//          int y = K % M;
        	// 이 좌표 구하는 식을 세우는데 정말 오래걸림
        	// 이런 식을 세울 때,
        	// 금방 떠올릴 수 있는 방법은 없을까?
            int x = (K - 1) / M + 1;
            int y = (K - 1) % M + 1;
            
            // 시작점부터 동그라미까지 경로 계산
            dp = new long[N + 1][M + 1];
            dp[1][1] = 1;
            
            for (int i = 1; i <= x; i++) {
                for (int j = 1; j <= y; j++) {
                    if (i > 1) dp[i][j] += dp[i-1][j];
                    if (j > 1) dp[i][j] += dp[i][j-1];
                }
            }
            
            long val1 = dp[x][y];  // 경로 개수 저장
            
            // 동그라미부터 도착지점까지 경로 계산
            dp = new long[N + 1][M + 1];
            dp[x][y] = 1;
            
            for (int i = x; i <= N; i++) {
                for (int j = y; j <= M; j++) {
                    if (i > x) dp[i][j] += dp[i-1][j];
                    if (j > y) dp[i][j] += dp[i][j-1];
                }
            }
            
            long val2 = dp[N][M];
            
            // 처음에는 두 값을 더해야하는 줄 알았는데 곱해야하네요...
            // 다시 생각해보면 당연한건데, 아직 이런 디테일들이 부족한 것 같습니다.
            System.out.println(val1 * val2);
        }
    }
}
