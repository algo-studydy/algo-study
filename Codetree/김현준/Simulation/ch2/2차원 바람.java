import java.util.*;

public class Main {
    static int n, m, q;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt();
        int[][] building = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                building[i][j] = sc.nextInt();
        int[][] queries = new int[q][4];
        for (int i = 0; i < q; i++)
            for (int j = 0; j < 4; j++)
                queries[i][j] = sc.nextInt();

        // Please write your code here.

        // 반복 횟수 만큼 진행
        for(int i=0;i<q;i++) {
            Deque<Integer> Q = new ArrayDeque<>();
            int start_r = queries[i][0] - 1;
            int start_c = queries[i][1] - 1;
            int end_r = queries[i][2] - 1;
            int end_c = queries[i][3] - 1;

            for(int c=start_c;c<=end_c;c++) {
                Q.offer(building[start_r][c]);
            }
            for(int r=start_r+1;r<=end_r;r++) {
                Q.offer(building[r][end_c]);
            }
            for(int c=end_c-1;c>=start_c;c--) {
                Q.offer(building[end_r][c]);
            }
            for(int r=end_r-1;r>start_r;r--) {
                Q.offer(building[r][start_c]);
            }

            // 1. 시계 방향으로 회전
            Q.addFirst(Q.pollLast());

            for(int c = start_c; c <= end_c; c++) {
                building[start_r][c] = Q.poll();
            }

            for(int r = start_r + 1; r <= end_r; r++) {
                building[r][end_c] = Q.poll();
            }

            for(int c = end_c - 1; c >= start_c; c--) {
                building[end_r][c] = Q.poll();
            }

            for(int r = end_r - 1; r > start_r; r--) {
                building[r][start_c] = Q.poll();
            }

            int[][] tmp = new int[n][m];
            for(int r=0;r<n;r++) {
                for(int c=0;c<m;c++) {
                    tmp[r][c] = building[r][c];
                }
            }
            // 2. 현재 칸에 적혀있는 숫자 + 인전합 곳에 적혀있는 숫자들의 평균
            for(int r=start_r;r<=end_r;r++) {
                for(int c=start_c;c<=end_c;c++) {
                    tmp[r][c] = findKey(r, c, building);

                    // for(int nr=0;nr<n;nr++) {
                    //     for(int nc=0;nc<m;nc++) {
                    //         System.out.print(tmp[nr][nc] + " ");
                    //     }
                    //     System.out.println();
                    // }
                    // System.out.println("===========");
                }
            }

            for(int r=0;r<n;r++) {
                for(int c=0;c<m;c++) {
                    building[r][c] = tmp[r][c];
                }
            }
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                System.out.print(building[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int findKey(int r, int c, int[][] building) {
        int total = building[r][c];
        int cnt = 1;
        for(int k=0;k<4;k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if(!inRange(nr, nc)) continue;

            total += building[nr][nc];
            cnt++;
        }

        return total / cnt;
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}