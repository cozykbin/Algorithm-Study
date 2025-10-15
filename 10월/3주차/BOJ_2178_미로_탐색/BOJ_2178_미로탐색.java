import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178_미로탐색 {

	private static int N;  // 미로 세로 크기
	private static int M;  // 미로 가로 크기
	private static int[][] maze;  // 미로 저장 배열
	private static boolean[][] visited;  // 방문여부 저장 배열
	
	// 탐색방향
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	private static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		
		visited[0][0] = true;
		q.offer(new int[] {0, 0, 1});  // (x좌표, y좌표, 이동거리)
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int dist = cur[2];
			
			// 도착하면 거리 반환
			if(x == N - 1 && y == M - 1) return dist;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;  // 범위 체크
				if (visited[nx][ny] || maze[nx][ny] != 1) continue;  // 방문여부, 이동 가능 여부 체크
				
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny, dist + 1});
			}
		}
		
		return -1;  // 도착 못하는 경우, 이 문제에서는 없음
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maze = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = line.charAt(j) - '0';
			}
		}
		
		int ans = bfs();
		
		System.out.println(ans);
	}	
}
