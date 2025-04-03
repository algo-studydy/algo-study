import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // 도시의 개수(정점)
        int m = sc.nextInt();  // 버스의 개수(간선)

        int[][] graph = new int[n+1][n+1];  // 노드의 개수가 적으므로 인접 행렬로 그래프 구성

        // 그래프 초기화
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(i != j) graph[i][j] = 100000000;
            }
        }

        for(int i = 0; i < m; i++){
            int a = sc.nextInt();  // 출발 도시
            int b = sc.nextInt();  // 도착 도시
            int c = sc.nextInt();  // 이동 비용

            if(graph[a][b] > c) graph[a][b] = c;
        }

        for(int k = 1; k <= n; k++){  // 경유할 도시
            for(int i = 1; i <= n; i++){  // 출발 도시
                for(int j = 1; j <= n; j++){  // 도착 도시
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(graph[i][j] == 100000000) System.out.print("0" + " ");
                else System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
}
