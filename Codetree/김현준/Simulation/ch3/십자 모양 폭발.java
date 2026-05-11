
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()) -1;
        int e = Integer.parseInt(st.nextToken()) -1;

        solve(s, e);
    }

    public static void solve(int r, int c) {
        int range = arr[r][c];
        arr[r][c] = 0;
        if(range == 1) {
            return;
        }

        // 격자 터트리기
        for(int k=0;k<4;k++) {
            int nr = r;
            int nc = c;
            int cnt = range;
            while(cnt > 1) {
                nr += dr[k];
                nc += dc[k];
                if(!inRange(nr, nc)) break;

                arr[nr][nc] = 0;
                cnt--;
            }
        }

        // 배열 채우기 템플릿
        int[][] tmp = new int[N][N];
        for(int col=0;col<N;col++) {
            int idx = N-1;
            for(int row=N-1;row>=0;row--) {
                if(arr[row][col] != 0) {
                    tmp[idx][col] = arr[row][col];
                    idx--;
                }
            }
        }

        arr = tmp;

        printMap();
    }

    public static void printMap() {
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
