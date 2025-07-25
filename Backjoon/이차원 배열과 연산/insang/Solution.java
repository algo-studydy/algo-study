import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BOJ17140이차원배열과연산 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt() - 1;
        int c = sc.nextInt() - 1;
        int k = sc.nextInt();

        int[][] arr = new int[100][100];

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                arr[i][j] = sc.nextInt();
            }
        }
        int row = 3;
        int col = 3;
        for(int t = 0; t <= 100; t++){
            // 종료 조건
            if (arr[r][c] == k) {
                System.out.println(t);
                return;
            }

            // R연산 수행
            if(row >= col){
                int maxRow = 0;  // 가장 큰 행의 길이 저장
                for(int i = 0; i < row; i++){
                    int[] cnt = new int[101];  // 등장 횟수 카운트 배열

                    for(int j = 0; j < col; j++){
                        cnt[arr[i][j]]++;
                    }

                    // 리스트에 [수, 등장횟수] 저장
                    ArrayList<int[]> list = new ArrayList<>();  // [수, 등장 횟수]
                    for(int num = 1; num <= 100; num++){
                        if(cnt[num] != 0) list.add(new int[] {num, cnt[num]});
                    }

                    // 수 등장횟수 오름차순 -> 수 오름차순 정렬
                    list.sort((o1, o2) -> {
                        if (o1[1] != o2[1]) return o1[1] - o2[1];
                        return o1[0] - o2[0];
                    });

                    // 배열 갱신 (길이가 100을 넘을 시 무시)
                    int len = 0;
                    for(int[] e : list){
                        if(len > 99) break;
                        arr[i][len++] = e[0];
                        if(len > 99) break;
                        arr[i][len++] = e[1];
                    }
                    maxRow = Math.max(maxRow, len);

                    for (int j = len; j < 100; j++) {
                        arr[i][j] = 0;
                    }
                }
                col = maxRow;

            }
            // C연산 수행
            else {
                int maxCol = 0;
                for (int i = 0; i < col; i++) {
                    int[] cnt = new int[101];
                    for (int j = 0; j < row; j++) {
                        cnt[arr[j][i]]++;
                    }

                    ArrayList<int[]> list = new ArrayList<>();
                    for (int num = 1; num <= 100; num++) {
                        if (cnt[num] != 0) list.add(new int[] {num, cnt[num]});
                    }

                    list.sort((o1, o2) -> {
                        if (o1[1] != o2[1]) return o1[1] - o2[1];
                        return o1[0] - o2[0];
                    });

                    int len = 0;
                    for (int[] e : list) {
                        if (len > 99) break;
                        arr[len++][i] = e[0];
                        if (len > 99) break;
                        arr[len++][i] = e[1];
                    }

                    maxCol = Math.max(maxCol, len);

                    for (int j = len; j < 100; j++) {
                        arr[j][i] = 0;
                    }
                }
                row = maxCol;
            }
        }

        System.out.println(-1);
    }
}
