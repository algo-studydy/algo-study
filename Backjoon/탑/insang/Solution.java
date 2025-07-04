import java.io.*;
import java.util.*;

public class BOJ2493탑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] top = new int[n+1];
        int[] answer = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            top[i] = Integer.parseInt(st.nextToken());
        }

        Stack<int[]> stack = new Stack<>(); // [높이, 인덱스]
        for(int i = 1; i <= n; i++){
            // 현재 탑보다 낮은 탑을 스택에서 제거
            while(!stack.isEmpty() && stack.peek()[0] < top[i]) {
                stack.pop();
            }
            // 스택이 비어 있지 않으면 레이저 수신 가능 -> 정답 갱신
            if(!stack.isEmpty()){
                answer[i] = stack.peek()[1];
            } else {
                answer[i] = 0;
            }
            // 현재 탑을 스택에 추가
            stack.push(new int[] {top[i], i});
        }

        for(int i = 1; i <= n; i++){
            System.out.print(answer[i] + " ");
        }
    }
}
