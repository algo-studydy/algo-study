import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct0505 {
	static class Point {
		int row, col, mass, direction, number;

		Point(int row, int col, int mass, int direction, int number) {
			this.row = row;
			this.col = col;
			this.mass = mass;
			this.direction = direction;
			this.number = number;
		}

		@Override
		public String toString() {
			return String.format("["+number+"]"+" row: "+this.row+", col: "+this.col+", d: "+direction+", mass: "+this.mass);
		}
	}

	static int N, M, T;
	static Point[][] map, nextMap;
	static int maxWeight, remain;
	static int[][] delta = {
			{-1, 0},
			{0, -1},
			{1, 0},
			{0, 1}
	};

	static boolean isValid(int row, int col) {
		return (row>=0&&col>=0&&row<N&&col<N);
	}

	static void go() {
		while (T-- > 0) {
			for (int r=0;r<N;r++) {
				for (int c=0;c<N;c++) {
					if (map[r][c] == null) continue;

					Point point = map[r][c];
					int currRow = point.row;
					int currCol = point.col;
					int mass = point.mass;
					int direction = point.direction;
					int number = point.number;

					// 다음 이동 좌표 및 방향 결정
					int nextRow = currRow + delta[direction][0];
					int nextCol = currCol + delta[direction][1];
					if (!isValid(nextRow, nextCol)) {
						direction = (direction + 2) % delta.length;
						nextRow = currRow;
						nextCol = currCol;
					}

					// 구슬 이동
					if (nextMap[nextRow][nextCol] != null) { // 겹치는 경우 (이동 좌표에 다른 구슬이 존재하는 경우)
						Point point2 = nextMap[nextRow][nextCol];

						if (point2.number > number) {
							number = point2.number;
							direction = point2.direction;
						}

						mass = mass + point2.mass;
						maxWeight = Math.max(maxWeight, mass);
					}
					map[currRow][currCol] = null;
					nextMap[nextRow][nextCol] = new Point(nextRow, nextCol, mass, direction, number);
				}
			}

			// 이동결과 반영
			int count = 0;
			for (int r=0;r<N;r++) {
				for (int c = 0; c < N; c++) {
					if (nextMap[r][c] == null) continue;
					Point p = nextMap[r][c];
					map[r][c] = new Point(p.row, p.col, p.mass, p.direction, p.number);
					nextMap[r][c] = null;
					count += 1;
				}
			}
			remain = count;
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
		map = new Point[N][N];
		nextMap = new Point[N][N];
		maxWeight = -1;

		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			String d = st.nextToken();
			int w = Integer.parseInt(st.nextToken());
			int num = i + 1;

			int direction = -1;
			switch (d) {
				case "U": direction=0;break;
				case "L": direction=1;break;
				case "D": direction=2;break;
				case "R": direction=3;break;
			}
			map[r][c] = new Point(r, c, w, direction, num);
			maxWeight = Math.max(maxWeight, w);
		}

		go();

		System.out.print(remain+" "+maxWeight);
	}
}
