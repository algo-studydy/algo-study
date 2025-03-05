import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int[] answer = new int[n];
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        // 입력, 등장 횟수 map에 저장
        for(int i = 0; i < n; i++){
            int num = sc.nextInt();
            nums[i] = num;
            // map에 해당 key가 존재하지 않으면 key와 value를 추가
            if(!map.containsKey(num)){
                map.put(num, 1);
            }
            // map에 해당 키가 존재할 경우 값 + 1
            else map.computeIfPresent(num, (key, value) -> value+1);
        }

        Arrays.fill(answer, -1);
        for(int i = 0; i < n; i++){
            // (스택 최상단 숫자의 등장 횟수 < 현재 숫자의 등장 횟수)면 정답 배열 갱신
            while(!stack.isEmpty() && (map.get(nums[stack.peek()]) < map.get(nums[i]))){
                int idx = stack.pop();
                answer[idx] = nums[i];
            }
            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int num : answer) {
            sb.append(num).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
