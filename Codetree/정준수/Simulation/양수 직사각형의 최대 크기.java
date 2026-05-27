import java.io.*;
import java.util.*;

public class ct0107 {
	static int N, M;
	static int[][] map;
	static int ans = -1;
	
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<M;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
				
		for (int startRow=0;startRow<N;startRow++) {
			for (int startCol=0; startCol<M; startCol++) {
				
				int deltaHeight = N -1 - startRow;
				int deltaWidth = M -1 - startCol;
				for (int dr=0; dr<=deltaHeight; dr++) {
					for (int dc=0; dc<=deltaWidth; dc++) {
						int endRow = startRow + dr;
						int endCol = startCol + dc;
						
						int count = 0;
						L: for (int r=startRow; r<=endRow; r++) {
							for (int c=startCol; c<=endCol; c++) {
								if (map[r][c] <= 0) {
									count = -1;
									break L;
								}
								
								count += 1;
							}
						}
						
						ans = Math.max(ans, count);
					}
				}
			}
		}
		
		System.out.print(ans);
	}
}
