import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		long[] tri = new long[116 + 1];
		tri[1] = tri[2] = tri[3] = 1;
		tri[4] = 2;
		
		for(int i = 5; i <= N; i++) {
			tri[i] = tri[i - 1] + tri[i - 3];
		}
		
		
		bw.write(tri[N] + "\n");

		bw.flush();
		bw.close();
		br.close();
	}
}