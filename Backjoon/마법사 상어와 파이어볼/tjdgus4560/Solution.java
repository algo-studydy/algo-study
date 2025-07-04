import java.util.*;

public class Main {
    static class Point {
        int r, c, m, s, d;

        Point(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m; //질량
            this.s = s; //속력(이동칸수)
            this.d = d; //방향
        }
    }

    static int N; //맵크기
    static int M; //볼갯수
    static int K; //이동명령수
    static List<Point>[][] map;
    static List<Point> list; // 파이어볼 담을 리스트

    // 8방향
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        list = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            int m = sc.nextInt();
            int s = sc.nextInt();
            int d = sc.nextInt();

            list.add(new Point(r, c, m, s, d));
        }

        // K번 수행
        for (int t = 0; t < K; t++) {
            // 이동
            move();

            // 합치고 나누기
            mergeAndSplit();
        }

        int ans = 0;
        for (Point p : list) {
            ans += p.m;
        }
        System.out.println(ans);
    }

    static void move() {
        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (Point p : list) {
            int nr = (p.r + dr[p.d] * p.s);
            int nc = (p.c + dc[p.d] * p.s);

            // 외곽으로 나가면 처리
            while (nr < 0) nr += N;
            while (nr >= N) nr -= N;
            while (nc < 0) nc += N;
            while (nc >= N) nc -= N;

            map[nr][nc].add(new Point(nr, nc, p.m, p.s, p.d));
        }

    }

    static void mergeAndSplit() {
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                /**
                 * 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
                 * 파이어볼은 4개의 파이어볼로 나누어진다.
                 * 나누어진 파이어볼의 질량m, 속력s, 방향d은 다음과 같다.
                 * 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다.
                 * 속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
                 * 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
                 * 질량이 0인 파이어볼은 소멸되어 없어진다.
                 */

                if(map[i][j].isEmpty()){
                    continue;
                }


                if(map[i][j].size() == 1){
                    list.add(map[i][j].get(0));
                    continue;
                }
                int sumM = 0;
                int sumS = 0;
                boolean allEven = true; //짝수
                boolean allOdd = true; //홀수

                // 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다
                for(Point p : map[i][j]){
                    sumM += p.m;
                    sumS += p.s;
                    if (p.d % 2 == 0) {
                        allOdd = false;
                    } else {
                        allEven = false;
                    }
                }

                // 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다.
                int newM = sumM / 5;
                // 질량이 0인 파이어볼은 소멸되어 없어진다.
                if(newM == 0){
                    continue;
                }

                // 속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
                int newS = sumS / map[i][j].size();

                // 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
                if(allEven || allOdd){
                    int d =0;
                    for(int t=0; t<4; t++){
                        list.add(new Point(i,j, newM, newS, d));
                        d+=2;
                    }
                }else{
                    int d =1;
                    for(int t=0; t<4; t++){
                        list.add(new Point(i,j, newM, newS, d));
                        d+=2;
                    }
                }
            }
        }
    }
}
