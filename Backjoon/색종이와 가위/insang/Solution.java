import java.util.Scanner;

public class BOJ20444색종이와가위 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long k = sc.nextLong();

        // 가로 or 세로로만 자르는 경우
        if(n+1 == k){
            System.out.println("YES");
            return;
        }

        /**
         * 가로로 x번, 세로로 y번 자르면 (x+1) * (y+1)개의 조각이 생김
         * 가로로 자른 횟수를 x라 하면,
         * 세로로 자른 횟수는 n-x 이므로
         * n번 잘라 k개의 조각을 만들기 위한 식은 아래와 같음
         * (x+1) * (n-x+1) = k
         */

        long left = 0;
        long right = n;

        while(left <= right){
            long mid = (left + right) / 2;  // 타겟(x)
            long paper = (mid +1) * ((n-mid) + 1);

            if(paper == k) {
                System.out.println("YES");
                return;
            }
            // 색종이의 개수가 k보다 크면 right를 줄여 개수를 줄임
            if(paper > k){
                right = mid - 1;
            }

            // 색종이의 개수가 k보다 작으면 left를 늘려 개수를 늘림
            else if(paper < k){
                left = mid + 1;
            }
        }

        System.out.println("NO");
    }
}
