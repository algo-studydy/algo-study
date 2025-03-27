import java.util.*;

public class Main {
    static int n, c;
    static int[] house;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        c = sc.nextInt();

        house = new int[n];
        for(int i = 0; i < n; i++){
            house[i] = sc.nextInt();
        }

        // 이분탐색을 위한 정렬 수행
        Arrays.sort(house);

        int left = 1;  // 최소 거리
        int right = house[n-1] - house[0]; // 최대 거리
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            // mid 거리로 설치 가능하면 정답 갱신
            if (install(mid)) {
                answer = mid;
                left = mid + 1;  // 더 넓게 설치할 수 있는지 확인
            }
            // 너무 멀리 설치하려 해서 실패
            else {
                right = mid - 1;  // 거리 줄이기
            }
        }

        System.out.println(answer);
    }

    static boolean install(int min) {
        int count = 1; // 첫 번째 집에 설치 후 아래 작업 수행
        int last = house[0];  // 마지막으로 설치한 집의 위치(첫 번째 부터 시작)

        for (int i = 1; i < n; i++) {
            // 현재 집이 마지막으로 설치한 집보다 min 이상 떨어져 있으면
            if (house[i] - last >= min) {
                count++;  // 공유기 설치
                last = house[i];
            }
        }

        return count >= c; // 설치한 공유기 수가 목표 이상이면 true
    }
}
