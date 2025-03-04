import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] result = new int[n];
        HashMap<Integer, Integer> hmap = new HashMap<>(); //등장 횟수 저장
        Stack<Integer> stack = new Stack<>(); //인덱스 저장용 스택

        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
            hmap.put(arr[i], hmap.getOrDefault(arr[i], 0) +1);
        }

        //오등큰수 계산
        for(int i=0; i<n; i++){
            // 0번 idx 부터 n-1번 idx까지 순서대로 진행하므로 스택에 저장된것은 현재 진행되는것보다 항상 왼쪽의 값들
            // arr[i]가 스택에 존재하는 인덱스의 값보다 등장 빈도가 더큰 값이면 갱신
            while(!stack.isEmpty() && hmap.get(arr[stack.peek()]) < hmap.get(arr[i])){
                result[stack.pop()] = arr[i];
            }
            stack.push(i);
        }

        //스택에 남은 인덱스들은 오등큰수가 없음
        while(!stack.isEmpty()){
            result[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
    }
}
