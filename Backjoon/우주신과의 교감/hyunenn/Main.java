import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pair {
        int u, v;
        double w;
        Pair(int u, int v, double w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static int N, M;
    static int[][] points;
    static int[] parent;
    static List<Pair> list = new ArrayList<>();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        points = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            uni(start, end);
        }

        for(int i=1;i<=N;i++) {
            for(int j=i+1;j<=N;j++) {
                list.add(new Pair(i, j, Math.hypot(points[i][0] - points[j][0], points[i][1] - points[j][1])));
            }
        }

        list.sort((a, b) -> Double.compare(a.w, b.w));

        double ans = 0;
        for(Pair p : list) {
            int u = find(p.u);
            int v = find(p.v);
            if(u == v) continue;
            else {
                uni(u, v);
                ans += p.w;
            }
        }

        System.out.printf("%.2f", ans);
        /**
         * 1. parent[i] 를 전부 자기 자신으로 채우기
         * 2. 이미 연결된 통로는 uni(a,b) 를 통해 parent[b] = a 처리
         * 3. 모든 점에 대한 리스트 Math.hypot 의 거리 함수를 통해 생성
         * 4. 생성된 리스트를 거리 기준 오름차순 정렬
         * 5. list 에서 간선의 각 정점이 연결되지 않은 정점일 경우, 값에 추가
         */
    }

    public static int find(int u) {
        if (parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }

    public static boolean uni(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return false;
        parent[v] = u;
        return true;
    }
}
