import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct0301 {
	static int N, count;
	static void solution(int[] arr, int s, int e) {
		int idx = 0;

		for (int i=1;i<=N;i++) {
			if (arr[i] < 0) continue;

			idx += 1;

			if (idx >= s && idx <= e) {
				arr[i] = -1;
				count -= 1;
			}

			if (idx > e) break;
		}
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		count = N;
		int[] arr = new int[N + 1];

		for (int i=1;i<=N;i++) {
			int num = Integer.parseInt(br.readLine());
			arr[i] = num;
		}

		st = new StringTokenizer(br.readLine());
		int s1 = Integer.parseInt(st.nextToken());
		int e1 = Integer.parseInt(st.nextToken());
		solution(arr,s1,e1);
		st = new StringTokenizer(br.readLine());
		int s2 = Integer.parseInt(st.nextToken());
		int e2 = Integer.parseInt(st.nextToken());
		solution(arr,s2,e2);

		StringBuilder sb = new StringBuilder();
		sb.append(count).append("\n");
		for (int i=1;i<=N;i++) {
			if (arr[i] > 0) sb.append(arr[i]).append("\n");
		}

		System.out.print(sb.toString().trim());
	}
}
