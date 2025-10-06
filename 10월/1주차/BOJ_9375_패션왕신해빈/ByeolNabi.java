package playground;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			int N = Integer.parseInt(br.readLine());
			Map<String, Integer> dict = new HashMap<String, Integer>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();

				String group = st.nextToken();
				dict.put(group, dict.getOrDefault(group, 1) + 1);

			}

			Collection<Integer> li = dict.values();
			
//			System.out.println(li);
			int total_mul = 1;
			for(int num : li) {
				total_mul *= num;
			}
			
			System.out.println(total_mul-1);

		}
	}
}

// 옷 분류가 몇 번 호출되었는지 확인하기;
// dictionary를 이용해서 cnt하기 -> 나중에 value풀어서 경우의 수 계산하기
// Map을 통한 count를 위해서는 getOrDefault가 가장 중요한 메서드였다!!!