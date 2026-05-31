import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct0502 {
	static class Point {
		int row, col;

		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return String.format("row: "+row+" col: "+col);
		}
	}

	static int N, M;
	static int[][] map;
	static int[][] delta = {
			{-1, 0},
			{1, 0},
			{0, -1},
			{0, 1},
			{-1, -1},
			{-1, 1},
			{1, 1},
			{1, -1}
	};
	static Point[] pList;

	static void printMap() {
		StringBuilder sb = new StringBuilder();
		for (int r=0;r<N;r++) {
			for (int c=0;c<N;c++) {
				sb.append(map[r][c]);
				if (c != N-1) sb.append(" ");
			}
			sb. append("\n");
		}
		System.out.print(sb.toString().trim());
	}

	static boolean isValid(int row, int col) {
		return (row>=0&&col>=0&&row<N&&col<N);
	}

	static void go() {
		while (M-- > 0) {
			for (int i=0;i<N*N;i++) {
				int nextRow = pList[i].row;
				int nextCol = pList[i].col;
				int maxVal = -1;

				// 인접한 요소 중 가장 큰 원소 위치 찾기
				for (int d=0;d<delta.length;d++) {
					int tmpRow = pList[i].row + delta[d][0];
					int tmpCol = pList[i].col + delta[d][1];

					if (!isValid(tmpRow, tmpCol)) continue;
					if (map[tmpRow][tmpCol] > maxVal) {
						nextRow = tmpRow;
						nextCol = tmpCol;
						maxVal = map[nextRow][nextCol];
					}
				}

				// 중앙과 값 교환
				map[pList[i].row][pList[i].col] = maxVal;
				map[nextRow][nextCol] = i + 1;

				// 위치 업데이트
				pList[maxVal-1].row = pList[i].row;
				pList[maxVal-1].col = pList[i].col;
				pList[i].row = nextRow;
				pList[i].col = nextCol;
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
		map = new int[N][N];
		pList = new Point[N*N];

		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				pList[map[r][c] - 1] = new Point(r, c);
			}
		}

		go();

		// 정답 출력
		printMap();
	}
}
