import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_11403_경로찾기 {

	private static int N;  // 정점의 개수
	private static int[][] graph;  // 입력 받을 인접행렬 저장 배열
	private static boolean[] visited;  // 방문여부 저장 배열
	private static ArrayList<ArrayList<Integer>> list;  // 그래프를 인접리스트로 변환
	
	private static int dfs(int start, int end) {	
		
		for (Integer next : list.get(start)) {  // 출발 노드에서 갈 수 있는 노드를 하나씩 뽑아냄
			
			// 출발노드가 도착지(j)와 같아지면 1 반환
			if (next == end) {
				return 1;
			}
			
			// 방문하지 않은 노드라면
			if (!visited[next]) {
				visited[next] = true;  // 방문 처리
				if (dfs(next, end) == 1) {
					return 1;
				}
			}
		}
		
		// i에서 j로 도달할 수 없으면 0 반환
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		graph = new int[N][N];
		list = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i < N; i++) {
            list.add(new ArrayList<Integer>());
        }
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				
				// i에서 j까지의 간선 연결
				if (graph[i][j] == 1) {
					list.get(i).add(j);
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited = new boolean[N];  // 완탐으로 탐색마다 방문여부 배열 생성
				visited[i] = true;
				int canGo = dfs(i, j);  // i에서 j까지 갈 수 있는지 여부
				sb.append(canGo + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
