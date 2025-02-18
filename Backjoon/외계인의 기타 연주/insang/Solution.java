import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int answer = 0;
        int n = sc.nextInt();
        int p = sc.nextInt();
        Stack<Integer>[] stack = new Stack[6];

        for (int i = 0; i < 6; i++) {
            stack[i] = new Stack<>();
        }

        for(int i = 0; i < n; i++){
            int line = sc.nextInt() - 1;
            int fret = sc.nextInt();

            // 스택이 비어 있지 않다면 기존 프렛 중 더 높은 값들을 제거
            while (!stack[line].isEmpty() && stack[line].peek() > fret) {
                stack[line].pop();
                answer++;
            }

            // 같은 프렛이면 추가하지 않음
            if (!stack[line].isEmpty() && stack[line].peek() == fret) {
                continue;
            }

            // 새로운 프렛 추가
            stack[line].push(fret);
            answer++;
        }
        System.out.println(answer);
    }
}
