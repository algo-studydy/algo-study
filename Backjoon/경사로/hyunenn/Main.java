import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static int[][] map;
    static int ans;
    static boolean[][] v;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        ans = 0;
        v = new boolean[N][N];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 가로줄
        for (int i = 0; i < N; i++) {
            boolean flag = false;
            // 왼쪽으로 오른쪽으로 다 탐색해야함

            for (int j = 0; j < N - 1; j++) {
                int curr = map[i][j];
                int next = map[i][j + 1];

                if(curr == next) continue;
                else if (curr + 1 == next) {
                    if (!canInstallLeft(i, j)) {
                        flag = true;
                        break;
                    }
                } else if (curr == next + 1) {
                    if (!canInstallRight(i, j + 1)) {
                        flag = true;
                        break;
                    }
                } else {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
//                System.out.println("가로 : " + i);
                ans++;
            }
        }

        v = new boolean[N][N];
        // 세로줄
        for(int j=0;j<N;j++) {
            boolean flag = false;
            for(int i=0;i<N-1;i++) {
                int curr = map[i][j];
                int next = map[i+1][j];
                if(curr == next) continue;
                else if(curr + 1 == next) {
                    if (!canInstallUp(i, j)) {
                        flag = true;
                        break;
                    }
                } else if(curr == next + 1) {
                    if(!canInstallDown(i + 1, j)) {
                        flag = true;
                        break;
                    }
                } else {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
//                System.out.println("세로 : " + j);
                ans++;
            }
        }


        System.out.println(ans);
    }

    /*
        3 3 3 3 3 3
        2 3 3 3 3 3
        2 2 2 3 2 3
        1 1 1 2 2 2
        1 1 1 3 3 1
        1 1 2 3 3 2
     */

    /*
        경사로는 낮은 칸에 놓으며, L개의 연속된 칸에 경사로의 바닥이 모두 접해야 한다.
        낮은 칸과 높은 칸의 높이 차이는 1이어야 한다.
        경사로를 놓을 낮은 칸의 높이는 모두 같아야 하고, L개의 칸이 연속되어 있어야 한다.
     */
    public static boolean canInstallLeft(int r, int c) {
        // 우선, 경사로를 놓을 (r,c-1) 이 주어짐
        // 1. 연속으로 발판이 이어져있고, 범위를 벗어나지 않으면 L 길이의 경사로를 놓을 수 있음
        int h = map[r][c];
        for (int i = c; i > c - L; i--) {
            if (i < 0 || map[r][i] != h || v[r][i]) return false;
        }
        for (int i = c; i > c - L; i--) v[r][i] = true;
        return true;
    }

    public static boolean canInstallRight(int r, int c) {
        int h = map[r][c];
        for (int i = c; i < c + L; i++) {
            if (i >= N || map[r][i] != h || v[r][i]) return false;
        }
        for (int i = c; i < c + L; i++) v[r][i] = true;
        return true;
    }

    public static boolean canInstallUp(int r, int c) {
        int h = map[r][c];
        for (int i = r; i > r - L; i--) {
            if (i < 0 || map[i][c] != h || v[i][c]) return false;
        }
        for (int i = r; i > r - L; i--) v[i][c] = true;
        return true;
    }

    public static boolean canInstallDown(int r, int c) {
        int h = map[r][c];
        for (int i = r; i < r + L; i++) {
            if (i >= N || map[i][c] != h || v[i][c]) return false;
        }
        for (int i = r; i < r + L; i++) v[i][c] = true;
        return true;
    }
    // 경사로를 놓으면 그 자리를 방문배열에 체크해서 경사로가 겹치게 놓을 일이 없게 한다.
    // 1. (가로줄, 세로줄) 검사를 시작하는데, 현재 자리가 앞 자리의 높이와 맞으면 다음 열로 진행한다
    // 만일, 다르다면 현재 자리에서 경사로를 L의 길이만큼 놓을 수 있는 지 확인한다. 못 놓으면, flag 를 true 처리하고 반복문 종료

}
