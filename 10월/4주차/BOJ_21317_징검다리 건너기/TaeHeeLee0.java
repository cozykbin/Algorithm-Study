import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21317_징검다리건너기 {

    private static int N;  // 돌의 개수
    private static int[][] rock;  // 점프 시 필요한 에너지 저장 배열
    private static int K;  // 매우 큰 점프 시 필요한 에너지
    private static int ans = Integer.MAX_VALUE;  // 산삼을 얻기 위한 최소 에너지
    private static int[][] dp;  // 메모이제이션을 위한 배열

    private static void dfs(int idx, int val, boolean isBig) {
    	
    	// 매우 큰 점프 사용 여부
    	int used;
        if (isBig) {
        	used = 1;
        } else {
        	used = 0;
        }
        
        // 가지치기
        if (val >= dp[idx][used]) return;
        
        // 에너지 누적 값 메모이제이션
        dp[idx][used] = val;

        // 기저 조건
        if (idx == N - 1) {
            ans = Math.min(ans, val);
            return;
        }

        // 유도 부분
        if (idx + 1 < N) {  // 작은 점프
            dfs(idx + 1, val + rock[idx][0], isBig);
        }
        if (idx + 2 < N) {  // 큰 점프
            dfs(idx + 2, val + rock[idx][1], isBig);
        }
        if (!isBig && idx + 3 < N) {  // 매우 큰 점프
            dfs(idx + 3, val + K, true);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        rock = new int[N][2];
        dp = new int[N][2];

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            rock[i][0] = Integer.parseInt(st.nextToken());
            rock[i][1] = Integer.parseInt(st.nextToken());
        }
        K = Integer.parseInt(br.readLine());

        // 모두 최대값으로 초기화
        for (int i = 0; i < N; i++) {
        	dp[i][0] = Integer.MAX_VALUE;
        	dp[i][1] = Integer.MAX_VALUE;
        }

        dfs(0, 0, false);

        System.out.println(ans);
    }
}
