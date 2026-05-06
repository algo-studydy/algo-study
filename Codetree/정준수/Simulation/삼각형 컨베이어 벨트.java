import java.io.*;
import java.util.*;

public class ct0201 {
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[3 * N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=-0;i<N;i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i=N;i<2*N;i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i=2*N;i<3*N;i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int startIdx = 3 * N -  T % (3 * N);
		
		StringBuilder sb = new StringBuilder();
		for (int i=-0;i<3*N;i++){
			sb.append(arr[(startIdx + i) % (3 * N)]);
			
			if (i == N-1) sb.append("\n");
			else if (i == 2 * N - 1) sb.append("\n");
			else sb.append(" ");
		}

		System.out.print(sb.toString().trim());
	}
}
