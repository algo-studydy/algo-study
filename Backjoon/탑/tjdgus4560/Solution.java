import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class Top{
        int idx;
        int h;

        public Top(int idx, int h) {
            this.idx = idx;
            this.h = h;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 탑의 갯수
        int[] heights = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++){
            heights[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        Stack<Top> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int curH = heights[i];

            // 현재 탑의 높이가 스택안의 탑보다 높은거 전부 pop
            while (!stack.isEmpty() && stack.peek().h < curH) {
                stack.pop();
            }

            // 이후 스택이 비어있지 않다면 자신보다 높거나 같은게 존재한거니까 sb에 추가
            if (!stack.isEmpty()) {
                sb.append(stack.peek().idx).append(" ");
            } else {
                sb.append("0 ");
            }

            // 현재 탑 스택에 push
            stack.push(new Top(i+1, curH));
        }

        System.out.println(sb);
    }
}
