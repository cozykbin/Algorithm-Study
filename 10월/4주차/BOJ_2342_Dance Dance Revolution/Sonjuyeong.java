import java.io.*;
import java.util.*;

public class Sonjuyeong {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //각각 밟을 때(?), 왼발 위치, 오른발 위치
        //각 시점에서 위치 별 최소 힘 담을 dp 생성..
        int[][][] dp = new int[100001][5][5];
        for (int i = 0; i <= 100000; i++) {
            for (int j = 0; j < 5; j++) {
                //밟을 일이 없는 부분 처리
                Arrays.fill(dp[i][j], 500000);
            }
        }

        //첫 시작.
        dp[0][0][0] = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        int input = Integer.parseInt(st.nextToken());

        int idx = 0;

        while (input != 0) {
            for (int i = 0; i < 5; i++) {
                //왼 발을 움직였을 때 드는 힘.
                int left = calculate(i, input);
                for (int j = 0; j < 5; j++) {
                    //오른발을 움직였을 때 드는 힘.
                    int right = calculate(j, input);
                    //왼발을 움직였을 때 드는 최소 힘 할당
                    if (input != j)
                        dp[idx + 1][input][j] = Math.min(dp[idx + 1][input][j], dp[idx][i][j] + left);
                    //오른발을 움직였을 때 드는 최소 힘 할당
                    if (input != i)
                        dp[idx + 1][i][input] = Math.min(dp[idx + 1][i][input], dp[idx][i][j] + right);
                }
            }
            ++idx;
            input = Integer.parseInt(st.nextToken());
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                //DDR이 끝난 뒤 최소로 든 힘 찾기
                answer = Math.min(answer, dp[idx][i][j]);
            }
        }

        System.out.println(answer);
    }

    public static int calculate(int pre, int cur) {
        // 0에서 출발하는 경우
        if (pre == 0) {
            return 2;
        } else if (pre == cur) {    // 같은 위치를 밟는 경우
            return 1;
        } else if (Math.abs(pre - cur) == 2) {  // 반대 편 위치를 밟는 경우
            return 4;
        } else return 3;    // 그 외 경우
    }
}
