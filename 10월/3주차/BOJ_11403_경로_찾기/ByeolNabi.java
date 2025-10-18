package solution.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B4_21964 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N;
		N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 플로이드 워셜은 3중 포문을 사용합니다.  (k, i, j)순서의 for문
		// 아무튼 for문 3개를 이용하면 모든 노드와의 연결을 확인한다는 아이디어를 가지고 사용했습니다.
		// D[i][j] = min(D[i][j], D[i][k] + D[k][j]); 가 원래 최소거리를 구하기 위한 공식입니다.
		// 그러나 우리는 연결 확인만 하면 되기 때문에 둘 중 하나라도 1이면 연결되어있다고 1을 저장했습니다.
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// !! D[i][j] = min(D[i][j], D[i][k] + D[k][j]); 까진 필요없다
					if ((arr[i][k] == 1 && arr[k][j] == 1) || arr[i][j] == 1) { 
						arr[i][j] = 1;
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}
}
