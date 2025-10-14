import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21736_헌내기는친구가필요해 {

	private static int N;  // 캠퍼스 세로 크기
	private static int M;  // 캠퍼스 가로 크기
	private static char[][] campus;  // 캠퍼스 저장 배열
	private static boolean[][] visited;  // 방문여부 저장 배열
	private static int cnt;  // 만날 수 있는 사람 수
	
	// 탐색 방향
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	private static void bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		
		visited[x][y] = true;
		q.offer(new int[] {x, y});
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;  // 범위 체크
				if (visited[nx][ny] || campus[nx][ny] == 'X') continue;  // 방문 여부, 벽 여부 체크
				
				// 사람을 만나면 cnt를 올려줌
				if (campus[nx][ny] == 'P') {
					cnt++;
				}
				
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny});	
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer (br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		campus = new char[N][M];
		visited = new boolean[N][M];
		
		int ix = 0;
		int iy = 0;
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				campus[i][j] = line.charAt(j);
				if (campus[i][j] == 'I') {
					ix = i;
					iy = j;
				}
			}
		}
		
		cnt = 0;
		bfs(ix, iy);
		
		if (cnt == 0) {
			System.out.println("TT");
		} else {
			System.out.println(cnt);
		}
		
	}
}
