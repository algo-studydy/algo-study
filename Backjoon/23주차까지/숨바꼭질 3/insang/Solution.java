import java.util.*;

public class Main {
    static int[] dist; // 각 위치까지의 최소 시간
    static Deque<Integer> deque = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 수빈이 위치
        int K = sc.nextInt(); // 동생 위치

        dist = new int[100000 + 1]; // 방문 시간 저장 배열
        Arrays.fill(dist, Integer.MAX_VALUE); // 최소 시간을 갱신하기 위해 초기값을 무한대로 설정

        bfs(N);

        System.out.println(dist[K]);
    }

    static void bfs(int start) {
        deque.offer(start);
        dist[start] = 0;

        while (!deque.isEmpty()) {
            int cur = deque.poll();  // 현재 위치

            // 순간 이동은 비용이 0이므로 deque의 맨 앞에 넣음
            int teleport = cur * 2;
            if (teleport <= 100000 && dist[teleport] > dist[cur]) {
                dist[teleport] = dist[cur];
                deque.offerFirst(teleport);
            }

            // 앞으로 이동
            int front = cur + 1;
            if (front <= 100000 && dist[front] > dist[cur] + 1) {
                dist[front] = dist[cur] + 1;
                deque.offerLast(front);
            }

            // 뒤로 이동
            int back = cur - 1;
            if (back >= 0 && dist[back] > dist[cur] + 1) {
                dist[back] = dist[cur] + 1;
                deque.offerLast(back);
            }
        }
    }
}
