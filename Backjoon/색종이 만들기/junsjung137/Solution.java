import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static long w = 0, b = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(in.readLine());    // N은 짝수
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        go(0, 0, N);

        bw.append(w+"\n").append(b+"\n");
        bw.close();
    }

    private static void go(int r, int c, int n) {
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                count += map[r+i][c+j];
            }
        }

        if(count == n * n) {    // 모두 blue
            b++;
        } else if (count == 0) {    // 모두 white
            w++;
        } else {
            int size = n / 2;
            go(r, c, size);
            go(r, c+size, size);
            go(r+size, c, size);
            go(r+size, c+size, size);
        }
    }

}