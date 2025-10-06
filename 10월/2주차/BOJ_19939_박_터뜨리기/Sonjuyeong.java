import java.io.*;
import java.util.*;

public class Sonjuyeong {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int min = 0;
        for(int i = 1;i<=k;i++){
            min+=i;
        }

        if(n<min){
            System.out.println(-1);
            return;
        }

        int m = n-min;
        System.out.println(k-1+(m%k==0?0:1));
    }
}