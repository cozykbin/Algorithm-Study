import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class ohwootaek {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 공
        int K = Integer.parseInt(st.nextToken()); // 바구니
        int bk[] = new int[K];

        int minball = 0;
        for(int i = 1; i <= K; i++){
            bk[i-1] = i;
            minball += i;
        }

        if(minball > N){
            bw.write(-1 + "");
            bw.flush();
            return;
        } // 최소 공 개수보다 작으면 끝내라

        int r_ball = N - minball; // 남은 공 개수

        // 남은 공 분배
        int q = r_ball / K;
        int r = r_ball % K;

        // 균등하게 모든 바구니에 더한다.
        for(int i = 0; i < K; i++){
            bk[i] += q;
        }

        for(int i = K - 1; i >= K - r; i--){
            bk[i]++;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < K; i++) {
            max = Math.max(max, bk[i]);
            min = Math.min(min, bk[i]);
        }

        bw.write((max - min) + "");
        bw.flush();
        br.close();
        bw.close();
    }
}
