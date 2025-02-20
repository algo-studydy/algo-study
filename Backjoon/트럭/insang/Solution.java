import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 트럭 개수
        int w = sc.nextInt(); // 다리 길이
        int l = sc.nextInt(); // 다리 최대 하중
        int answer = 0;

        int[] car = new int[n];
        for (int i = 0; i < n; i++) {
            car[i] = sc.nextInt();
        }

        Queue<Integer> bridge = new ArrayDeque<>();
        int totalW = 0;  // 다리 위의 트럭 무게 합

        // 다리를 다리 길이만큼 0으로 초기화
        for (int i = 0; i < w; i++) {
            bridge.add(0);
        }

        int i = 0;  // 현재 다리를 지나려는 트럭의 인덱스
        while (i < n) {
            answer++; // 1초 증가

            // 다리에서 맨 앞의 트럭을 제거
            totalW -= bridge.poll();

            // 새로운 트럭이 다리에 올라갈 수 있는지 확인
            if (totalW + car[i] <= l) {
                bridge.add(car[i]);
                totalW += car[i];
                i++; // 다음 트럭 이동
            } else {
                bridge.add(0); // 트럭을 올라갈 수 없으면 0을 추가하여 시간만 흐르게 함
            }
        }

        // 마지막 트럭이 다리를 완전히 건너는 시간 추가
        answer += w;

        System.out.println(answer);
    }
}
