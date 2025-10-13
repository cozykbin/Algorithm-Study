package BOJ_14888_연산자끼워넣기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888_연산자끼워넣기 {
	
	private static int N;  // 수의 개수
	private static int[] A;  // 숫자 저장 배열
	private static int[] opCnt;  // 연산자 개수 저장 배열
	
	private static int maxValue = Integer.MIN_VALUE;
	private static int minValue = Integer.MAX_VALUE;
	
	private static void dfs(int val, int cnt) {
		
		// 기저조건
		if (cnt == N) {
			maxValue = Math.max(maxValue, val);
            minValue = Math.min(minValue, val);
            return;
		}
		
		// 유도부분
		for (int i = 0; i < 4; i++) {
			// 연산자 개수 확인
			if (opCnt[i] > 0) {
				// 덧셈
				if(i == 0) {
					opCnt[i]--;
					dfs(val + A[cnt], cnt + 1);
					opCnt[i]++;
				}
				// 뺄셈
				else if(i == 1) {
					opCnt[i]--;
					dfs(val - A[cnt], cnt + 1);
					opCnt[i]++;
				}
				// 곱하기
				else if(i == 2) {
					opCnt[i]--;
					dfs(val * A[cnt], cnt + 1);
					opCnt[i]++;
				}
				// 나누기
				else if(i == 3) {
					opCnt[i]--;
					dfs(val / A[cnt], cnt + 1);
					opCnt[i]++;
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		opCnt = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < 4; j++) {
			opCnt[j] = Integer.parseInt(st.nextToken());
		}
		
		dfs(A[0], 1);
		
		System.out.println(maxValue);
		System.out.println(minValue);
		
	}

}
