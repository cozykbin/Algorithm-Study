import java.io.*;
import java.util.*;

class Sonjuyeong {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> plus = new ArrayList<>();
        List<Integer> minus = new ArrayList<>();
        int zeroCnt = 0;

        for(int i = 0;i<n;i++){
            int t = Integer.parseInt(br.readLine());
            if(t>0)
                plus.add(t);
            else if(t<0)
                minus.add(t);
            else ++zeroCnt;
        }

        Collections.sort(plus);
        Collections.sort(minus);

        int answer = 0;

        for(int i = plus.size()-1;i>0;i-=2){
            answer+= Math.max(plus.get(i)*plus.get(i-1), plus.get(i)+plus.get(i-1)) ;
        }

        if(plus.size()%2==1)
            answer+=plus.get(0);

        for(int i = 0;i<minus.size()-1;i+=2){
            answer+=minus.get(i)*minus.get(i+1);
        }

        if(minus.size()%2==1&&zeroCnt==0){
            answer += minus.get(minus.size() - 1);
        }

        System.out.println(answer);
    }
}

