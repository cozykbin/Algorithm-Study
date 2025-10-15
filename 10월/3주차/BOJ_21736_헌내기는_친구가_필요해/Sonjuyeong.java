import java.io.*;
import java.util.*;

public class Sonjuyeong {

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static class Node{
        int r;
        int c;
        public Node(int r, int c) {
            this.r=r;
            this.c=c;
        }
    }

    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] arr = new char[n][m];

        Node start = null;

        for(int i = 0;i<n;i++) {
            String str = br.readLine();
            for(int j = 0;j<m;j++) {
                arr[i][j]=str.charAt(j);
                if(arr[i][j]=='I')
                    start = new Node(i, j);
            }
        }

        int answer = 0;

        Queue<Node> q = new ArrayDeque<>();

        q.offer(start);

        while(!q.isEmpty()) {
            Node cur = q.poll();
            for(int i = 0; i<4;i++) {
                int r = cur.r+dr[i];
                int c = cur.c+dc[i];
                if(r>=0&&r<n&&c>=0&&c<m&&arr[r][c]!='X') {
                    if(arr[r][c]=='P')
                        ++answer;
                    arr[r][c]='X';
                    q.offer(new Node(r, c));
                }
            }
        }
        if(answer==0) System.out.println("TT");
        else System.out.println(answer);
    }

}