import java.io.*;
import java.util.*;

public class Sonjuyeong {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int size = str.length();

        int[] arr = new int[26];
        char[] answer = new char[size];

        for(int i = 0;i<size;i++){
            char c = str.charAt(i);
            ++arr[c - 'A'];
        }

        int oddCnt = 0;
        int idx = 0;

        for(int i = 0;i<arr.length;i++){
            char c = (char)(i+'A');
            if(arr[i]%2==1){
                if(oddCnt==0)
                    oddCnt++;
                else{
                    System.out.println("I'm Sorry Hansoo");
                    return;
                }
                answer[size/2]=c;
            }
            for(int j = 0;j<arr[i]/2;j++){
                answer[idx]=c;
                answer[size-idx-1]=c;
                ++idx;
            }
        }

        for(int i = 0;i<size;i++){
            System.out.print(answer[i]);
        }
    }
}