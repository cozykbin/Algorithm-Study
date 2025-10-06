package 브론즈박살;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class M_28702_FizzBuzz {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] input = new String[3];
		boolean[] isNumeric = new boolean[3];
		int answerNum = 0;

		for (int i = 0; i < 3; i++) {
			input[i] = in.readLine();
			isNumeric[i] = input[i].chars().allMatch(Character::isDigit);
		}
		if (isNumeric[0]) {
			answerNum = Integer.parseInt(input[0]) + 3;
		} else if (isNumeric[1]) {
			answerNum = Integer.parseInt(input[1]) + 2;
		} else if (isNumeric[2]) {
			answerNum = Integer.parseInt(input[2]) + 1;
		}

		if (answerNum % 3 == 0 && answerNum % 5 == 0) {
			System.out.println("FizzBuzz");
			return;
		} else if (answerNum % 3 != 0 && answerNum % 5 == 0) {
			System.out.println("Buzz");
			return;
		} else if (answerNum % 3 == 0 && answerNum % 5 != 0) {
			System.out.println("Fizz");
			return;
		} else {

			System.out.println(answerNum);
		}

	}
}
