import java.io.*;

public class BOJ31455쿠키자르기 {
    static int[][] cookie;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int tc = 0; tc < t; tc++){
            int n = Integer.parseInt(br.readLine());
            cookie = new int[n][n];

            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                for (int j = 0; j < n; j++) {
                    // 문자를 숫자로 변환
                    cookie[i][j] = line.charAt(j) - '0';
                }
            }
            // 시작 좌표, 탐색 격자 크기
            int result = divide(0, 0, n);
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }

    // 시작 좌표, 조각 크기
    static int divide(int r, int c, int size) {
        if (size == 1) {
            return cookie[r][c];
        }

        // 현재 조각의 숫자 총합
        int total = 0;
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                total += cookie[i][j];
            }
        }

        // 좌상(0), 우상(1), 좌하(2), 우하(3)
        int[][] area = {
                {r, c},
                {r, c + size / 2},
                {r + size / 2, c},
                {r + size / 2, c + size / 2}
        };

        int sum = 0;
        // 4개의 조각 중, 먹어야 하는 조각을 제외하고 나머지 조각 재귀 호출
        for (int i = 0; i < 4; i++) {
            // 먹어야 하는 조각 제외
            if (i == total % 4) continue;
            sum += divide(area[i][0], area[i][1], size / 2);
        }

        return sum;
    }
}
