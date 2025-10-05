import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(in.readLine());
			Map<String, Integer> map = new HashMap<>();

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				String clo = st.nextToken();
				String type = st.nextToken();

				if (!map.containsKey(type)) {
					map.put(type, 1);
				} else if (map.containsKey(type)) {
					int cnt = map.get(type) + 1;
					map.replace(type, cnt);
				}
			}

			int answer = 1;
			for (int count : map.values()) {
				answer *= count + 1;
			}
			answer -= 1;
			sb.append(answer + "\n");

		}
		System.out.println(sb);
	}

}

// 항상 필요한 값만 입력 받아도 된다는 생각 하기. 불필요한 정보는 버리자.
// .value() 로 저렇게 꺼내서 쓸 수 있구나
//사실 그것보다 저놈의 향상된 for문 이 익숙해지지가 않아서 while 로 해보려다가 고생만 했다.
//의식적으로 생각하고 써봐야지...
