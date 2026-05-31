import java.io.*;
import java.util.*;

public class ct0501 {
	static int N, M, T;
	static int[][] map, count, nextCount;
	static int remain;
	static int[][] delta = {
			{-1, 0},
			{1, 0},
			{0, -1},
			{0, 1}
	};

	static boolean isValid(int row, int col) {
		return (row>=0&&col>=0&&row<N&&col<N);
	}

	static void go() {
		while (T-- > 0) {
			for (int r=0;r<N;r++) {
				for (int c=0;c<N;c++) {
					if (count[r][c] == 0) continue;
					int nextRow = r;
					int nextCol = c;
					int maxVal = -1;

					// 구슬 이동
					for (int d=0;d<delta.length;d++) {
						int tmpRow = r + delta[d][0];
						int tmpCol = c + delta[d][1];

						if (!isValid(tmpRow, tmpCol)) continue;

						if (map[tmpRow][tmpCol] > maxVal) {
							nextRow = tmpRow;
							nextCol = tmpCol;
							maxVal = map[nextRow][nextCol];
						}
					}

					nextCount[nextRow][nextCol] += 1;
				}
			}

			// 충돌 처리
			for (int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					int update = nextCount[i][j];
					if (nextCount[i][j] > 1) {
						update = 0;
						remain -= nextCount[i][j];
					}

					count[i][j] = update;
					nextCount[i][j] = 0;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		count = new int[N][N];
		nextCount = new int[N][N];
		remain = M;

		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;

			count[row][col] = 1;
		}

		go();

		System.out.print(remain);
	}
}
