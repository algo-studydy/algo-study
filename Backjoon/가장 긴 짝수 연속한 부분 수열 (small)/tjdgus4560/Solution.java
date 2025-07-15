import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        int right =0;
        int left =0;

        int length =0; // 짝수 수열길이 변수
        int count =0; //홀수 카운트 변수
        int ans =0;

        while(right < n){
            if(arr[right]%2 ==0){
                length++;
                ans = Math.max(ans, length); //정답 갱신
            }else{
                // 홀수인데 최대 홀수갯수를 이미 채웠을때
                count++;
                if(count > k){
                    while (arr[left]%2==0){
                        length--;
                        left++;
                    }
                    count--;
                    left++;
                }
            }

            right++;
        }

        System.out.println(ans);
    }
}
