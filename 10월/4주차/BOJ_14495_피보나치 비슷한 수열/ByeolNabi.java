import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static long[] l = new long[117];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		l[1] = 1;
		l[2] = 1;
		l[3] = 1;

		int n = Integer.parseInt(br.readLine());
		if (n <= 3) {
			System.out.println(l[n]);
		} else {
			for (int i = 4; i <= n; i++) {
				l[i] = l[i - 1] + l[i - 3];
			}
			System.out.println(l[n]);
		}

	}
}
