import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 문제 개수
        int m = sc.nextInt(); // 정보 개수

        List<Integer>[] graph = new ArrayList[n + 1]; // 인접 리스트
        int[] inDegree = new int[n + 1]; // 진입 차수 배열
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 최소 힙 (번호가 작은 문제부터 풀기 위해)

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 선행 관계 입력
        for (int i = 0; i < m; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            graph[A].add(B); // A -> B 방향 간선 추가
            inDegree[B]++; // B의 진입 차수 증가
        }

        // 진입 차수가 0인 문제들을 우선순위 큐에 삽입 (가장 쉬운 문제들)
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                pq.add(i);
            }
        }

        // 위상 정렬 수행
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int cur = pq.poll(); // 현재 풀 수 있는 가장 쉬운 문제 선택
            sb.append(cur).append(" ");

            // 현재 문제를 풀었으므로, 다음 문제들의 진입 차수를 감소
            for (int next : graph[cur]) {
                inDegree[next]--;
                if (inDegree[next] == 0) { // 진입 차수가 0이 되면 풀 수 있는 문제이므로 큐에 추가
                    pq.add(next);
                }
            }
        }

        System.out.println(sb.toString().trim());
    }
}
