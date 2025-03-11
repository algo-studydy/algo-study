import java.util.*;

public class BOJ2075N번째큰수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 최대힙을 유지하는 우선순위 큐 선언
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // 큐에 값 삽입(내림차순)
        for(int i = 0; i < n*n; i++){
            pq.offer(sc.nextInt());
        }

        // n번째로 큰 수가 큐의 가장 상단에 오도록 작업 수행
        for(int i = 0; i < n-1; i++){
            pq.poll();
        }
        System.out.println(pq.peek());
    }
}
