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
        int N = Integer.parseInt(st.nextToken()); //도시 개수
        int L = N - 1;

        int gas_station[] = new int[N];
        int load_length[] = new int[N-1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < L; i++){
            load_length[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            gas_station[i] = Integer.parseInt(st.nextToken());
        }

        long m_cost = gas_station[0];
        long result = 0;

        for(int i = 0; i < N - 1; i++){
            m_cost = Math.min(m_cost, gas_station[i]);
            result += (m_cost * load_length[i]);
        }

        bw.write(result + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
//일부분만 정답(58점)이 나왔고 조건3 42점 원래의 제약조건 이외에 아무 제약조건이 없다.라는 조건을 통과시키지 못하여 AI를 사용하였고 
//result 결과 뿐만 아니라 m_cost도 long으로 선언하지 안으면 33번줄에서 오버플로우가 나온다는 것을 알면서 long m_cost으로 수정하여 100점 점답이 되었습니다

