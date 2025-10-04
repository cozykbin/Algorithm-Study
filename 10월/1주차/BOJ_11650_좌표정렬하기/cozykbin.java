import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());

		class Jwapyo implements Comparable<Jwapyo> { // !!!!!
			int x, y;

			Jwapyo(int x, int y) {
				this.x = x;
				this.y = y;
			}

			@Override
			public int compareTo(Jwapyo other) {
				int xCompare = Integer.compare(this.x, other.x);
				if (xCompare != 0) {
					return xCompare;
				}
				return Integer.compare(this.y, other.y);
			}
		}

		ArrayList<Jwapyo> input = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			Jwapyo line = new Jwapyo(x, y);

			input.add(line);
		}
		Collections.sort(input); // !!!!!!!!!!!!!!!1
		for (int i = 0; i < N; i++) {
			Jwapyo answer = input.get(i);
			System.out.println(answer.x + " " + answer.y);
		}

	}

}
