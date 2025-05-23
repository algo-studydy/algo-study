import java.util.*;

public class BOJ20366같이눈사람만들래 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] snow = new int[n];

        for (int i = 0; i < n; i++) {
            snow[i] = sc.nextInt();
        }

        List<int[]> pair = new ArrayList<>();

        // 두 눈덩이 조합의 합과 인덱스 저장 [합, i, j]
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                pair.add(new int[]{snow[i] + snow[j], i, j});
            }
        }

        // 합 기준으로 정렬
        pair.sort((a, b) -> Integer.compare(a[0], b[0]));

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < pair.size(); i++) {
            int[] a = pair.get(i);

            for (int j = i + 1; j < pair.size(); j++) {
                int[] b = pair.get(j);
                // 선택된 조합이 같은 눈덩이를 사용하면 스킵
                if (a[1] == b[1] || a[1] == b[2] || a[2] == b[1] || a[2] == b[2]) continue;

                int diff = Math.abs(a[0] - b[0]);
                answer = Math.min(answer, diff);

                // 0이면 더 이상 탐색할 필요 없음
                if (answer == 0) {
                    System.out.println(0);
                    return;
                }
                break;
            }
        }

        System.out.println(answer);
    }
}
