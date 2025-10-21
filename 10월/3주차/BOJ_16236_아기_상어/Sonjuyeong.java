import java.io.*;
import java.util.*;

public class Sonjuyeong {

    //아기상어 클래스. 상어 위치, 크기, 초를 필드로 갖고 있음.
    static class Shark {
        int r;
        int c;
        int size;
        int sec;
        int cnt;

        public Shark(int r, int c, int size, int sec, int cnt) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.sec = sec;
            this.cnt = cnt;
        }
    }

    //정답으로 가질 상어.
    static Shark shark;

    //초. 정답이 될 변수.
    static int sec;
    //가까운 고기가 많담녀 가장 가장 위에 있는 물고기를 골라야 하므로, 위부터 탐색
    //그 다음으로 왼쪽이 우선순위이므로 왼쪽, 오른쪽, 아래 탐색
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};

    //공간의 크기
    static int N;

    //값일 입력받을 이차원 배열
    static int[][] arr;

    //bfs. return은 boolean. 먹을 수 있는 물고기가 있는지 없는 지 확인.
    //return이 false면 먹는 걸 종료하게 될거임.
    public static boolean bfs() {
        Queue<Shark> q = new ArrayDeque<>();
        q.offer(new Shark(shark.r, shark.c, shark.size, 0, shark.cnt));
        arr[shark.r][shark.c] = 0;
        boolean[][] visited = new boolean[N][N];
        visited[shark.r][shark.c] = true;

        //임의의 temp를 두기... 같은 sec에서 우선순위에 가까운 애로 갱신할 예정.
        Shark temp = new Shark(Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, shark.cnt);

        while (!q.isEmpty()) {
            Shark cur = q.poll();
            if (temp.sec <= cur.sec)
                break;
            for (int i = 0; i < 4; i++) {
                int r = cur.r + dr[i];
                int c = cur.c + dc[i];
                if (r >= 0 && r < N && c >= 0 && c < N && !visited[r][c] && arr[r][c] <= cur.size) {
                    if (arr[r][c] == cur.size || arr[r][c] == 0) {
                        visited[r][c] = true;
                        q.offer(new Shark(r, c, cur.size, cur.sec + 1, cur.cnt));
                    } else {
                        //우선순위에 걸맞으면 temp를 갱신
                        if (temp.sec >= cur.sec + 1) {
                            if (temp.r > r || (temp.r == r && temp.c > c)) {
                                temp.r = r;
                                temp.c = c;
                                temp.sec = cur.sec + 1;
                            }
                        }
                    }
                }
            }
        }

        //temp 갱신이 안 된 거 -> 물고기를 찾지 못한 경우
        if (temp.r == Integer.MAX_VALUE)
            return false;
            //물고기를 찾은 경우
        else {
            shark.r = temp.r;
            shark.c = temp.c;
            shark.sec += temp.sec;
            arr[shark.r][shark.c] = 9;
            if (shark.size == ++shark.cnt) {
                shark.cnt = 0;
                ++shark.size;
            }
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                    shark = new Shark(i, j, 2, 0, 0);
                }
            }
        }

        //물고기를 먹을 수 없으면 bfs 중단.
        while (true) {
            if (!bfs())
                break;
        }

        System.out.println(shark.sec);
    }
}