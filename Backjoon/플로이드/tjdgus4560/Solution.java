import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int n, m;
    static int[][] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        dist = new int[n+1][n+1];

        // dist 배열 초기화
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }

        // 버스 정보 입력
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            // 동일한 경로이면 더작은값으로 갱신
            if (dist[a][b] > c) {
                dist[a][b] = c;
            }
        }

        // Floyd-Warshall
        //k : 중간 경유 노드
        for (int k = 1; k <= n; k++) {
            // i : 시작도시
            for (int i = 1; i <= n; i++) {
                // j : 도착 도시
                for (int j = 1; j <= n; j++) {
                    // 만약 중간 경유 노드를 거쳐가는 것이 그냥 가는 것보다 짧으면 갱신
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 결과 출력
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == INF) System.out.print("0 ");
                else System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}
