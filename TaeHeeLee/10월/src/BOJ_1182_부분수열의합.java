import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1182_부분수열의합 {
	
	private static int N;
	private static int S;
	private static int[] arr;
	private static int ans;

	private static void dfs(int cnt, int val) {
		
		if (cnt == N) {
			if (val == S) {
				ans++;
			}
			return;
		}
		
		// 선택
		dfs(cnt + 1, val + arr[cnt]);
		// 선택 X
		dfs(cnt + 1, val);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		ans = 0;
		dfs(0, 0);
		
		// 공집합 처리
		if (S == 0) System.out.println(ans - 1); 
        else  System.out.println(ans);
		
	}
}
