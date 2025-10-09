import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class ohwootaek{
    static int s_cnt = 0;
    static int N, S;
    static boolean isSelected[];
    static int SubsetNum[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 5
        S = Integer.parseInt(st.nextToken()); // 0

        isSelected = new boolean[N];
        SubsetNum = new int[N];
        s_cnt = 0;

        st = new StringTokenizer(br.readLine()); // -7 -3 -2 5 8
        for(int i = 0; i < N; i++) {
            SubsetNum[i] = Integer.parseInt(st.nextToken());
        }

        subset(0);

        bw.write(s_cnt + "\n"); // 아무것도 안골랐을 경우를 뺴면된다

        bw.flush();
        br.close();
        bw.close();
    }

    private static void subset(int cnt) {
        // 기저부분
        if(cnt == N){
            int zero = 0;
            for(int i = 0; i < N; i++){
                if(!isSelected[i]){
                    zero++;
                }
                if(zero == N) return;
            }

            int sum = 0;
            for(int i = 0; i < N; i++){
                if(isSelected[i]){
                    sum += SubsetNum[i];
                }
            }
            if(sum == S) s_cnt++;
            return;
        }

        // 유도부분
        isSelected[cnt] = true;
        subset(cnt + 1);

        isSelected[cnt] = false;
        subset(cnt + 1);
    }
}

//250804W의 SubsetInputTest를 한번 읽어본 후 문제를 풀었습니다.
