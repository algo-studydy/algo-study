import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] arr = new int[N];
        int positiveCount=0;
        int negativeCount=0;
        for(int i = 0; i < N; i++){
            arr[i] = sc.nextInt();
            if(arr[i]>0) positiveCount++;
            else negativeCount++;
        }

        int[] positive = new int[positiveCount]; //양수만 담은 배열
        int[] negative = new int[negativeCount]; //음수~0 담은 배열
        Arrays.sort(arr); // 오름차순 정렬

        for(int i=0; i<negativeCount; i++){
            negative[i] = arr[i];
        }

        int cur=0;
        for(int i=negativeCount; i<N; i++){
            positive[cur] = arr[i];
            cur++;
        }

        int ans = 0;

        // 음수 처리
        for (int i = 0; i + 1 < negativeCount; i += 2) {
            ans += negative[i] * negative[i + 1];
        }

        // 음수가 하나 남으면 그대로 더함
        if (negativeCount % 2 == 1) {
            ans += negative[negativeCount - 1];
        }

        // 양수 처리
        for (int i = positiveCount - 1; i > 0; i -= 2) {
            // 1은 곱하지 말고 더하는 것이 이득
            if (positive[i] == 1 || positive[i - 1] == 1) {
                ans += positive[i] + positive[i - 1];
            } else {
                ans += positive[i] * positive[i - 1];
            }
        }

        // 양수가 하나 남으면 더함
        if (positiveCount % 2 == 1) {
            ans += positive[0];
        }

        System.out.println(ans);

    }
}
