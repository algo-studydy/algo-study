import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct0404 {
	static int N, M, curRow, curCol, curValue;
	static int[][] map;
	static int[][] delta = {
			{0, 1},
			{1, 0},
			{0, -1},
			{-1, 0}
	};
	static int[][] dice = {
			{0, 5, 0, 0},
			{4, 1, 3, 6},
			{0, 2, 0, 0}
	};

	static boolean isValid(int row, int col) {
		return (row>=0 && col>=0 && row<N && col<N);
	}

	static int calcArea() {
		int sum = 0;
		for (int r=0;r<N;r++) {
			for (int c=0;c<N;c++) {
				sum += map[r][c];
			}
		}

		return sum;
	}

	static int convertDir(String dir) {
		int returnValue = 0;
		switch(dir) {
			case("R"): returnValue = 0; break;
			case("D"): returnValue = 1; break;
			case("L"): returnValue = 2; break;
			case("U"): returnValue = 3; break;
		}
		return returnValue;
	}

	static void reposition(int direction) {
		int tmp = 0;
		if (direction == 0) { // 오른쪽
			tmp = dice[1][3];
			dice[1][3] = dice[1][2];
			dice[1][2] = dice[1][1];
			dice[1][1] = dice[1][0];
			dice[1][0] = tmp;
		} else if (direction == 1) { // 아래
			tmp = dice[1][3];
			dice[1][3] = dice[2][1];
			dice[2][1] = dice[1][1];
			dice[1][1] = dice[0][1];
			dice[0][1] = tmp;
		} else if (direction == 2) { // 왼쪽
			tmp = dice[1][0];
			dice[1][0] = dice[1][1];
			dice[1][1] = dice[1][2];
			dice[1][2] = dice[1][3];
			dice[1][3] = tmp;
		} else { // 위
			tmp = dice[2][1];
			dice[2][1] = dice[1][3];
			dice[1][3] = dice[0][1];
			dice[0][1] = dice[1][1];
			dice[1][1] = tmp;
		}
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		curRow = Integer.parseInt(st.nextToken()) - 1;
		curCol = Integer.parseInt(st.nextToken()) - 1;
		map = new int[N][N];
		curValue = 1;

		map[curRow][curCol] = 6;
		st = new StringTokenizer(br.readLine());
		while (M-- > 0) {
			int direction = convertDir(st.nextToken());
			int nextRow = curRow + delta[direction][0];
			int nextCol = curCol + delta[direction][1];

			if (!isValid(nextRow, nextCol)) continue;

			reposition(direction);
			map[nextRow][nextCol] = dice[1][3];

			curRow = nextRow;
			curCol = nextCol;
		}

		System.out.print(calcArea());
	}
}
