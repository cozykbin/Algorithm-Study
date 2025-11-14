import java.io.*;
import java.util.*;

public class Sonjuyeong {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        boolean[][] block = new boolean[h][w];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < w; i++) {
            int height = Integer.parseInt(st.nextToken());
            for (int j = 0; j < height; j++) {
                block[h - j - 1][i] = true;
            }
        }

        int answer = 0;

        for (int i = h - 1; i >= 0; i--) {
            int temp = 0;
            boolean blocked = false;
            for (int j = 0; j < w; j++) {
                if (block[i][j]) {
                    blocked = true;
                    answer += temp;
                    temp = 0;
                } else {
                    if (blocked)
                        ++temp;
                }
            }
        }

        System.out.println(answer);
    }
}