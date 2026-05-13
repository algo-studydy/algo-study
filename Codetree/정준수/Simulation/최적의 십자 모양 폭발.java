import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct0305 {
	static int N;
	static int[][] arr, tmp;
	static int rBom, cBom;
	static int[][] delta = {
			{0,1},
			{1,0},
			{0,-1},
			{-1,0}
	};
	static int ans;

	static boolean isValid(int row, int col) {
		return (row >= 0 && col >= 0 && row < N && col < N);
	}

	static void solution() {
		// 폭탄 영향을 받는 범위 원소 삭제
		int size = tmp[rBom][cBom];

		for (int d=0; d<delta.length; d++) {
			for (int w=0; w<size; w++) {
				int nextRow = rBom + w * delta[d][0];
				int nextCol = cBom + w * delta[d][1];

				if (!isValid(nextRow, nextCol)) continue;

				tmp[nextRow][nextCol] = -1;
			}
		}

		// 중력으로 떨어지기
		for (int col=0; col < N; col++) {
			int idx = N;
			int[] t = new int[N];
			for (int row=N-1; row>=0; row--) {
				if (tmp[row][col] < 0) continue;
				idx -= 1;
				t[idx] = tmp[row][col];
			}
			for (int row=0; row<N; row++) {
				tmp[row][col] = t[row];
			}
		}
	}

	static void calcPair() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int d=0; d<delta.length; d++) {
					int nextRow = i + delta[d][0];
					int nextCol = j + delta[d][1];

					if (!isValid(nextRow, nextCol)) continue;

					if (tmp[i][j] > 0 && tmp[i][j] == tmp[nextRow][nextCol]) count += 1;
				}
			}
		}

		ans = Math.max(ans, count);
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		tmp = new int[N][N];
		ans = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				tmp[i][j] = arr[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				rBom = i;
				cBom = j;

				solution();
				calcPair();

				for (int k=0;k<N;k++) {
					System.arraycopy(arr[k], 0, tmp[k], 0, N);
				}
			}
		}

		solution();

		System.out.print(ans / 2);
	}
}
