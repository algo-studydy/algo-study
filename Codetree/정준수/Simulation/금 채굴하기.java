import java.io.*;
import java.util.*;

public class ct0104 {
	static int N, M;
	static int[][] map;
	static int ans;
	
	static boolean isValid(int row, int col) {
		return (row >= 0 && col >= 0 && row < N && col < N);
	}

	public static void main(String[] agrs) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			
			for (int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int centerRow=0;centerRow<N;centerRow++) {
			for (int centerCol=0;centerCol<N;centerCol++) {
				
				for (int k=0;k<=2*(N-1);k++) {
					int sum = 0;
					int count = 0;

					for (int dr=-k; dr<=k; dr++) {
						for (int dc=-k; dc<=k; dc++) {
							int nextRow = centerRow + dr;
							int nextCol = centerCol + dc;
	
							if (!isValid(nextRow, nextCol)) continue;
							if (Math.abs(centerRow - nextRow) + Math.abs(centerCol - nextCol) > k) continue;
							
							sum += M * map[nextRow][nextCol];
							count += map[nextRow][nextCol];
						}
					}
					
					if (sum >= 2*k*k + 2*k + 1) {
						ans = Math.max(ans, count);
					}
				}
				
			}
		}
		
		System.out.print(ans);
	}
}
