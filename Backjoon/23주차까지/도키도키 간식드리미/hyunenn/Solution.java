import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int[] arr;
    static boolean[] v;
    static Stack<Integer> stack = new Stack<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        v = new boolean[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 1;
        for(int i=1;i<=N;i++) {
            // 스택의 맨 위가 idx와 같으면 계속 pop
            while (!stack.isEmpty() && stack.peek() == idx) {
                stack.pop();
                idx++;
            }

            if (arr[i] == idx) {
                idx++; // 바로 간식 받음
            } else {
                stack.push(arr[i]); // 스택에 추가
            }
        }

        // 스택 남아있는 요소 다시 확인
        while (!stack.isEmpty() && stack.peek() == idx) {
            stack.pop();
            idx++;
        }

        if(stack.isEmpty()) System.out.println("Nice");
        else System.out.println("Sad");

    }
}