import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int i = 0; i < t; i++){
            int n = sc.nextInt();
            String[] nums = new String[n];

            for(int j = 0; j < n; j++){
                nums[j] = sc.next();
            }

            Arrays.sort(nums);

            boolean answer = false;
            for(int j = 1; j < n; j++){
                String s1 = nums[j-1];
                String s2 = nums[j];
                // 정렬을 했기 때문에 s1이 s2보다 길면 접두어가 될 수 없음
                if(s1.length() > s2.length()) continue;

                boolean flag = true;
                for(int k = 0; k < s1.length(); k++){
                    // s2가 s1을 포함하지 않는 경우 접두어가 될 수 없음
                    if(s1.charAt(k) != s2.charAt(k)){
                        flag = false;
                        break;
                    }
                }

                // 접두어가 포함된 쌍을 발견하면 반복 중지
                if(flag) {
                    answer = true;
                    break;
                }
            }

            System.out.println(answer ? "NO" : "YES");

        }
    }
}