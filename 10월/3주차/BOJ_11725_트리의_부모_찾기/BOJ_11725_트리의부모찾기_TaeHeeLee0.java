import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_11725_트리의부모찾기 {

	private static int N;  // 노드의 개수
	private static ArrayList<ArrayList<Integer>> graph;  // 그래프 저장 인접행렬
	private static boolean[] visited;  // 방문여부 저장 배열
	private static int[] parent;  // 부모노드 번호 저장 배열
	
	private static void dfs(int start) {
		
		for (Integer next : graph.get(start)) {		
			if(!visited[next]) {
				parent[next] = start;  // 부모노드 번호 저장
				visited[next] = true;
				dfs(next);
				visited[next] = false;
			}	
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		graph = new ArrayList<ArrayList<Integer>>();
		visited = new boolean[N + 1];
		parent = new int[N + 1];
		
		for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Integer>());
        }
		
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		dfs(1);
		
		for (int j = 2; j < N + 1; j++) {
			sb.append(parent[j] + "\n");
		}
		
		System.out.println(sb);
	}
}

/***
 * 아래 주석 코드는 인접행렬을 사용해 먼저 풀었지만 메모리 초과가 나옴
 * 저런 풀이도 있구나 하고 풀이 방법만 참고하면 좋을듯?
 */

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.StringTokenizer;
//
//public class BOJ_11725_트리의부모찾기 {
//
//	private static int N;  // 노드의 개수
//	private static int[][] graph;  // 그래프 저장 인접행렬
//	private static boolean[] visited;  // 방문여부 저장 배열
//	private static int[] parent;  // 부모노드 번호 저장 배열
//	
//	private static void dfs(int start) {
//		
//		for (int i = 1; i < N + 1; i++) {
//			if (graph[start][i] == 1 && !visited[i]) {
//				parent[i] = start;  // 부모노드 번호 저장
//				visited[i] = true;
//				dfs(i);
//				visited[i] = false;
//			}
//		}
//		
//	}
//	
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		
//		N = Integer.parseInt(br.readLine());
//		
//		graph = new int[N + 1][N + 1];
//		visited = new boolean[N + 1];
//		parent = new int[N + 1];
//		
//		for (int r = 0; r < N + 1; r++) {
//			Arrays.fill(graph[r], 0);
//		}
//		
//		for (int i = 0; i < N - 1; i++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			int a = Integer.parseInt(st.nextToken());
//			int b = Integer.parseInt(st.nextToken());
//			
//			graph[a][b] = graph[b][a] = 1;
//		}
//		
//		dfs(1);
//		
//		for (int j = 2; j < N + 1; j++) {
//			sb.append(parent[j] + "\n");
//		}
//		
//		System.out.println(sb);
//	}
//}
