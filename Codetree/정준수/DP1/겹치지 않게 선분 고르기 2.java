import java.io.*;
import java.util.*;

public class ct1505 {
    static int N;
    static int[][] map;
    static int[] dp;

    private static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][3];
        dp = new int[N];

        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            map[i][0]=s;
            map[i][1]=e;
            map[i][2]=p;
        }

        Arrays.sort(map, (o1, o2)-> {return (Integer.compare(o1[0], o2[0]));});
    }

    private static void go() {
        for (int i=0;i<N;i++) {
            dp[i] = map[i][2];

            for (int j=0;j<i;j++) {
                if (map[i][1] >= map[j][0] && map[j][1] >= map[i][0]) continue;

                dp[i] = Math.max(dp[i], dp[j] + map[i][2]);
            }
        }

        int ans = 1;
        for (Integer num : dp) {
            ans = Math.max(ans, num);
        }

        System.out.print(ans);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}

