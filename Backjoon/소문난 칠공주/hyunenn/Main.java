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
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int ans;
    static List<Point> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[5][5];
        ans = 0;
        for(int i = 0; i < 5; i++) {
            String line = br.readLine();
            for(int j = 0; j < 5; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        recursive(0, 0);
        System.out.println(ans);
    }
    /**
     * 이름이 이름인 만큼, 7명의 여학생들로 구성되어야 한다.
     * 강한 결속력을 위해, 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야 한다.
     * 화합과 번영을 위해, 반드시 ‘이다솜파’의 학생들로만 구성될 필요는 없다.
     * 그러나 생존을 위해, ‘이다솜파’가 반드시 우위를 점해야 한다. 따라서 7명의 학생 중 ‘이다솜파’의 학생이 적어도 4명 이상은 반드시 포함되어 있어야 한다.
     */
    /*
    25 명 중에서 7명을 뽑아서, 그 7개가 bfs 로 탐색 가능한지 검사한다?
    25C7 을 해야한다.
     */

    public static void recursive(int idx, int num) {
        // basis
        if(idx == 7) {
            if(bfs()) ans++;
            return;
        }
        // inductive
        for(int i=num;i<25;i++) {
            list.add(new Point(i/5, i%5));
            recursive(idx + 1, i + 1);
            list.remove(list.size() - 1);
        }
    }

    // list 에 있는 좌표값들을 Q 에 삽입해서 서로가 만날 수 있냐를 확인하는게 빠를듯?
    public static boolean bfs() {
        Queue<Point> Q = new ArrayDeque<>();
        int cnt = 1;
        int sCnt = 0;
        boolean[][] ch = new boolean[5][5];
        boolean[][] v = new boolean[5][5];
        for(Point o : list) {
            ch[o.r][o.c] = true;
        }

        Point np = list.get(0);
        Q.offer(new Point(np.r, np.c));
        v[np.r][np.c] = true;
        if(map[np.r][np.c] == 'S') sCnt++;
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(inRange(nr, nc) && !v[nr][nc] && ch[nr][nc]) {
                    v[nr][nc] = true;
                    Q.offer(new Point(nr, nc));
                    cnt++;
                    if(map[nr][nc] == 'S') sCnt++;
                }
            }
        }

        if(cnt == 7 && sCnt >= 4) return true;
        else return false;
    }


    public static boolean inRange(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }

    // YYYYY
    // SYSYS
    // YYYYY
    // YSYYS
    // YYYYY
}