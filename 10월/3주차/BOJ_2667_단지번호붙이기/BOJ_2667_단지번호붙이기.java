import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class BOJ_2667_단지번호붙이기 {

	private static int N;  // 지도 크기
	private static int[][] map;  // 지도 저장 배열
	private static boolean[][] visited;  // 방문 여부 저장 배열
	private static List<Integer> ans = new ArrayList<>();  // 단지별로 집의 개수를 담을 리스트
	
	// 탐색 방향
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	// 단지별 집의 개수 세는 메서드
	private static int bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		
		visited[x][y] = true;
		q.offer(new int[] {x, y});
		int cnt = 1;  // 단지별 집의 개수
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if (nx < 0 || nx >= N || ny <0 || ny >= N) continue;
				if (visited[nx][ny] || map[nx][ny] != 1) continue;
				
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny});
				cnt++;  // 새로 탐색할 때 집의 개수 더해주기
			}
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		int count = 0;  // 단지 개수
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (!visited[x][y] && map[x][y] == 1) {
					int cntHome = bfs(x, y);  // 단지별 집의 개수
					ans.add(cntHome);
					count++;
				}
			}
		}
		
		Collections.sort(ans);  // 단지별 집의 개수 오름차순 정렬
		
		sb.append(count + "\n");
		for (int i = 0; i < ans.size(); i++) {
            sb.append(ans.get(i) + "\n");
        }
		
		System.out.println(sb);
	}
}
