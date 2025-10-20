import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16236_아기상어 {

	private static int N;  // 공간 크기
	private static int[][] map;  // 공간 저장 배열
	private static int[] start;  // 탐색 시작 위치 저장 배열
	private static boolean[][] visited;  // 방문여부 저장 
	private static int size;  // 아기상어 크기
	private static int sec;  // 물고기를 잡아먹을 수 있는 시간
	private static int cnt;  // 물고기 개수
	private static int bite;  // 물고기를 잡아먹은 횟수
	private static int[] end;  // 물고기를 잡아먹은 지점
	
	// 탐색 방향
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	// 다익스트라 탐색
	private static int bfs(int x, int y) {
		PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
			if (a[2] != b[2]) return Integer.compare(a[2], b[2]);  // 거리 비교
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);  // 행 비교
            return Integer.compare(a[1], b[1]);  // 열 비교
		});
		
		visited[x][y] = true;
		q.offer(new int[] {x, y, 0});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			int dist = cur[2];
			
			// 물고기를 잡아 먹었다면 종료지점 저장 후, 거리 반환
			if (map[cx][cy] != 0 && map[cx][cy] < size) {
                end[0] = cx;
                end[1] = cy;
                return dist;
            }
			
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;  // 범위 체크
				if (visited[nx][ny] || size < map[nx][ny]) continue;  // 방문 여부, 더 큰 물고기인지 체크
				
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny, dist + 1});
			}
		}
		
		return -1;  // 잡아먹을 물고기가 없으면 -1 반환
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		start = new int[2];
		size = 2;
		sec = 0;
		cnt = 0;
		bite = 0;
		end = new int[2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 탐색 시작 위치 저장
				if (map[i][j] == 9) {
					start[0] = i;
					start[1] = j;
					map[i][j] = 0;  // 위치 저장 후, 0으로 초기화
				}
				// 잡아먹을 수 있는 물고기 있는지 체크
				if (map[i][j] != 0 && map[i][j] != 9) cnt++;
			}
		}
		
		// 잡아 먹을 수 있는 물고기가 있는지 먼저 체크 후 없다면 0 반환
		if (cnt == 0) {
			System.out.println(0);
			return;
		}
		
		while (true) {
			visited = new boolean[N][N];  // 방문여부 배열 반복문마다 생성
			int move = bfs(start[0], start[1]);  // 시작 지점부터 탐색
			
			if (move == -1) break;  // 더 이상 잡아먹을 물고기 없으면 반복문 종료
			
			map[end[0]][end[1]] = 0;  // 잡아먹은 물고기 위치 0으로 초기화
			
			bite++;  // 물고기를 잡아먹은 횟수 증가
			if (size == bite) {  // 크기와 잡아먹은 횟수가 같아지면
				size++;  // 크기 증가
				bite = 0;  // 잡아먹은 횟수 초기화
			}
			
			sec += move;  // 이동거리만큼 시간 증가
			
			// 탐색 종료 지점을 시작 위치로 설정
			start[0] = end[0];
			start[1] = end[1];
		}
		
		System.out.println(sec);
	}
}
