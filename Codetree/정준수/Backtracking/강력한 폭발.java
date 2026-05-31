import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ct0603 {
    static class Bomb {
        int row, col;

        Bomb(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return String.format("row: "+this.row+" col: "+this.col);
        }
    }

    static int N, bombCount;
    static int[][] map;
    static ArrayList<Bomb> bList;
    static int[][][] deltaList = {
            {{-1,0},{-2,0},{0,0},{1,0},{2,0}},
            {{-1,0},{1,0},{0,0},{0,-1},{0,1}},
            {{-1,-1},{-1,1},{0,0},{1,1},{1,-1}}
    };
    static int[][] visited;
    static int ans;

    static boolean isValid(int row, int col) {
        return (row>=0&&col>=0&&row<N&&col<N);
    }

    static void go(int idx, int delCount) {
        if (idx == bombCount) {
            ans = Math.max(ans, delCount);
            return;
        }

        Bomb bomb = bList.get(idx);
        for (int i=0;i<deltaList.length;i++) {
            int[][] delta = deltaList[i];

            int count = 0;
            for (int[] d : delta) {
                int nextRow = bomb.row + d[0];
                int nextCol = bomb.col + d[1];

                if (!isValid(nextRow, nextCol)) continue;

                visited[nextRow][nextCol] += 1;
                if (visited[nextRow][nextCol] > 1) continue;
                count += 1;
            }
            go(idx + 1, delCount + count);
            for (int[] d : delta) {
                int nextRow = bomb.row + d[0];
                int nextCol = bomb.col + d[1];

                if (!isValid(nextRow, nextCol)) continue;
                if (visited[nextRow][nextCol] > 0) visited[nextRow][nextCol] -= 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new int[N][N];
        bList = new ArrayList<>();


        for (int row=0;row<N;row++) {
            st = new StringTokenizer(br.readLine());
            for (int col=0;col<N;col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                if (map[row][col] == 1) {
                    bList.add(new Bomb(row, col));
                    bombCount += 1;
                }
            }
        }

        go(0, 0);

        System.out.print(ans);
    }
}
