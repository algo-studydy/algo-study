import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Stack<Integer> stack = new Stack<>();

        int cnt = 1;  // 간식을 받을 번호표 (순서)
        for(int i = 0; i < n; i++){
            int num = sc.nextInt();  // 줄을 서 있는 학생의 번호표

            // 간식을 받을 순서(cnt)와 번호표(num)가 같지 않으면 해당 순번을 스택에 넣음
            if(num != cnt){
                stack.push(num);
            }
            // 간식을 받을 순서(cnt)와 번호표(num)가 같으면 간식을 받고 다음 순번으로 cnt 증가
            else{
                cnt++;
            }
            // 이후 스택에 남아있는 순번이 간식을 받을 수 있다면 간식을 받고 다음 순번으로 cnt 증가
            while(!stack.isEmpty() && stack.peek() == cnt){
                stack.pop();
                cnt++;
            }
        }

        // 스택에 간식을 받지 못한 학생이 남아있으면 Sad, 다 받았으면 Nice
        String answer = stack.isEmpty() ? "Nice" : "Sad";
        System.out.println(answer);
    }
}
