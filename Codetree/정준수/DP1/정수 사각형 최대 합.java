import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct1401 {
    static int N;
    static int[][] map;
    static long[][] ans;

    static boolean isValid(int row, int col) {
        return (row>=0&&col>=0&&row<N&&col<N);
    }

    static void go() {
        for (int r=0;r<N;r++) {
            for (int c=0;c<N;c++) {
                long upVal = 0;
                long leftVal = 0;

                if (isValid(r, c-1)) leftVal = ans[r][c-1];
                if (isValid(r-1, c)) upVal = ans[r-1][c];

                ans[r][c] = map[r][c] + Math.max(leftVal, upVal);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        ans = new long[N][N];

        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        go();

        System.out.print(ans[N-1][N-1]);
    }
}
