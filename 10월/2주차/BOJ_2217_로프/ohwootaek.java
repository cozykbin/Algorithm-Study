import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class ohwootaek {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        Integer rope[] = new Integer[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            rope[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(rope, Comparator.reverseOrder());

        int max = Integer.MIN_VALUE;

        for(int i = 0; i < N; i++){ // 로프개슈만큼
            max = Math.max(max, rope[i] * (i + 1));
        }

        bw.write(max + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
//내림차순 방법이 기억이 나질 않아서 https://hianna.tistory.com/922 블로그를 참고하였습니다.