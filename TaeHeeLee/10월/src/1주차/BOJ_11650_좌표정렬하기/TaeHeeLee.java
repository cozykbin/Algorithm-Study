package Week01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11650_좌표정렬하기 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());  // x좌표
			arr[i][1] = Integer.parseInt(st.nextToken());  // y좌표
		}
		
		// 비교
        Arrays.sort(arr, (a, b) -> {
        	// x가 다르면 x좌표 비교
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            // x가 같다면 y좌표 비교
            return Integer.compare(a[1], b[1]);
        });

        // 출력
        for (int i = 0; i < N; i++) {
            sb.append(arr[i][0]).append(' ').append(arr[i][1]).append('\n');
        }
        
        System.out.print(sb);
	
	}

}
