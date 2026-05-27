import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.StringTokenizer;

public class ct0302 {
	static int N;
	static int[][] arr;
	static int rBom, cBom;
	static int[][] delta = {
			{0,1},
			{1,0},
			{0,-1},
			{-1,0}
	};

	static boolean isValid(int row, int col) {
		return (row >= 0 && col >= 0 && row < N && col < N);
	}

	static void solution() {
		// 폭탄 영향을 받는 범위 원소 삭제
		int size = arr[rBom][cBom];

		for (int d=0; d<delta.length; d++) {
			for (int w=0; w<size; w++) {
				int nextRow = rBom + w * delta[d][0];
				int nextCol = cBom + w * delta[d][1];

				if (!isValid(nextRow, nextCol)) continue;

				arr[nextRow][nextCol] = -1;
			}
		}

		// 중력으로 떨어지기
		for (int col=0; col < N; col++) {
			int idx = N;
			int[] tmp = new int[N];
			for (int row=N-1; row>=0; row--) {
				if (arr[row][col] < 0) continue;
				idx -= 1;
				tmp[idx] = arr[row][col];
			}
			for (int row=0; row<N; row++) {
				arr[row][col] = tmp[row];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		rBom = Integer.parseInt(st.nextToken()) - 1;
		cBom = Integer.parseInt(st.nextToken()) - 1;

		solution();

		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				sb.append(arr[r][c]);
				if (c != N - 1) sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString().trim());
	}
}
