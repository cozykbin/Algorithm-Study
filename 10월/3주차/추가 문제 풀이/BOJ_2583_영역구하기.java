import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2583_영역구하기 {

	private static int M;  // 종이 세로 크기
	private static int N;  // 종이 가로 크기
	private static int K;  // 직사각형 개수
	private static int[][] paper;  // 종이 저장 배열
	private static List<Integer> list;  // 분리된 영역 넓이 저장 리스트
	private static boolean[][] visited;  // 방문여부 저장 배열
	
	// 탐색 방향
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	// 분리된 영역 구하는 메서드
	private static int bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		
		visited[x][y] = true;
		q.offer(new int[] {x, y});
		
		int cnt = 1;  // 분리된 영역의 넓이
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;  // 범위체크
				if (visited[nx][ny] || paper[nx][ny] == 1) continue;  // 방문여부, 직사각형 여부 체크
				
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny});
				cnt++;
			}
		}
		
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		paper = new int[M][N];
		list = new ArrayList<>();
		visited = new boolean[M][N];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			// 자꾸 아웃오브인덱스 나길래 확인해보니
			// y좌표를 먼저 돌려줘야 하네요ㅠㅠ.
			// 기본적으로 생각해야하는데, 아직 y, x 좌표가 헷갈릴 때가 있습니다.
//			for (int x = x1; x < x2; x++) {
//				for (int y = y1; y < y2; y++) {
//					paper[x][y] = 1;
//				}
//			}
			
			// 직사각형 영역은 1로 바꿔주기
			for (int y = y1; y < y2; y++) {
				for (int x = x1; x < x2; x++) {
					paper[y][x] = 1;
				}
			}
		}
		
		// 분리된 영역 구하기
		int cnt = 0;  // 분리된 영역 개수
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (paper[i][j] != 1 && !visited[i][j]) {
					int area = bfs(i, j);  // 분리된 영역 넓이
					list.add(area);
					cnt++;
				}
			}
		}
		
		// 분리된 영역의 넓이 오름차순 정렬
		Collections.sort(list);
		
		sb.append(cnt + "\n");
		for (int val : list) {
			sb.append(val + " ");
		}
		
		System.out.println(sb);
	}
}
