import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int[] answer;
    static HashMap<Integer, Integer> map = new HashMap<>();
    static Stack<Integer> stack = new Stack<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        answer = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        // 뒤에서부터 생각
        for(int i=N-1;i>=0;i--) {
            // 현재 값보다 나온 수가 적다면 제거
            while(!stack.isEmpty()) {
                if(map.get(stack.peek()) <= map.get(arr[i])) {
                    stack.pop();
                } else if(map.get(stack.peek()) > map.get(arr[i])) {
                    answer[i] = stack.peek();
                    break;
                }
            }
            // stack 이 비었다면 -1
            if(stack.isEmpty()) answer[i] = -1;
            // 끝났다면 현재 값 stack 에 추가
            stack.push(arr[i]);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }
}