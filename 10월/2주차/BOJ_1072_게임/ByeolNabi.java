import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		long T, W;
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		// 승률이 이미 100퍼면 -1 출력
		if (T == W) {
			System.out.println(-1);
			return;
		}

		long Z;
		Z = ((W * 100) / T);
//		System.out.println(W * 100);
//		System.out.println(T);
//		System.out.println(((W * 100) / T));

		// 99퍼면 아무리 열심히 이겨도 100퍼가 될 수 없어
		if (Z == 99) {
			System.out.println(-1);
			return;
		}

		long left, right, middle;
		left = 0;
		right = T + 1;

		while (left + 1 != right) {
			middle = (left + right) / 2;

			long percent = ((W + middle) * 100) / (T + middle);

			if (percent <= Z) {
				left = middle;
			} else {
				right = middle;
			}
		}

		System.out.println(right);
	}
}
// 현재 퍼센트에서 +1 퍼센트가 되기 위한 x값을 방정식으로 만들었다.
// 1.9인 상황에서 2로 넘어가는 상황에서 방정식은 정직하게 1->2로 가는 값을 구해버린다.
// 이분탐색이 맞다~

// Z인 구간 + 1을 정답으로 고르자.
// Z이하인 구간 true, Z초과인 구간 false => 가장 작은 false 
// long!!!!