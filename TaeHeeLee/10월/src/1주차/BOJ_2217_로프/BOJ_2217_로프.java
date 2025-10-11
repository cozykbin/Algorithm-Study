import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2217_로프 {

	private static int N;
	private static int[] W;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		W = new int[N];
		
		for(int i = 0; i < N; i++) {
			W[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(W);
		
		int ans = 0;
		
		 // 완전 탐색으로 최대 중량 찾기
		for (int i = 0; i < N; i++) {
            int val = W[i] * (N - i);
            if (val > ans) ans = val;
        }
		
		System.out.println(ans);
	}
}
