package BOJ_14888_연산자끼워넣기;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1213_펠린드롬만들기 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String s = br.readLine();  // 문자열 입력받기
		int[] arr = new int[26];  // 입력 받은 문자열의 각 알파벳 빈도수
		
		// 입력받은 알파벳을 숫자로 매핑
		for (int i = 0; i < s.length(); i++) {
			int idx = s.charAt(i) - 'A';
			arr[idx]++;
		}
		
		
		// 알파벳의 개수가 홀수인 경우가 2개 이상이면 불가능
		int odd = 0;  // 홀수인 경우 수
        int num = 0;  // 홀수인 경우의 수를 가진 알파벳
        for (int i = 0; i < arr.length; i++) {
            // 홀수인 경우의 수 찾기
        	if (arr[i] % 2 != 0) {
                odd++;
                num = i;
            }
        	// 불가능
            if (odd >= 2) {
                System.out.println("I'm Sorry Hansoo");
                return;
            }
        }
        
        // 알파벳의 빈도수의 절반만 추가
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i] / 2; j++) {
                sb.append((char)(i + 'A'));
            }
        }
        
        String ans;
        if (odd == 1) {
            ans = sb.toString() + (char)(num + 'A')+ sb.reverse().toString();
        } else {
        	ans = sb.toString() + sb.reverse().toString();
        }

        System.out.println(ans);

	}

}
