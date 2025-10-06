package Week01;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_28702_FizzBuzz {
	
	// 문제의 기본 조건
	private static String FizzBuzz(int num) {
		if (num % 3 == 0 && num % 5 == 0) return "FizzBuzz";
		else if (num % 3 == 0 && num % 5 != 0) return "Fizz";
		else if (num % 3 != 0 && num % 5 == 0) return "Buzz";
		else return Integer.toString(num);
	}
	
	// 숫자인지 판별하는 메서드
	// 피즈버즈, 피즈, 버즈가 아니라면 숫자임!
	private static boolean isNum(String s) {
		if (s.equals("FizzBuzz") || s.equals("Fizz") || s.equals("Buzz")) return false;
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s1 = br.readLine();
		String s2 = br.readLine();
		String s3 = br.readLine();
		
		int num;
		
		// 입력받은 세 개의 문자열을 숫자인지 분석 후,
		// 숫자를 발견했다면 해당 문자열 인덱스를 통해 4번째 숫자 구하기
		if (isNum(s1)) num = Integer.parseInt(s1) + 3;
		else if (isNum(s2)) num = Integer.parseInt(s2) + 2;
		else num = Integer.parseInt(s3) + 1;
		
		System.out.println(FizzBuzz(num));	
		
	}
}
