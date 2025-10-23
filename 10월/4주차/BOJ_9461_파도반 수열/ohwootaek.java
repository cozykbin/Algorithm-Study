import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int temp[] = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			temp[i] = Integer.parseInt(st.nextToken());
		}
		
		long[] tri = new long[100 + 1];
		tri[1] = tri[2] = tri[3] = 1;
		tri[4] = tri[5] = 2;
		
		for(int i = 6; i <= 100; i++) {
			tri[i] = tri[i - 1] + tri[i - 5];
		}
		
		for(int i = 1; i <= N; i++) {
			bw.write(tri[temp[i]] + "\n");
		}
		

		bw.flush();
		bw.close();
		br.close();
	}
}