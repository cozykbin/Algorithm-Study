import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20152_GameAddiction {

	private static final int M = 30;
	private static long[][] dp = new long[M + 1][M + 1];
	private static int H;
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		if (H > N) {
			int tmp = H;
			H = N;
			N = tmp;
		}

		dp[H][H] = 1;
		//  dp[H + 1][H] = 1;
		//  dp[H + 1][H + 1] = 1;
		//  dp[H + 2][H] = 1;
		for (int i = H; i <= N; i++) {
			for (int j = H; j <= N; j++) {
				if (i < j) continue;
				if (i > H) dp[i][j] += dp[i - 1][j];
				if (j > H) dp[i][j] += dp[i][j - 1];
			}
		}

		System.out.println(dp[N][N]);
	}
}


//  * x x x
//  * * x x
//  * * * x
//  * * * *
// 아 점화식 나쁘지 않게 작성한 것 같은데 테스트케이스 1번부터 틀려서
// 초기값 설정에만 신경쓰고 있었다.
// 테스트케이스 2번을 다시 돌려보니 잘 돌아가는 것 같다.
// 테스트케이스와 내 코드를 다시 보니, H > N일 경우를 처리를 해야할 듯 하다.
// 어떻게 처리를하지?
// 감소반복문을 하나 더 만들어야할 듯하다. 넘 귀찮다.
// 만들면서 생각해보니 감소반복문을 하나 더 만들바에 H랑 N을 바꿔주면 될 듯 하다.
// 어? 제출해봤는데 틀렸네?
// 무엇이 잘못된걸까? H랑 N을 바꾼게 문제일까? 초기값이 문제일까? 점화식이 잘못된걸까?

// 문제점:
// 경로의 개수를 찾는 문제라 범위가 int를 넘어가지 않을 것이라고 생각하여 dp배열을 int형으로 생성했었다.
// 대규님의 조언을 듣고 dp가 우숩냐. long타입으로 바꿔봐라 해서 long타입으로 변경하니. 통과했습니다.