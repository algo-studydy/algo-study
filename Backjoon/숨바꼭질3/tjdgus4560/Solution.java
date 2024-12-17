package Baekjoon;

import java.util.*;

public class 숨바꼭질3 {
    static int[] walk;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 수빈이의 위치
        int K = sc.nextInt(); // 동생의 위치

        System.out.println(bfs(N, K));
        sc.close();
    }

    private static int bfs(int start, int target) {
        if (start >= target) {
            return start - target;
        }
        // 방문배열 초기화
        int max = 100000;
        int[] visited = new int[max + 1];
        Arrays.fill(visited, -1);

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = 0; // 시작 위치의 시간은 0

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == target) {
                return visited[cur];
            }

            // 순간이동
            int teleport = cur * 2;
            if (teleport <= max && visited[teleport] == -1) {
                visited[teleport] = visited[cur];
                q.add(teleport);
            }

            // 걷기
            walk = new int[2];
            walk[0] = cur - 1;
            walk[1] = cur + 1;
            for (int n : walk) {
                if (n >= 0 && n <= max && visited[n] == -1) {
                    visited[n] = visited[cur] + 1;
                    q.add(n);
                }
            }
        }

        // 이쪽으로 리턴할일은 없음
        return -1;
    }
}
