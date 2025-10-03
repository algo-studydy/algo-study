import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pair {
        int s, e, v;
        Pair(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }
    }
    static int V, E;
    static int[][] dist;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dist = new int[V+1][V+1];
        for(int i=1;i<=V;i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            dist[start][end] = value;
        }

        for(int k=1;k<=V;k++) {
            for(int i=1;i<=V;i++) {
                if(dist[i][k] == Integer.MAX_VALUE) continue;
                for(int j=1;j<=V;j++) {
                    if(dist[k][j] == Integer.MAX_VALUE) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i=1;i<=V;i++) {
            for(int j=1;j<=V;j++) {
                if(i==j)continue;
                if(dist[i][j] < Integer.MAX_VALUE && dist[j][i] < Integer.MAX_VALUE) {
                    ans = Math.min(ans, dist[i][j] + dist[j][i]);
                }
            }
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }


}
