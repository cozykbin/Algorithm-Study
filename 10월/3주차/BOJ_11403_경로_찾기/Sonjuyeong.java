package samsung01;

import java.io.*;
import java.util.*;

public class Sonjuyeong {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] answer = bfs(n, arr);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0;i<n;i++) {
			for(int j=0;j<n;j++) {
				sb.append(answer[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static int[][] bfs(int n, int[][] arr) {
		int[][] result = new int[n][n];
		for (int i = 0; i < n; i++) {
			boolean visited[][] = new boolean[n][n];
			Queue<Integer> q = new ArrayDeque<>();
			q.offer(i);
			while (!q.isEmpty()) {
				int cur = q.poll();
				for (int j = 0; j < n; j++) {
					if (arr[cur][j] == 1) {
						result[cur][j] = 1;
						result[i][j] = 1;
						if (!visited[i][j]) {
							visited[i][j]=true;
							q.offer(j);
						}
					}
				}
			}
		}
		return result;
	}
}
