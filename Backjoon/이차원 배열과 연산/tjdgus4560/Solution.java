import java.util.*;

public class Main {

    public static class Node implements Comparable<Node> {
        int num;
        int cnt;

        public Node(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            if (this.cnt == o.cnt) {
                return this.num - o.num;
            }
            return this.cnt - o.cnt;
        }
    }

    static int[][] arr = new int[100][100];
    static int rSize = 3; // 현재 행의 크기
    static int cSize = 3; // 현재 열의 크기

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt() - 1;
        int c = sc.nextInt() - 1;
        int k = sc.nextInt();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int time = 0;
        while (time <= 100) {
            // 목표 위치에 목표값이 존재하는 경우 종료
            if (arr[r][c] == k) {
                System.out.println(time);
                return;
            }

            // r,c 크기에 따라 연산
            if (rSize >= cSize) {
                RCalc();
            } else {
                CCalc();
            }

            time++;
        }

        System.out.println(-1);
    }

    // 행 단위 연산
    private static void RCalc() {
        int maxC = 0; // 연산 후 가장 긴 열의 길이

        for (int i = 0; i < rSize; i++) {
            Map<Integer, Integer> map = new HashMap<>();

            // 각 숫자 등장 횟수 카운트
            for (int j = 0; j < cSize; j++) {
                int num = arr[i][j];
                if (num == 0) {
                    continue;
                }
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            // 리스트 변환 후 정렬
            List<Node> li = new ArrayList<>();
            for (Integer key : map.keySet()) {
                li.add(new Node(key, map.get(key)));
            }
            Collections.sort(li);

            int idx = 0; // 열 인덱스
            for (Node node : li) {
                if (idx >= 100) {
                    break; // 최대 100개 제한
                }
                arr[i][idx++] = node.num;
                if (idx >= 100) {
                    break;
                }
                arr[i][idx++] = node.cnt;
            }

            for (int j = idx; j < 100; j++) {
                arr[i][j] = 0;
            }

            // 가장 긴 C 저장
            maxC = Math.max(maxC, idx);
        }

        cSize = maxC;
    }

    // 열 단위 연산
    private static void CCalc() {
        int maxR = 0; // 연산 후 가장 긴 행의 길이

        for (int j = 0; j < cSize; j++) {
            Map<Integer, Integer> map = new HashMap<>(); // 숫자별 등장 횟수 저장

            // 각 숫자 등장 횟수 카운트
            for (int i = 0; i < rSize; i++) {
                int num = arr[i][j];
                if (num == 0) {
                    continue;
                }
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            // 리스트 변환 후 정렬
            List<Node> li = new ArrayList<>();
            for (Integer key : map.keySet()) {
                li.add(new Node(key, map.get(key)));
            }
            Collections.sort(li);

            int idx = 0; // 행 인덱스
            for (Node node : li) {
                if (idx >= 100) {
                    break;
                }
                arr[idx++][j] = node.num;
                if (idx >= 100) {
                    break;
                }
                arr[idx++][j] = node.cnt;
            }


            for (int i = idx; i < 100; i++) {
                arr[i][j] = 0;
            }

            // 가장 긴 R 저장
            maxR = Math.max(maxR, idx);

        }

        rSize = maxR;
    }
}
