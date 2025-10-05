import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class basicprogram {
    static class Node{
        int x, y, num;
        Node(int x, int y, int num){
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    static int N;
    static char [][]map;
    static List<Node> num_location;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 4

        map = new char[N][N];
        num_location = new ArrayList<>();

        Node start[] = new Node[1]; // 자리는 무조건 1개씩
        Node end[] = new Node[1];


        for(int i = 0; i < N; i++){
//            st = new StringTokenizer(br.readLine());
            String temp = br.readLine();
            for(int j = 0; j < N; j++){
//                map[i][j] = st.nextToken().charAt(0);
                map[i][j] = temp.charAt(j);

                if(map[i][j] == 'S'){
                    start[0] = new Node(i, j,map[i][j]);
                } // 시작위치

                if(map[i][j] == 'E'){
                    end[0] = new Node(i, j, map[i][j]);
                } // 도착 위치

                int check = map[i][j] - '0'; // 숫자인지 판단하는 check
                if(check > 0 && check < 10){
                    num_location.add(new Node(i, j, map[i][j])); //숫자인 곳의 좌표를 추가한다
                }
            }
        }

        int result = Integer.MAX_VALUE; // 이 값이 그대로 출력됬다는 소리인데

        int coint_cnt = num_location.size();
        for(int i = 0; i < coint_cnt; i++){
            for(int j = i+1; j < coint_cnt; j++){
                for(int k = j+1; k < coint_cnt; k++){
                    //num_location 여기서 3개를 뽑아서 거리를 구하고 거리의 합으로 최솟값인 것을 구하고 최솟값 리턴한다
                    // 숫자를 큰 순서대로 이동해야된다

                    //선택한 숫자 여기에 담아준다 3개
                    Node newnumList[] = {num_location.get(i), num_location.get(j), num_location.get(k)};
                    Arrays.sort(newnumList, (a, b)-> a.num - b.num); // 3개를 뽑아서 순서대로 거리를 구하기 위해서 나열해준다

                    int dist_num = 0;
                    dist_num += Math.abs(start[0].x - newnumList[0].x) + Math.abs(start[0].y - newnumList[0].y);
                    dist_num += Math.abs(newnumList[0].x - newnumList[1].x) + Math.abs(newnumList[0].y - newnumList[1].y);
                    dist_num += Math.abs(newnumList[1].x - newnumList[2].x) + Math.abs(newnumList[1].y - newnumList[2].y);
                    dist_num += Math.abs(newnumList[2].x - end[0].x) + Math.abs(newnumList[2].y - end[0].y);
                    result = Math.min(result, dist_num);

                }
            }
        }

        if(coint_cnt < 3){
            bw.write(-1 + "\n");
        }
        else{
            bw.write(result + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
;