import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_18353_병사배치하기 {
    static int N, H;
    static int[] D;
    static int[] input;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        D = new int[N];
        input = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(D, 1);

        int ans = 0;
        for(int i = N - 2; i >= 0; i--) {
            for(int j = N - 1; j > i; j--) {
                if(input[i] > input[j]) {
                    D[i] = Math.max(D[i], D[j] + 1);
                    //D[i] = input[i];
                }
            }
        }

//		for(int i = 0; i < N; i++) {
//			bw.write(D[i] + " ");
//		}

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            max = Math.max(max, D[i]);
        }

        ans = N - max;

        bw.write(ans + "\n");

        bw.flush();
        br.close();
        bw.close();
    }
}
