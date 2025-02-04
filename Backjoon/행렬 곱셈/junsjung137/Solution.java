import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] A = new int[N][M];

        for (int i = 0; i < N; i++) {   // 2차원 A 행렬(N x M) 초기화
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        st.nextToken(); // M 위에서 이미 초기화 -> 스킵
        int K = Integer.parseInt(st.nextToken());
        int[][] B = new int[M][K];
        for (int i = 0; i < M; i++) {   // 2차원 B 행렬(M x K) 초기화
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] result = new int[N][K];
        for (int i = 0; i < N; i++) {   // 그냥 행렬 곱셈
            for (int j = 0; j < K; j++) {
                for (int l = 0; l < M; l++) {
                    result[i][j] += A[i][l] * B[l][j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int[] row : result) {  // 2차원 행렬 출력
            for (int num : row) {
                sb.append(num).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}