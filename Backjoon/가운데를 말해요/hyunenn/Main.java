import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> leftPQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> rightPQ = new PriorityQueue<>();
        for(int i=0;i<N;i++) {
            int curr = Integer.parseInt(br.readLine());

            // 일단 초기값이 왔다면, 값을 넣고 다음으로
            if(leftPQ.isEmpty()) {
                leftPQ.add(curr);
                System.out.println(leftPQ.peek());
                continue;
            }

            // 입력값을 비교해서, 왼쪽 PQ 보다 작거나 작으면 left 에, 크면 right 에 값을 넣는다.
            if(curr > leftPQ.peek()) {
                rightPQ.add(curr);
            } else {
                leftPQ.add(curr);
            }

            // 여기서, 한 쪽이 많아지게 되면, 그 값을 빼고 다른 PQ 에 넣어준다.
            if(rightPQ.size() > leftPQ.size()) {
                leftPQ.add(rightPQ.poll());
            } else if(leftPQ.size() > rightPQ.size() + 1) {
                rightPQ.add(leftPQ.poll());
            }

            System.out.println(leftPQ.peek());
        }
    }
}
