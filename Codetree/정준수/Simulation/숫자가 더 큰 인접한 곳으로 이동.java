import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct0401 {
	static int N, startRow, startCol;
	static int[][] map;
	static boolean[][] visited;
	static StringBuilder sb;
	static int[][] delta = {
			{-1, 0},
			{1, 0},
			{0, -1},
			{0, 1}
	};

	static boolean isValid(int row, int col) {
		return (row >= 0 && col >= 0 && row < N && col < N);
	}

	static void go(int row, int col) {
		int targetRow = -1;
		int targetCol = -1;
		int targetAmount = -1;

		for (int d=0;d<delta.length;d++) {
			int nextRow = row + delta[d][0];
			int nextCol = col + delta[d][1];

			if (!isValid(nextRow, nextCol)) continue;
			if (visited[nextRow][nextCol]) continue;

			if (map[row][col] < map[nextRow][nextCol]) {
				targetAmount = map[nextRow][nextCol];
				targetRow = nextRow;
				targetCol = nextCol;
				break;
			}
		}

		if (targetAmount < 0) return;

		visited[targetRow][targetCol] = true;
		sb.append(targetAmount).append(" ");
		go(targetRow, targetCol);
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		startRow = Integer.parseInt(st.nextToken()) - 1;
		startCol = Integer.parseInt(st.nextToken()) - 1;
		map = new int[N][N];
		visited = new boolean[N][N];
		sb = new StringBuilder();

		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		visited[startRow][startCol] = true;
		sb.append(map[startRow][startCol]).append(" ");
		go(startRow, startCol);

		System.out.print(sb.toString().trim());
	}
}
