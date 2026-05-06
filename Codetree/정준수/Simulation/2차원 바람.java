import java.io.*;
import java.util.*;

public class ct0204 {
	static int N, M, Q;
	static int[][][] map;
	static int[][] delta = {
			{0, 1},
			{1, 0},
			{0, -1},
			{-1, 0}
	};
	static int[] point1;
	static int[] point2;
	
	static boolean isValid(int row, int col) {
		return (row >= 0 && col >= 0 && row < N && col < M);
	}
	
	static boolean isValid2(int row, int col) {
		return (row >= point1[0] && col >= point1[1] && row <= point2[0] && col <= point2[1]);
	}
	
	static void meanArea(int row, int col) {
		int sum = 0;
		int count = 1;
		
		for (int d=0;d<delta.length;d++) {
			int nextRow = row + delta[d][0];
			int nextCol = col + delta[d][1];

			if (isValid(nextRow, nextCol)) {
				count += 1;
				sum += map[0][nextRow][nextCol];
			}
		}
		map[1][row][col] = (int) ((sum + map[0][row][col])/ count) ; 
	}
	
	static void rotateArea(int row, int col, int phase) {
		int nextRow = row + delta[phase][0];
		int nextCol = col + delta[phase][1];
		
		if (!isValid2(nextRow, nextCol)) {
			phase += 1;
			if (phase >= 4) return;
			nextRow = row + delta[phase][0];
			nextCol = col + delta[phase][1];
		}
		
		map[1][nextRow][nextCol] = map[0][row][col];
		rotateArea(nextRow, nextCol, phase);
	}
	
	static void solution() {
		rotateArea(point1[0], point1[1], 0);
		for (int r=point1[0];r<=point2[0];r++) {
			for (int c=point1[1];c<=point2[1];c++) {
				map[0][r][c] = map[1][r][c];
			}
		}
		
		for (int r=point1[0];r<=point2[0];r++) {
			for (int c=point1[1];c<=point2[1];c++) {
				meanArea(r, c);
			}
		}
		for (int r=point1[0];r<=point2[0];r++) {
			for (int c=point1[1];c<=point2[1];c++) {
				map[0][r][c] = map[1][r][c];
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		map = new int[2][N][M];
		point1 = new int[2];
		point2 = new int[2];
		
		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for( int c=0;c<M;c++) {
				map[0][r][c] = Integer.parseInt(st.nextToken());
				map[1][r][c] = map[0][r][c];
			}
		}
		
		for (int i=0;i<Q;i++) {
			st = new StringTokenizer(br.readLine());
			point1[0] = Integer.parseInt(st.nextToken()) - 1;
			point1[1] = Integer.parseInt(st.nextToken()) - 1;
			point2[0] = Integer.parseInt(st.nextToken()) - 1;
			point2[1] = Integer.parseInt(st.nextToken()) - 1;
			
			solution();
		}
		
		
		StringBuilder sb = new StringBuilder();
		for (int r=0;r<N;r++) {
			for( int c=0;c<M;c++) {
				sb.append(map[0][r][c]);
				if (c != M-1) sb.append(" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb.toString().trim());
	}
}
