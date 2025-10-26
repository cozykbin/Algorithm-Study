package com.ssafy.pro1;

import java.io.*;
import java.util.*;

import com.sun.source.tree.ArrayAccessTree;

public class pro1 {
	static int N, K;
	static int[][] D;
	static int memo[];
	static int sum = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		D = new int[N-1][2];
		memo = new int[N];
		
//		Arrays.fill(memo, -1);
		
		for(int i = 0; i < N - 1; i++) { 
			st = new StringTokenizer(br.readLine());
			D[i][0] = Integer.parseInt(st.nextToken());
			D[i][1] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());

		int ans = Dyn(0, memo, 1);

		bw.write(ans + "\n");

		bw.flush();
		br.close();
		bw.close();
	}

	private static int Dyn(int cur, int[] memo, int cnt){
		if(cur == N - 1) {//종료조건
			sum = Math.min(sum, memo[N-1]);
			return 0;
		}

		if(memo[cur] != 0) { // 메모제이션 고려해봐야함
			memo[cur] = memo[cur];
		}

		if(memo[cur] > sum) return 0; //가지치기??

		if(cur + 1 <= N - 1) {
			int pl = D[cur][0];
			memo[cur + 1] = memo[cur] + pl;
			Dyn(cur + 1, memo, cnt);
		}

		if(cur + 2 <= N - 1) {
			int us = D[cur][1];
			memo[cur + 2] = memo[cur] + us;
			Dyn(cur + 2, memo, cnt);
		}

		if(cur + 3 <= N - 1 && cnt > 0) {
			memo[cur + 3] = memo[cur] + K;
			Dyn(cur + 3, memo, cnt - 1);
		}

		return sum; // 여기 부분을 수정해야됨
	}

	//  작 큰    
	//0 -> 1 | 1  2
	//1 -> 2 | 2  3
	//2 -> 3 | 4  5
	//3 -> 4 | 6  7
	//매큰은 무조건 K만큼
}