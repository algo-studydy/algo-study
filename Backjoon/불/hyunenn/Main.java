import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c, cnt;
        char rec;
        Point(int r, int c, char rec, int cnt) {
            this.r = r;
            this.c = c;
            this.rec = rec;
            this.cnt = cnt;
        }
    }
    static int W, H, T;
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            Queue<Point> fire = new ArrayDeque<>();
            Queue<Point> move = new ArrayDeque<>();
            for(int i=0;i<H;i++) {
                String line = br.readLine();
                for(int j=0;j<W;j++) {
                    map[i][j] = line.charAt(j);
                    if(map[i][j] == '*') fire.offer(new Point(i, j, '*', 0));
                    else if(map[i][j] == '@') move.offer(new Point(i, j, '@', 0));
                }
            }

            boolean flag = false;
            int ans = 0;
            List<Point> fireList = new ArrayList<>();
            List<Point> moveList = new ArrayList<>();
            Point p = fire.peek();
            while(!move.isEmpty()) {
                while(!fire.isEmpty()) {
                    Point f = fire.poll();
                    for(int k=0;k<4;k++) {
                        int fr = f.r + dr[k];
                        int fc = f.c + dc[k];
                        // 범위 안에 있고 벽, 불이 없던곳으로 진행
                        if(inRange(fr, fc) && map[fr][fc] != '#' && map[fr][fc] != '*') {
                            map[fr][fc] = '*';
                            fireList.add(new Point(fr, fc, '*', f.cnt + 1));
                        }
                    }
                }
                for(Point f : fireList) {
                    fire.offer(new Point(f.r, f.c, f.rec, f.cnt));
                }
                fireList = new ArrayList<>(); // 값을 복사하고, 초기화

                while(!move.isEmpty()) {
                    Point m = move.poll();
                    for(int k=0;k<4;k++) {
                        int mr = m.r + dr[k];
                        int mc = m.c + dc[k];
                        // 범위 안에 있고 빈칸이면 이동
                        if(inRange(mr, mc) && map[mr][mc] == '.') {
                            map[mr][mc] = '@';
                            moveList.add(new Point(mr, mc, '@', m.cnt + 1));
                        }

                        // 범위를 벗어난다면, 탈출한것으로 간주
                        else if(!inRange(mr, mc)) {
                            flag = true;
                            ans = m.cnt + 1;
                            break;
                        }
                    }

                    if(flag) break;
                }

                if(flag) break;
                for(Point m : moveList) {
                    move.offer(new Point(m.r, m.c, m.rec, m.cnt));
                }
                moveList = new ArrayList<>();
            }

            System.out.println(ans == 0 ? "IMPOSSIBLE" : ans);
            // @, *만 지속적으로 이동하는 방식으로 진행, 맵을 채우면?
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < H && c >= 0 && c < W;
    }
}
