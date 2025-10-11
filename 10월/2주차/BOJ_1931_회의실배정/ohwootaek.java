import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class ohwootaek {
    static class Node{
        int s, e;

        Node(int s, int e){
            this.s = s;
            this.e = e;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        Node[] m_room = new Node[N];
        List<Node> selected = new ArrayList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            m_room[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(m_room, (a, b) ->{
            if(a.e == b.e) return a.s - b.s;
            return a.e - b.e;
        }); // 예시의 경우는 정렬이 되어있지만 안되어있을 경우를 대비해서
        //https://pixx.tistory.com/236

        selected.add(m_room[0]); // 나열했기 때문에 첫번쨰를 넣어준다

        int cnt = 0;
        int lastEnd = 0;

        for(Node m : m_room){ //모든 회의를 순서대로 확인
            if(m.s >= lastEnd){ // 현재 회의의 시작시간이 직전 회의 종료시간 이상이면 들어가고
                cnt++; // 개수 늘려주고
                lastEnd = m.e; // 마지막 회의 종료시간을 바꿔준다
            }
        }

        //오류 + 시간초과
//        while(true){
//            Node now = selected.get(selected.size() - 1);
//            int start_num = now.e;
//
//            int checking = 0;
//
//            for(int i = 0; i < N; i++){
//                if(m_room[i].s > start_num){
//                    selected.add(m_room[i]);
//                    checking = i;
//                    break;
//                }
//            }
//
//            if(checking == N - 1){
//                break;
//            }
//        }

        // 오류가 나오는 종료조건을 변경했지만 메모리초과
        //        while (true) {
//            Node now = selected.get(selected.size() - 1);
//            int start_num = now.e;
//
//            boolean added = false; // 회의 추가 여부 체크
//
//            for (int i = 0; i < N; i++) {
//                if (m_room[i].s >= start_num) { // 겹치지 않는 회의 발견
//                    selected.add(m_room[i]);
//                    added = true;
//                    break;
//                }
//            }
//
//            if (!added) break; // 이번 루프에서 아무 회의도 추가 못 했으면 종료
//        }
//
//        System.out.println(selected.size());

        System.out.println(cnt);

        br.close();
    }
}
