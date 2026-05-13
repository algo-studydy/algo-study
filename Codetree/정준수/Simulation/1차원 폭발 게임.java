import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct0303 {
	static int N, M;
	static int[] arr, tmp;
	static int remain;

	static boolean isValid(int idx) {
		return (idx >= 0 && idx < N);
	}

	static void deleteNum(int idx, int target, int delta) {
		while (isValid(idx)) {
			if (arr[idx] != -1 && arr[idx] != target) return;
			if (arr[idx] == -1) {
				idx += delta;
				continue;
			}

			tmp[idx] = -1;
			idx += delta;
		}
	}

	static void solution() {
		while (true) {
			boolean isWork = false;
			int count = 0;
			int currNum = -1;

			System.arraycopy(arr, 0, tmp, 0, N);
			for (int i=0; i<N; i++) {
				if (arr[i] < 0) continue;

				if (arr[i] == currNum) {
					count += 1;
				} else {
					count = 1;
					currNum = arr[i];
				}

				if (count == M) {
					isWork = true;
					int target = arr[i];
					deleteNum(i, target, -1);
					deleteNum(i, target, +1);
				}
			}
			System.arraycopy(tmp, 0, arr, 0, N);
			if (!isWork) break;
		}
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		remain = N;
		arr = new int[N];
		tmp = new int[N];



		solution();

		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			if (arr[i] < 0) remain -= 1;
		}
		sb.append(remain).append("\n");
		for (int i=0; i<N; i++) {
			if (arr[i] > 0) sb.append(arr[i]).append("\n");
		}

		System.out.print(sb.toString().trim());
	}
}
