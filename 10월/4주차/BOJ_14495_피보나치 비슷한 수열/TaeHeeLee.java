//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//public class BOJ_14495_피보나치비스무리한수열 {
//    
//    private static Long[] dp;
//    
//    private static long DP(int num) {
//        if (dp[num] == null) {
//            dp[num] = DP(num - 1) + DP(num - 3);
//        }
//        
//        return dp[num];
//    }
//    
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        
//        int n = Integer.parseInt(br.readLine());
//        
//        dp = new Long[n + 1];
//        
//        dp[1] = dp[2] = dp[3] = 1L;
//        
//        System.out.println(DP(n));
//    }
//}


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_14495_피보나치비스무리한수열 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n <= 3) {
            System.out.println(1);
            return;
        }

        long[] dp = new long[n + 1];
        dp[1] = dp[2] = dp[3] = 1;

        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 3];
        }

        System.out.println(dp[n]);
    }
}
