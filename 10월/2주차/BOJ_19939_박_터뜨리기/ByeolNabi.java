import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N, K;
		N = Integer.parseInt(st.nextToken()); // 공 갯수
		K = Integer.parseInt(st.nextToken()); // 바구니 갯수

		int thres = (int) (((float) (K + 1) / 2) * K);
		int result = -1;
		if(N < thres) {
		} else {
			if((N-thres) % K == 0) {
				result = K-1;
			}else {
				result = K;
			}
		}
		System.out.println(result);
	}
}