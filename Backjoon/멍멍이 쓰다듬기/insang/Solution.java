import java.util.Scanner;

public class BOJ1669멍멍이쓰다듬기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // x: 원숭이의 현재 키, y: 멍멍이의 키
        long x = sc.nextLong();
        long y = sc.nextLong();

        // 원숭이가 키를 늘려야 할 거리 (y에 도달해야 함)
        long dist = y - x;

        // 이미 키가 같다면 0일 소요
        if (dist == 0) {
            System.out.println(0);
            return;
        }

        // n: 키를 늘리는 증가량의 최대값 후보
        // dist에 가장 가까운 제곱수의 제곱근을 기준으로 함
        long n = (long) Math.sqrt(dist);

        // Case 1: 원숭이가 n까지 키를 늘리고 다시 줄일 때 정확히 y에 도달할 경우
        // 키 변화: 1 → 2 → ... → n → ... → 2 → 1 (총 거리 = n^2)
        // 필요한 일수: 2n - 1
        if (dist == n * n) {
            System.out.println(n * 2 - 1);
        }
        // Case 2: n까지 키를 늘리고, n을 두 번 사용하면 y에 도달하는 경우
        // 키 변화: 1 → 2 → ... → n → n → ... → 2 → 1 (총 거리 = n^2 + n)
        // 필요한 일수: 2n
        else if (dist <= n * n + n) {
            System.out.println(n * 2);
        }
        // Case 3: 위의 두 방법으로 부족할 경우 → 꼭짓점(n)을 n+1로 올려야 함
        // 키 변화: 1 → 2 → ... → n+1 → ... → 2 → 1
        // 필요한 일수: 2n + 1
        else {
            System.out.println(n * 2 + 1);
        }
    }
}
