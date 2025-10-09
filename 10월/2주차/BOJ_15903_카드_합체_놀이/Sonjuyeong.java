import java.io.*;
import java.util.*;

public class Sonjuyeong {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Queue<Long> q = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());

        for(int i = 0;i<n;i++){
            long t = Integer.parseInt(st.nextToken());
            q.offer(t);
        }

        for(int i = 0; i < m;i++){
            long n1 = q.poll();
            long n2 = q.poll();
            q.offer(n1+n2);
            q.offer(n1+n2);
        }

        long answer = 0;
        while(!q.isEmpty()){
            answer+=q.poll();
        }
        System.out.println(answer);
    }
}