import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ct1003 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int villageCount, populationCount;
    static PriorityQueue<Integer> pQ;
    static StringBuilder sb;
    static int[][] delta = {
            {1,0},
            {-1,0},
            {0,-1},
            {0,1}
    };

    static boolean isValid(int row, int col) {
        return (row>=0&&col>=0&&row<N&&col<N);
    }

    static void dfs(int row, int col) {
        populationCount++;

        for (int d=0;d<delta.length;d++) {
            int nextRow = row + delta[d][0];
            int nextCol = col + delta[d][1];

            if (!isValid(nextRow, nextCol)) continue;
            if (visited[nextRow][nextCol]) continue;
            if (map[nextRow][nextCol]==0) continue;

            visited[nextRow][nextCol] = true;
            dfs(nextRow, nextCol);
        }
    }

    static void go() {
        for (int r=0;r<N;r++) {
            for (int c=0;c<N;c++) {
                if (visited[r][c]) continue;
                if (map[r][c] == 0) continue;

                visited[r][c] = true;
                populationCount = 0;
                villageCount++;
                dfs(r,c);
                pQ.add(populationCount);
            }
        }

        sb.append(villageCount).append("\n");
        while (!pQ.isEmpty()) {
            sb.append(pQ.poll()).append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        sb = new StringBuilder();
        pQ = new PriorityQueue<>();

        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        go();

        System.out.print(sb.toString().trim());
    }
}
