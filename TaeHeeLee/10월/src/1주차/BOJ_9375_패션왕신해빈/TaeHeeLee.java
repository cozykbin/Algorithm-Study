package Week01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/***
 * 해시맵 사용에 익숙하지 않다면 쉽게 풀 수 있을 것 같지 않습니다.
 * 혼자서 약 한시간 가량 조합을 사용해 풀어보려 했지만,
 * 결국 구글링을 하여 해시맵을 다루는 함수들과
 * 문제 풀이 방법을 살짝 보며 풀었습니다...
 * 그래도 해시맵에 대해 더 자세히 알아가는 것 같아 좋았습니다.
 */


public class BOJ_9375_패션왕신해빈 {
	
	private static int T;  // 테스트케이스 수
	private static int N;  // 의상 수
	private static Map<String, Integer> fashion;  // 의상 종류별 의상 수 저장 맵
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			fashion = new HashMap<>();
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();  // 의상 종류별 의상 수만 알면되기때문에 스킵
				String s = st.nextToken();  // 의상종류
				
				// 입력받은 의상 타입에 +1
				fashion.put(s, fashion.getOrDefault(s, 0) + 1);
			}
			
			
			int ans = 1;
            for (int n : fashion.values()) {
            	ans *= n + 1;  // 옷 종류별 n개의 의상에서 고르기, 고르지 않기
            }
            ans--;  // 아무것도 고르지 않았을 때의 경우 빼주기
            
            sb.append(ans + "\n");
		}
		
		System.out.println(sb);
	}
}
