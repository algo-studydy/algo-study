
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static ArrayList<Integer>[][] map;
    static Point[] move;
    static StringTokenizer st;

    // 순서대로 탐색을 진행함
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new ArrayList[N][N];
        move = new Point[N*N+1];
        // 0. 현재 위치에 대한 정보 저장
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = new ArrayList<>();
                int num = Integer.parseInt(st.nextToken()); // 현재 자리 값
                map[i][j].add(num);
                move[num] = new Point(i, j);
            }
        }

        int[] m = new int[M];

        // 1. move 에서 해당하는 위치를 찾아서 8방 탐색을 진행해서 그 중 가장 큰 값으로 이동
        // 1-2. 이동할때, 맨 앞으로 값이 들어가야함
        // 1-3. 만일 그 숫자 앞에 들어있는 숫자가 있다면, 한꺼번에 같이 이동한다.
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            m[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<M;i++) {
            Point p = move[m[i]];
            int num = 0, nextR = 0, nextC = 0;
            for(int k=0;k<8;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];

                if(!inRange(nr, nc)) continue;
                if(maxNum(nr, nc) > num) {
                    num = maxNum(nr, nc);
                    nextR = nr;
                    nextC = nc;
                }
            }
            // 값이 있다면, 그 위치로 이동할거임
            // 예시로, (4,1,7,6) 이 있으면 (4,1)을 (9)로 옮길때 -> (4, 1, 9) 가 되야함
            if(num != 0) {
                if(map[p.r][p.c].isEmpty()) continue;
                int idx = 0;
                for(int x=0;x<map[p.r][p.c].size();x++) {
                    if(map[p.r][p.c].get(x).equals(m[i])) {
                        idx = x; break;
                    }
                }
//                System.out.println(m[i] + " " + num + " " + idx);
                List<Integer> tmp = new ArrayList<>();
                // 그러면, (4, 1)을 (1, 4)로 빼게 되고, (1, 4) 순서대로 다시 넣으면 (4, 1) 형태로 들어감
                for(int x=0;x<=idx;x++) {
                    tmp.add(map[p.r][p.c].get(x));
                }

                int removeCount = idx + 1;

                while(removeCount > 0) {
                    map[p.r][p.c].remove(0);
                    removeCount--;
                }

                map[nextR][nextC].addAll(0, tmp);
//                System.out.println("여기는 이동한 위치의 list : " );

                // 이동한 인덱스 위치를 변경해줘야함
                for (int value : tmp) {
                    move[value] = new Point(nextR, nextC);
                }

//                printMap();
            }

//            System.out.println("여기는 위치 저장 ");
//            for(int j=0;j<move.length;j++) {
//                Point np = move[j];
//                System.out.println(np.r + " " + np.c);
//            }
//            System.out.println();
        }

        printMap();
    }

    // 현재 지도 위치에서 가장 큰 값 찾기
    private static int maxNum(int r, int c) {
        int n = 0;
        for(int i : map[r][c]) {
            if(i > n) n = i;
        }
        return n;
    }

    private static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].isEmpty()) System.out.println("None");
                else {
                    for (int x : map[i][j]) {
                        System.out.print(x + " ");
                    }
                    System.out.println();
                }
            }
        }
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}

/**
 * 20 * 20 * 8 * 100
 */