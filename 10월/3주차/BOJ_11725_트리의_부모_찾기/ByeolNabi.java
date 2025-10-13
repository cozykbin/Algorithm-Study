package solution.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S2_11725 {
	static StringTokenizer st;
	static List<Integer>[] g;
	static int[] visited;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		g = new ArrayList[n + 1];
		visited = new int[n + 1];
//		for (List<Integer> l : g) {
//			l = new ArrayList<Integer>();
//		}

		for (int i = 0; i < n + 1; i++) {
			g[i] = new ArrayList<Integer>();
		}
		parents = new int[n + 1];

		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			g[p].add(c);
			g[c].add(p);
		}

		bfs(1);

		for (int i = 2; i < n + 1; i++) {
			System.out.println(parents[i]);
		}

	}

	public static void bfs(int crt_node) {
		visited[crt_node] = 1;
		for (int nxt_node : g[crt_node]) {
			if (visited[nxt_node] == 0) {
//				System.out.println(crt_node + "->" + nxt_node);
				parents[nxt_node] = crt_node;
				bfs(nxt_node);
			}
		}
	}
}

// 들어오는건 양방향그래프인데 이걸 어떻게 트리처럼 방향을 생각할것인가...