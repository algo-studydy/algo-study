import java.util.*;

public class Main {
    static int N;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {
        int r, c, dir, count;
        Point(int r, int c, int dir, int count) {
            this.r = r;
            this.c = c;
            this.dir = dir; //방향
            this.count = count; //이동횟수
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        map = new char[N][N];

        ArrayList<int[]> start = new ArrayList<>(); //시작좌표("B")들 담기용
        ArrayList<int[]> end = new ArrayList<>(); //종료좌표("E")들 담기용

        for (int i=0; i<N; i++) {
            String s = scanner.next();
            for (int j=0; j<N; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'B') {
                    start.add(new int[]{i, j});
                }
                if (map[i][j] == 'E') {
                    end.add(new int[]{i, j});
                }
            }
        } //입력끝

        // 시작, 종료좌표들의 중간지점 및 방향 설정
        // 가로:0, 세로:1
        int sr = start.get(1)[0];
        int sc = start.get(1)[1];
        int sd = (start.get(0)[0] == start.get(1)[0]) ? 0 : 1;

        int er = end.get(1)[0];
        int ec = end.get(1)[1];
        int ed = (end.get(0)[0] == end.get(1)[0]) ? 0 : 1;

        System.out.println(bfs(sr, sc, sd, er, ec, ed));

    }

    private static int bfs(int sr, int sc, int sd, int er, int ec, int ed) {
        visited = new boolean[N][N][2];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sr, sc, sd, 0));
        visited[sr][sc][sd] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            // 종료조건
            if (p.r == er && p.c == ec && p.dir == ed) {
                return p.count;
            }

            //4방향 움직임
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (canMove(nr, nc, p.dir) && !visited[nr][nc][p.dir]) {
                    visited[nr][nc][p.dir] = true;
                    q.add(new Point(nr, nc, p.dir, p.count + 1));
                }
            }

            // 방향돌리기
            int nd = 1 - p.dir;
            if (canRotate(p.r, p.c) && !visited[p.r][p.c][nd]) {
                visited[p.r][p.c][nd] = true;
                q.add(new Point(p.r, p.c, nd, p.count + 1));
            }
        }

        return 0;
    }

    //이동 가능 체크
    static boolean canMove(int r, int c, int dir) {
        if (dir == 0) { //가로
            if (c-1<0 || c+1>=N || r<0 || r>=N) {
                return false;
            }
            for (int i=-1; i<=1; i++) {
                if (map[r][c + i] == '1') {
                    return false;
                }
            }
        } else { //세로
            if (r-1<0 || r+1>=N || c<0 || c>=N) {
                return false;
            }
            for (int i=-1; i<=1; i++) {
                if (map[r+i][c] == '1') {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean canRotate(int r, int c) {
        for (int i=r-1; i<=r+1; i++) {
            for (int j=c-1; j<=c+1; j++) {
                if (i<0 || j<0 || i>=N || j>=N || map[i][j] == '1') {
                    return false;
                }
            }
        }
        return true;
    }
}
