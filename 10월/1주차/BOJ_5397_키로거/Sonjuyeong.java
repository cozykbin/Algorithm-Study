import java.io.*;
import java.util.*;

public class Sonjuyeong {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= n; tc++) {
            String str = br.readLine();
            int size = str.length();
            char[] preStack = new char[size];
            char[] postStack = new char[size];
            int preTop = -1;
            int postTop = -1;

            for (int i = 0; i < size; i++) {
                char c = str.charAt(i);
                if (c == '<') {
                    if (preTop >= 0) {
                        postStack[++postTop] = preStack[preTop--];
                    }
                } else if (c == '>') {
                    if (postTop >= 0) {
                        preStack[++preTop] = postStack[postTop--];
                    }
                } else if(c=='-'){
                    if(preTop>=0)
                        --preTop;
                }
                else {
                    preStack[++preTop]=c;
                }
            }
            for(int i = 0;i<=preTop;i++){
                sb.append(preStack[i]);
            }
            for(int i = postTop;i>=0;i--){
                sb.append(postStack[i]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}