import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct0304 {
	static final int N = 4;
	static String dir;
	static int[][] map;

	static boolean isValid(int idx) {
		return (idx >= 0 && idx < N);
	}

	static void solution() {
		int startIdx = 0;
		int delta = 1;
		boolean isRowFixed = false;

		// 오프셋 설정하기
		if ("R".equals(dir)) {
			isRowFixed = true;
			startIdx = N-2;
			delta = -1;
		} else if ("L".equals(dir)) {
			isRowFixed = true;
			startIdx = 1;
			delta = 1;
		} else if ("D".equals(dir)) {
			isRowFixed = false;
			startIdx = N-2;
			delta = -1;
		} else {
			isRowFixed = false;
			startIdx = 1;
			delta = 1;
		}

		// 합치기
		for (int i=0; i< N; i++) {
			int currNum = isRowFixed ? map[i][startIdx - delta] : map[startIdx - delta][i];
			int currIdx = startIdx - delta;

			for (int j=startIdx; isValid(j); j += delta) {

				if (isRowFixed) {
					if (map[i][j] == 0) continue;

					// 행 고정
					if (map[i][j] == currNum) {
						map[i][j] *= 2;
						map[i][currIdx] = 0;
						currNum = -1;
					} else {
						currNum = map[i][j];
						currIdx = j;
					}
				} else {
					// 열 고정
					if (map[j][i] == 0) continue;

					if (map[j][i] == currNum) {
						map[j][i] *= 2;
						map[currIdx][i] = 0;
						currNum = -1;
					} else {
						currNum = map[j][i];
						currIdx = j;
					}
				}
			}
		}

		// 둔디기 탁
		for (int i=0; i< N; i++) {
			int tmpIdx = startIdx - delta;
			int[] tmp = new int[N];
			for (int j = tmpIdx; isValid(j); j += delta) {
				if (isRowFixed) {
					if (map[i][j] == 0) continue;
					tmp[tmpIdx] = map[i][j];
					tmpIdx += delta;
				} else {
					if (map[j][i] == 0) continue;
					tmp[tmpIdx] = map[j][i];
					tmpIdx += delta;
				}
			}
			if (isRowFixed) {
				System.arraycopy(tmp, 0, map[i], 0, N);
			} else {
				for (int k=0; k<N; k++) {
					map[k][i] = tmp[k];
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;

		map = new int[N][N];

		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dir = br.readLine();

		solution();

		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				sb.append(map[i][j]);
				if (j != N - 1) sb.append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb.toString().trim());
	}
}
