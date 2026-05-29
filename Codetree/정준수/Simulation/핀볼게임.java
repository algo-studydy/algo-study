import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct0405 {
	static int N, time;
	static int[][] map;
	static int[][] delta = {
			{0, 1},
			{1, 0},
			{0, -1},
			{-1, 0}
	};

	static boolean isValid(int row, int col) {
		return (row>=0 && col>=0 && row<N && col<N);
	}

	static int detDirection(int row, int col, int direction) {
		int dir = -1;

		if (map[row][col] == 1) {
			switch(direction) {
				case 0: dir = 3; break;
				case 1: dir = 2; break;
				case 2: dir = 1; break;
				case 3: dir = 0; break;
			}
		} else {
			switch(direction) {
				case 0: dir = 1; break;
				case 1: dir = 0; break;
				case 2: dir = 3; break;
				case 3: dir = 2; break;
			}
		}
		return dir;
	}

	static void go(int row, int col, int direction, int count) {
		if (!isValid(row, col)) {
			time = Math.max(time, count);
			return;
		}

		if (map[row][col] == 0) {
			go(row + delta[direction][0], col + delta[direction][1], direction, count+1);
		} else {
			int dir = detDirection(row, col, direction);

			int nextRow = row + delta[dir][0];
			int nextCol = col + delta[dir][1];

			go(nextRow, nextCol, dir, count+1);
		}
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		time = 0;

		for (int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i=0;i<N;i++) {
			go(0, i, 1, 1); // 수평 아래
			go(N-1, i, 3, 1); // 수평 위
			go(i, 0, 0, 1); // 수직 오른쪽
			go(i, N-1, 2, 1);// 수직 왼쪽
		}

		System.out.print(time);
	}
}
