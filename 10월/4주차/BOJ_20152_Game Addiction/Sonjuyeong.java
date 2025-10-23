package samsung01;

import java.io.*;
import java.util.*;

public class Sonjuyeong {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long[][] dp = new long[32][32];
		
		int H = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int start = H<N?H:N;
		int end = (start==H)?N:H;
		
		++start;
		++end;
		
		for(int i = start; i<=end;i++) {
			for(int j = i;j<=end;j++) {
				if(i==start&&j==start) {
					dp[i][j]=1;
					continue;
				}
				dp[i][j]=dp[i-1][j]+dp[i][j-1];
			}
		}
		
		System.out.println(dp[end][end]);
	}
}