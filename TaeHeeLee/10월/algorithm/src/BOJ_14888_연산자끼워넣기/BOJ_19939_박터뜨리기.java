package BOJ_14888_연산자끼워넣기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19939_박터뜨리기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// 등차수열 합 공식
		int sum = K * (K + 1) / 2;
		
		// 남는 공을 골고루 나누고 남는 개수
		int cnt = (N - sum) % K;
		
		if (N < sum) {
			System.out.println(-1);
		}
		else if (cnt == 0) {
			System.out.println(K - 1);
		} else {
			System.out.println(K);	
		}
	}
}
