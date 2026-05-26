package codetree.시뮬레이션.격자안에서터지고떨어지는경우;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static int N, M, R, C;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1이면 왼쪽, 2이면 오른쪽
        int ans = 0;
        // 12시 방향부터 위 오른쪽 아래 왼쪽 순으로 진행하고, 3차원 배열보다는 2차원 배열로 방향을 통한 접근
        for(int j=0;j<N;j++) {
            int nr = 0, nc = j;
            int dir = 2;
            int cnt = 1;
            while(true) {
                if(!inRange(nr, nc)) break;

                if(map[nr][nc] == 1) {
                    if(dir % 2 == 0) dir = turnRight(dir);
                    else dir = turnLeft(dir);
                } else if(map[nr][nc] == 2) {
                    if(dir % 2 == 0) dir = turnLeft(dir);
                    else dir = turnRight(dir);
                }

                nr += dr[dir];
                nc += dc[dir];
//                System.out.println(nr + " " + nc);
                cnt++;
            }
            ans = Math.max(ans, cnt);
        }

        for(int i=0;i<N;i++) {
            int nr = i, nc = N-1;
            int dir = 3;
            int cnt = 1;
            while(true) {
                if(!inRange(nr, nc)) break;

                if(map[nr][nc] == 1) {
                    if(dir % 2 == 0) dir = turnRight(dir);
                    else dir = turnLeft(dir);
                } else if(map[nr][nc] == 2) {
                    if(dir % 2 == 0) dir = turnLeft(dir);
                    else dir = turnRight(dir);
                }

                nr += dr[dir];
                nc += dc[dir];
//                System.out.println(nr + " " + nc);
                cnt++;
            }
            ans = Math.max(ans, cnt);
        }

        for(int j=0;j<N;j++) {
            int nr = N-1, nc = j;
            int dir = 0;
            int cnt = 1;
            while(true) {
                if(!inRange(nr, nc)) break;

                if(map[nr][nc] == 1) {
                    if(dir % 2 == 0) dir = turnRight(dir);
                    else dir = turnLeft(dir);
                } else if(map[nr][nc] == 2) {
                    if(dir % 2 == 0) dir = turnLeft(dir);
                    else dir = turnRight(dir);
                }

                nr += dr[dir];
                nc += dc[dir];
//                System.out.println(nr + " " + nc);
                cnt++;
            }
            ans = Math.max(ans, cnt);
        }

        for(int i=0;i<N;i++) {
            int nr = i, nc = 0;
            int dir = 1;
            int cnt = 1;
            while(true) {
                if(!inRange(nr, nc)) break;

                if(map[nr][nc] == 1) {
                    if(dir % 2 == 0) dir = turnRight(dir);
                    else dir = turnLeft(dir);
                } else if(map[nr][nc] == 2) {
                    if(dir % 2 == 0) dir = turnLeft(dir);
                    else dir = turnRight(dir);
                }

                nr += dr[dir];
                nc += dc[dir];
//                System.out.println(nr + " " + nc);
                cnt++;
            }
            ans = Math.max(ans, cnt);
        }

        System.out.println(ans);
    }

    private static int turnRight(int dir) {
        return (dir + 1) % 4;
    }

    private static int turnLeft(int dir) {
        return (dir + 3) % 4;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
