import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static long[][] D;
    static final long MOD = 1_000_000_007L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        D = new long[N + 1][M + 1];
        for (int i = 0; i <= N; i++) Arrays.fill(D[i], -1);

        long ans = path(1, 1);
        System.out.println(ans % MOD);
    }

    private static long path(int x, int y) {
        if (x == N && y == M) return 1;
        if (x > N || y > M) return 0;
        if (D[x][y] != -1) return D[x][y];

        long res = 0;
        res = (res + path(x + 1, y)) % MOD;     
        res = (res + path(x, y + 1)) % MOD;     
        res = (res + path(x + 1, y + 1)) % MOD; 

        return D[x][y] = res;
    }
}