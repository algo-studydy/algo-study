import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct0402 {
	static int N, M, K;
	static int[][] map;

	static boolean isBlocked(int row) {
		for (int c=K; c<=K+M-1; c++) {
			if (map[row][c] > 0) return true;
		}
		return false;
	}

	static void fillCol(int row) {
		for (int c=K; c<=K+M-1; c++) {
			map[row][c] = 1;
		}
	}

	static void go() {
		int nextRow = -1;

		for (int r=-1; r<N; r++) {
			nextRow = r + 1;

			if (nextRow >= N) {
				fillCol(nextRow - 1);
				break;
			}
			if (isBlocked(nextRow)) {
				fillCol(nextRow - 1);
				break;
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
		K = Integer.parseInt(st.nextToken()) - 1;

		map = new int[N][N];

		for (int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		go();

		StringBuilder sb = new StringBuilder();
		for (int r=0; r<N; r++) {
			for (int c=0; c<N; c++) {
				sb.append(map[r][c]);
				if (c != N-1) sb.append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb.toString().trim());
	}
}
