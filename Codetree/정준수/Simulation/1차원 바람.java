import java.io.*;
import java.util.*;

public class ct0203 {
	static int N, M, Q;
	static int[][] map;
	static int[] startIdx;
	
	static boolean isValid(int row) {
		return (row >= 0 && row < N);
	}
	
	static boolean checkEffect(int rowNum1, int rowNum2) {
		for (int i=0;i<M;i++) {
			int e1 = map[rowNum1][(startIdx[rowNum1] + i) % M];
			int e2 = map[rowNum2][(startIdx[rowNum2] + i) % M];
			
			if (e1 == e2) return true;
		}
		
		return false;
	}
	
	static void solution(int targetRow, int direction, int beforeRow) {
		int shiftDelta = (M + startIdx[targetRow] + direction) % M;

		startIdx[targetRow] = shiftDelta;
		
		int nextDelta = targetRow - beforeRow;
		if (nextDelta == 0) {
			if (isValid(targetRow + 1) && checkEffect(targetRow ,targetRow + 1)) {
				solution(targetRow + 1, -1 * direction, targetRow);
			}
			if (isValid(targetRow - 1) && checkEffect(targetRow ,targetRow - 1)) {
				solution(targetRow - 1, -1 * direction, targetRow);
			}
		} else {
			if (isValid(targetRow + nextDelta) && checkEffect(targetRow ,targetRow + nextDelta)) {
				solution(targetRow + nextDelta, -1 * direction, targetRow);
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
		map = new int[N][M];
		startIdx = new int[N];

		for (int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for( int c=0;c<M;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int targetRow = Integer.parseInt(st.nextToken()) - 1;
			int direction = st.nextToken().equals("L") ? -1 : 1;
			
			solution(targetRow, direction, targetRow);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int r=0;r<N;r++) {
			for( int c=0;c<M;c++) {
				sb.append(map[r][(startIdx[r] + c) % M]);
				if (c != M-1) sb.append(" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb.toString().trim());
	}
}
