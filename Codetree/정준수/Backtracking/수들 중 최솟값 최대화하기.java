import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ct0905 {
    static int N;
    static int[][] map;
    static boolean[] visited;
    static ArrayList<Integer> list;
    static int ans;

    static void go(int curRow) {
        if (curRow == N) {
            int min = Integer.MAX_VALUE;
            for (int i=0;i<N;i++) {
                min = Math.min(min, list.get(i));
            }
            ans = Math.max(ans, min);
            return;
        }

        for (int i=0;i<N;i++) {
            if (visited[i]) continue;

            list.add(map[curRow][i]);
            visited[i] = true;
            go(curRow+1);
            list.remove(list.size()-1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N];
        ans = Integer.MIN_VALUE;

        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++) {
                map[r][c]=Integer.parseInt(st.nextToken());
            }
        }

        list = new ArrayList<>();
        go(0);

        System.out.print(ans);
    }
}
