import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573_빙산 {
	
	private static int N;  // 배열 세로 크기
	private static int M;  // 배열 가로 크기
	private static int[][] map;  // 2차원 배열 저장
	private static boolean[][] visited;  // 방문여부 저장 배열
	
	// 탐색 방향
	private static int[] dx = {1, 0, -1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	// 빙산이 동시에 녹아야함...
	// 아래에 주석 처리한 메서드는 하나씩 녹이기때문에 잘못된 로직임.ㅠㅠ
//	private static void melt() {
//		
//		for (int i= 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				
//				if (map[i][j] != 0) {
//					
//					int cntWater = 0;
//					for (int k = 0; k < 4; k++) {
//						int nx = i + dx[k];
//						int ny = j + dy[k];
//						
//						if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
//						
//						if (map[nx][ny] == 0) cntWater++;
//					}
//					
//					map[i][j] -= cntWater;
//					
//					if (map[i][j] < 0) map[i][j] = 0;
//				}
//			}
//		}
//	}
	
	// 빙산 높이 줄이는 메서드
	private static void melt() {
        int[][] copy = new int[N][M];  // 빙산 높이를 한번에 줄여주기 위해 배열을 하나 생성

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {  // 물이 아니라면, 빙산일때!
                	int cntWater = 0;  // 주변 물 개수
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        
                        if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;  // 범위 체크
                        
                        if (map[nx][ny] == 0) cntWater++;  // 주변에 물이 있으면 물 개수 올려주기
                    }

                    copy[i][j] = map[i][j] - cntWater;  // 빙산 높이 줄여주기
                    
                    if (copy[i][j] < 0) copy[i][j] = 0;  // 음수 방지
                }
            }
        }

        // 새로 생성하여 줄어든 빙산 높이를 가지고 있는 배열을 원본 배열에 복사
        for (int i = 0; i < N; i++) {
            System.arraycopy(copy[i], 0, map[i], 0, M);
        }
    }
	
	// 기본 bfs코드, 빙산 덩어리 개수 체크 메서드
	private static void countIceberg(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		
		visited[x][y] = true;
		q.offer(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (visited[nx][ny] || map[nx][ny] == 0) continue;
				
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny});
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 로직 시작
		int year = 0;  // 지나간 시간(년)
		while(true) {
			int cntIceberg = 0;  // 빙산 덩어리 개수
			visited = new boolean[N][M];  // 반복문 돌때마다 방문여부 저장 배열 새로 생성
			
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < M; y++) {
					if (!visited[x][y] && map[x][y] != 0) {  // 방문하지 않고, 물이 아니라면 덩어리 탐색 시작
						countIceberg(x, y);
						cntIceberg++;
					}
				}
			}
			
			if (cntIceberg >= 2) break;  // 덩어리가 2개 이상이 되면 반복문 탈출
			
			melt();  // 덩어리가 2개 이상이 안됐으면 다음 해로... 빙산 녹여줌
			year++;  // 1년 지남
			
			if (cntIceberg == 0) {  // 덩어리가 한 개도 발견되지 않았다면 빙산이 다 녹아버렸다는 뜻
				year = 0;
				break;
			}	
		}
		
		System.out.println(year);	
	}
}
