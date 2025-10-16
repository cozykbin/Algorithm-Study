import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
	static int N, M, cnt;
	static int[][]map;
	static boolean[][]visited;
	static int min = Integer.MAX_VALUE;
	static int mx[] = {-1, 1, 0, 0};
	static int my[] = {0, 0, -1, 1};
	
	static class Node{
		int x, y, cnt;
		
		Node(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for(int i = 0; i < N; i++) {
			String split = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = split.charAt(j)-'0';
			}
		}
		
		bfs(new Node(0, 0, 1));

		bw.write(cnt + "");

		bw.flush();
		bw.close();
		br.close();
	}

	private static void bfs(Node now) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(now);
		visited[now.x][now.y] = true;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if(cur.x == N-1 && cur.y == M-1) {
				cnt = cur.cnt;
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + mx[i];
				int ny = cur.y + my[i];

				if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || map[nx][ny] == 0) continue;

				if(!visited[nx][ny] && map[nx][ny] == 1) {
					visited[nx][ny] = true;
					queue.offer(new Node(nx, ny, cur.cnt + 1));
				}
			}

		}
		
	}
}