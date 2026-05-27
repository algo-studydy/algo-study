import java.io.*;
import java.util.*;

public class ct0102 {
	static int N, M;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
				
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		for (int i=0; i<N; i++) {
			int rowElement = -1;
			int colElement = -1;
			int rowCount = 0;
			int colCount = 0;
			int rowOk = 0;
			int colOk = 0;
			
			for(int j=0; j<N; j++) {
				// 행고정
				if (map[i][j] == rowElement) rowCount += 1;
				else rowCount = 0;
				if (rowCount == M) rowOk = 1; 
				
				// 열고정 
				if (map[j][i] == colElement) colCount += 1;
				else colCount = 0;
				if (colCount == M) colOk = 1;
				
				rowElement = map[i][j];
				colElement = map[j][i];
			}
			
			answer = answer + rowOk + colOk;
		}
		
		System.out.print(answer);
	}
}
