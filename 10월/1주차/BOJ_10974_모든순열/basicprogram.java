import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class basicprogram {
    static int N;
    static int number[];
    static boolean isSelected[];
    static int input[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        number = new int[N];
        isSelected = new boolean[N];
        input = new int[N];

        for(int i = 0; i < N; i++){
            input[i] = i + 1;
        }

        Permitation(0);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void Permitation( int cnt) {
        //기저부분
        if(cnt == N){
            for(int i = 0; i < N; i++){
                System.out.print(number[i] + " ");
            }
            System.out.println();
            return;
        }

        //유도 부분
        for(int i = 0; i < N; i++){
            if(isSelected[i]) continue;

            number[cnt] = input[i];
            isSelected[i] = true;
            Permitation(cnt + 1);
            isSelected[i] = false;
        }
    }
}
