import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct0403 {
	static int N, curY, curX, dir, time;
	static int[][] map;
	static boolean[][][] visited;
	static int[][] delta = {
			{0, 1},
			{-1, 0},
			{0, -1},
			{1, 0}
	};
	static int[][] checkDelta = {
			{1, 1},
			{-1, 1},
			{-1, -1},
			{1, -1}
	};
	static boolean isOut;

	static boolean isExists(int row, int col) {
		if (map[row][col] == 1) return true;
		return false;
	}
	static boolean isValid(int row, int col) {
		return (row>=0 && col>=0 && row<N && col<N);
	}

	static void go(int row, int col, int direction) {
		visited[row][col][direction] = true;

		for (int i=0; i<delta.length; i++) {
			int nextRow = row + delta[direction][0];
			int nextCol = col + delta[direction][1];

			if (!isValid(nextRow, nextCol)) {
				isOut = true;
				time += 1;
				return;
			}

			if (map[nextRow][nextCol] == 1) {
				direction = (direction + 1) % delta.length;
				continue;
			}

			if (map[nextRow][nextCol] == 0) {
				time += 1;
				int targetRow = row + checkDelta[direction][0];
				int targetCol = col + checkDelta[direction][1];
				if (visited[nextRow][nextCol][direction]) return;

				visited[nextRow][nextCol][direction] = true;
				if (isExists(targetRow, targetCol)) go(nextRow, nextCol, direction);
				else {
					if (map[nextRow][nextCol] == 1) return;
					time += 1;
					go(targetRow, targetCol, (direction - 1 + delta.length)  % delta.length);
				}
				break;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		curY = Integer.parseInt(st.nextToken()) - 1;
		curX = Integer.parseInt(st.nextToken()) - 1;
		map = new int[N][N];
		visited = new boolean[N][N][4];
		dir = 0;
		time = 0;
		isOut = false;

		for (int r=0; r<N; r++) {
			String inputRow = br.readLine();
			for (int c=0; c<N; c++) {
				map[r][c] = ('#' == inputRow.charAt(c) ? 1 : 0);
			}
		}

		go(curY, curX, dir);

		System.out.print(isOut ? time : -1);
	}
}


