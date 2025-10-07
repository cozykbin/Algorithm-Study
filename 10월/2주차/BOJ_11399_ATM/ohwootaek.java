import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ohwootaek {
    public static void main(String[] args)  throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int person[] = new int[N];
        int sum = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            person[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(person);

        for(int j = 0; j < N; j++){
            int s_num = 0;
            for(int k = 0; k <= j; k++){
                s_num += person[k];
            }
            sum += s_num;
        }

        bw.write(sum + "\n");

        bw.flush();
        br.close();
        bw.close();

    }
}
