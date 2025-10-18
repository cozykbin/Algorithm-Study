package com.ssafy.pro1;

import java.util.*;
import java.io.*;
import java.lang.*;

public class pro1 {
	static int N, cnt;
	static int[][]map;
	static boolean[][]visited;
	static int min = Integer.MAX_VALUE;
	static int mx[] = {1, -1, 0, 0};
	static int my[] = {0, 0, 1, -1};
	static List<Integer> count;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		count = new ArrayList<>();
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			String split = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = split.charAt(j)-'0';
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					cnt = 1;
					dfs(i, j);
					count.add(cnt);
				}
			}
		}
		
		Collections.sort(count);
		
		bw.write(count.size() + "\n");
		for(int i = 0; i < count.size(); i++) {
			bw.write(count.get(i) + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int x, int y) {
		visited[x][y] = true;
		
		
		for(int i = 0; i < 4; i++) {
			int nx = x + mx[i];
			int ny = y + my[i];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) {
				continue;
			}
			
			if(map[nx][ny] == 1) {
				cnt++;
				dfs(nx, ny);
			}
		}
		
	}
}
























