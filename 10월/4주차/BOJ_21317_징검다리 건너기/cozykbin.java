package 실버박살;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class M_21317_징검다리건너기 {
	static int N, K;
	static int[] sJump, bJump, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());

		if (N == 1) { // 돌이 1개면 점프 없이 종료
			System.out.println(0);
			return;
		}

		sJump = new int[N - 1]; // sJump[i] = (i+1 -> i+2) 돌 점프
		bJump = new int[N - 1]; // bJump[i] = (i+1 -> i+3) 돌 점프

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			sJump[i] = Integer.parseInt(st.nextToken());
			bJump[i] = Integer.parseInt(st.nextToken());
		}

		K = Integer.parseInt(in.readLine()); // KJump (i -> i+3)

		dp = new int[N];
		dp[0] = 0; // 1번 돌
		dp[1] = sJump[0];

		if (N == 2) { // 돌이 2개면 sJump 1회 후 종료
			System.out.println(dp[1]);
			return;
		}

		// 1. DP without K점프
		for (int i = 2; i < N; i++) {
			dp[i] = Math.min(dp[i - 1] + sJump[i - 1], dp[i - 2] + bJump[i - 2]);
		}

		int minEnergy = dp[N - 1]; // 일단 최소 E가 이건데..

		// 2. DP with K점프
		for (int i = 3; i < N; i++) { // 더 줄일 수 있는 세계관을 향해
			int[] dpWithK = new int[N];

			dpWithK[0] = dp[0];
			dpWithK[1] = dp[1];
			dpWithK[2] = dp[2];

			// 4번돌 착지부터, K점프를 사용을 고려한 경우를 포함하여 계산하고 비교
			for (int j = 3; j < N; j++) {
				dpWithK[j] = Math.min(dpWithK[j - 1] + sJump[j - 1], dpWithK[j - 2] + bJump[j - 2]);

				if (j == i) {
					dpWithK[j] = Math.min(dpWithK[j], dp[j - 3] + K);   // K 점프 사용해보기로 한 순서에는, 점프한 경우와 사용하지 않은 원본 DP값을 가져와 비교 
				}
			}
			minEnergy = Math.min(minEnergy, dpWithK[N - 1]);
		}
		System.out.println(minEnergy);
	}
}

// 끔찍하지만 많은 것을 배운 문제였습니다
// 어떻게 DP를 돌리면서 내부에서 스킬을 1번만 쓸 수 있을까 엄청 고민하다가.. N이 20밖에 안되길래, 포기하고 결국 완탐 비슷하게 풀었습니다.
// DP는 조건 1개만 추가되어도 너무나 새롭고 어렵네오.
// N = 1 안 쳐내면 89퍼에서 터지고 2 안쳐내면 94퍼에서 터집니다.
// 코드를 더 깔끔하게 바꿀 수 있을 것 같은데..원본 디피랑 같이 점프를 찾아볼 수 있을 것 같기도 하고... 비효율적이고 아름답지 않은 코드라고 생각하지만
// 너무 지쳐서 일단 올립니당 ><
