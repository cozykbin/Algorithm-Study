import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20152_GameAddiction {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long dp[][] = new long[N + 1][M + 1];

        dp[1][1] = 1;

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                if(i == 1 && j == 1){
                    continue;
                }

                dp[i][j] = (dp[i][j-1] + dp[i-1][j]+ dp[i-1][j-1]) % 1000000007;
            }
        }

        System.out.println(dp[N][M]%1000000007);

        br.close();
    }
}
