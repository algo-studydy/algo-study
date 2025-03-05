import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        
        Queue<Integer> queue = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            queue.add(Integer.parseInt(st.nextToken()));
        }
        
        Stack<Integer> stack = new Stack<>();
        
        int current = 1;
        
        while (!queue.isEmpty() || !stack.isEmpty()) {
            if (!queue.isEmpty() && queue.peek() == current) {
                queue.poll();
                current++;
            }
            else if (!stack.isEmpty() && stack.peek() == current) {
                stack.pop();
                current++;
            }
            else if (!queue.isEmpty()) {
                stack.push(queue.poll());
            }
            else {
                break;
            }
        }
        
        if (current == N + 1) {
            System.out.println("Nice");
        } else {
            System.out.println("Sad");
        }
    }
}