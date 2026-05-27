import java.io.*;
import java.util.*;

public class ct0103 {
	static int N, M;
	static int[][] map;
	
	static int[][][] LShape = {
			{{-1, 0}, {0, 1}},
			{{-1, 0}, {0, -1}},
			{{1, 0}, {0, 1}},
			{{1, 0}, {0, -1}},
	};
	static int[][][] IShape = {
			{{0, -1}, {0, 1}},
			{{-1, 0}, {1, 0}}
	};
	
	static boolean isValid(int row, int col) {
		return (row>=0 && row<N && col>=0 && col<M);
	}
	
	static int maxCount(int row, int col, int[][][] delta) {
		int shapeNum = delta.length;
		int maxSum = 0;
		
		for (int d=0;d<shapeNum;d++) {
			int sum = 0;
			int row1 = row + delta[d][0][0];
			int col1 = col + delta[d][0][1];
			int row2 = row + delta[d][1][0];
			int col2 = col + delta[d][1][1];
			
			if (!isValid(row1, col1)) continue;
			if (!isValid(row2, col2)) continue;
			
			sum = map[row][col] + map[row1][col1] + map[row2][col2];
			maxSum = Math.max(maxSum, sum);
		}

		return maxSum; 
	}
	
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0;c<M;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		
		for (int r=0;r<N;r++) {
			for(int c=0;c<M;c++) {
				// L자 
				answer = Math.max(answer, maxCount(r,c,LShape));
				
				// I자 
				answer = Math.max(answer, maxCount(r,c,IShape));
			}
		}
		
		System.out.print(answer);
	}
}
