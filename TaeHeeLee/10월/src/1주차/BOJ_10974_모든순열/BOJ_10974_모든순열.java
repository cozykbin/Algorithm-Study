import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_10974_모든순열 {

	private static int N;  // 주어진 수
	private static int[] numbers;  // 1부터 N까지 숫자 저장 배열
	private static int[] selected;  // 선택된 수들 저장 배열
	private static boolean[] visited;  // 방문 여부 저장
	private static StringBuilder sb = new StringBuilder();
	
	private static void permutation(int cnt) {
		// 기저 조건
		if (cnt == N) {
			for (int i = 0; i < N; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
			return;
		}
		
		// 유도 부분
		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;
			
			selected[cnt] = numbers[i];
			visited[i] = true;
			permutation(cnt + 1);
			visited[i] = false;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		selected = new int[N];
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			numbers[i] = i + 1;
		}
		
		permutation(0);
		
		System.out.println(sb);
		
	}
}
