import java.io.*;
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
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int[][] ans = new int[n][2]; // 개수, 가장 가까운 건물의 번호
        for(int i=0; i<n; i++){
            ans[i][1] = Integer.MAX_VALUE;
        }

        Stack<Top> stack = new Stack<>();

        // 왼쪽 바라볼때 큰거 찾기
        for (int i = 0; i < n; i++) {
            int curH = heights[i];

            // 현재 탑의 높이가 스택안의 탑보다 높으면 전부 pop
            while (!stack.isEmpty() && stack.peek().h <= curH) {
                stack.pop();
            }

            // 이후 스택이 비어있지 않다면 자신보다 높은게 존재
            if (!stack.isEmpty()) {
                ans[i][0] += stack.size();

                int dist1 = Math.abs((i + 1) - stack.peek().idx);
                int dist2 = Math.abs((i + 1) - ans[i][1]);

                if(ans[i][1] == Integer.MAX_VALUE || dist1 < dist2 || (dist1 == dist2 && stack.peek().idx < ans[i][1])) {
                    ans[i][1] = stack.peek().idx;
                }
            }
            // 현재 탑 스택에 push
            stack.push(new Top(i + 1, curH));
        }

        stack = new Stack<>();
        // 왼쪽 바라볼때 큰거 찾기
        for (int i = n - 1; i >= 0; i--) {
            int curH = heights[i];

            // 현재 탑의 높이가 스택안의 탑보다 높으면 전부 pop
            while (!stack.isEmpty() && stack.peek().h <= curH) {
                stack.pop();
            }

            // 이후 스택이 비어있지 않다면 자신보다 높은게 존재
            if (!stack.isEmpty()) {
                ans[i][0] += stack.size();
                int dist1 = Math.abs((i + 1) - stack.peek().idx);
                int dist2 = Math.abs((i + 1) - ans[i][1]);

                if(ans[i][1] == Integer.MAX_VALUE || dist1 < dist2 || (dist1 == dist2 && stack.peek().idx < ans[i][1])) {
                    ans[i][1] = stack.peek().idx;
                }
            }
            // 현재 탑 스택에 push
            stack.push(new Top(i + 1, curH));
        }

        for(int i=0; i<n; i++){
            if(ans[i][0] > 0){
                System.out.println(ans[i][0]+" "+ans[i][1]);
            }else{
                System.out.println(ans[i][0]);
            }
        }
    }
}
