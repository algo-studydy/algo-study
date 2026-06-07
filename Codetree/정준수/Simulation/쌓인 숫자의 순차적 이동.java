import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class ct0504 {
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
	static int[][][] map;
	static Deque<Integer> dQ;
	static Point[] pList;
	static int[][] delta = {
			{-1, 0},
			{1, 0},
			{0, -1},
			{0, 1},
			{-1, -1},
			{-1, 1},
			{1, -1},
			{1, 1}
	};

	static boolean isValid(int row, int col) {
		return (row>=0&&col>=0&&row<N&&col<N);
	}

	static void go() {
		while (!dQ.isEmpty()) {
			int target = dQ.pollFirst();
			Point p = pList[target];
			int curRow = pList[target].row;
			int curCol = pList[target].col;

			int nextRow = curRow;
			int nextCol = curCol;
			int maxValue = -1;

			// 가장 큰 원소 찾기
			for (int d = 0; d < delta.length; d++) {
				int tmpRow = p.row + delta[d][0];
				int tmpCol = p.col + delta[d][1];
				if (!isValid(tmpRow, tmpCol)) continue;
				if (map[tmpRow][tmpCol][1] == 0) continue;

				int value = -1;
				for (int i = 1; i <= map[tmpRow][tmpCol][0]; i++) {
					value = Math.max(value, map[tmpRow][tmpCol][i]);
				}

				if (maxValue < value) {
					nextRow = tmpRow;
					nextCol = tmpCol;
					maxValue = value;
				}
			}

			if (maxValue == -1) continue;

			// 이동
			int startIdx = map[nextRow][nextCol][0];
			int targetIdx = 1;
			for (int i = 1; i <= map[p.row][p.col][0]; i++) {
				if (map[curRow][curCol][i] != target) continue;
				targetIdx = i;
				break;
			}

			int offset = 0;
			int deleteCount = map[p.row][p.col][0] - targetIdx + 1;
			int size = map[p.row][p.col][0];
			for (int i = targetIdx; i <= size; i++) {
				int currValue = map[curRow][curCol][i];
				map[nextRow][nextCol][startIdx + ++offset] = currValue;
				pList[currValue].row = nextRow;
				pList[currValue].col = nextCol;
				map[curRow][curCol][i] = 0;
			}

			map[curRow][curCol][0] = map[curRow][curCol][0] - deleteCount;
			map[nextRow][nextCol][0] = map[nextRow][nextCol][0] + deleteCount;
		}
	}


	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N][N*N + 1];
		pList = new Point[N*N+1];
		dQ = new ArrayDeque<>();

		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<N;c++) {
				map[r][c][1] = Integer.parseInt(st.nextToken());
				pList[map[r][c][1]] = new Point(r, c);
				map[r][c][0] += 1;
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int m=0;m<M;m++) {
			dQ.add(Integer.parseInt(st.nextToken()));
		}

		go();

		StringBuilder sb = new StringBuilder();
		for (int r=0;r<N;r++) {
			for (int c=0;c<N;c++) {
				if (map[r][c][0] == 0) sb.append("None").append("\n");
				else {
					for (int i=map[r][c][0];i>=1;i--) {
						sb.append(map[r][c][i]);
						if (i != 1) sb.append(" ");
					}
					sb.append("\n");
				}
			}
		}
		System.out.print(sb.toString().trim());
	}
}
