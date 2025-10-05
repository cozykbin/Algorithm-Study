import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1759암호만들기 {
    static int L, C;
    static char[] c_alpha;
    static List<String> cipher;
    static List<String> temp;
    static boolean isSelected[];
    static char alpha[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        c_alpha = new char[C];
        cipher = new ArrayList<String>();
        temp = new ArrayList<String>();
        isSelected = new boolean[C];
        alpha = new char[L];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++){
            c_alpha[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(c_alpha);

        combination(0, 0);

//        bw.write(temp.size() + "\n");

        checking(temp);

        for(int i = 0; i < cipher.size(); i++){
            bw.write(cipher.get(i) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void checking(List<String> temp) {
        for (String check : temp) {
            boolean flag = true;
            for (int j = 1; j < check.length(); j++) { // 무조건 4번을 반복
                if (check.charAt(j - 1) > check.charAt(j)) {
                    flag = false;
                    break;
                }
            }

            if (!flag) continue;

            int v_cnt = 0;
            int c_cnt = 0;

            for (int k = 0; k < check.length(); k++) {
                char c = check.charAt(k);
                if ("aeiou".indexOf(c) != -1) {
                    v_cnt++; // 모음 증가
                } else {
                    c_cnt++; // 자음 증가
                }
            }

            if (v_cnt > 0 && c_cnt > 1 && flag) {
                cipher.add(check);
            }

        }
    }

    private static void combination(int cnt, int start) {
        //기저부분
        if(cnt == L) {
            StringBuilder sb = new StringBuilder();
            for(int s = 0; s < L; s++){
                sb.append(alpha[s]);
            }
            temp.add(sb.toString());
            return;
        }

        //유도부분
        for(int i = start; i < C; i++){
            alpha[cnt] = c_alpha[i];
            combination(cnt + 1, i + 1);
        }
    }
}
