import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노 {
	
	private static int N;  // 종이의 세로 크기
	private static int M;  // 종이의 가로 크기
	private static int[][] paper;  // 종이 저장 배열
	private static boolean[][] visited;  // 방문 여부 저장 배열
	private static int ans = Integer.MIN_VALUE;  // 테트로미노가 놓인 칸에 쓰인 수들의 합의 최댓값
	
	// 4방향 탐색 배열
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	// 무작위 방향으로 4칸 탐색했을 때
	// 값의 최대값 구하는 메서드
	// 주의할 점은 'ㅗ' 모양의 테트로미노
	private static void dfs(int x, int y, int cnt, int val) {
		
		// 4방향을 탐색했다면 최대값 갱신
		if (cnt == 4) {
			if (ans < val) ans = val;
			return;
		}
		
		// 4방향을 무작위로 백트래킹을 사용해 완전탐색
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
				// 'ㅗ' 모양일 때 처리
				// 다음 좌표의 값은 더해주지만\
				// 탐색 시작은 원래 좌표에서 시작
				if (cnt == 2) {
					visited[nx][ny] = true;
	                dfs(x, y, cnt + 1, val + paper[nx][ny]);
	                visited[nx][ny] = false;
				}
				
				// 일반적인 백트래킹 탐색
				visited[nx][ny] = true;
				dfs(nx, ny, cnt + 1, val + paper[nx][ny]);
				visited[nx][ny] = false;
			}
			
		}	
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		paper = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 완전탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j , 1, paper[i][j]);
				visited[i][j] = false;
			}
		}
		
		System.out.println(ans);
	}
}
