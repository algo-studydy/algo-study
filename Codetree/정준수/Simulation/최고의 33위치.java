import java.io.*;
import java.util.*;

public class ct0101 {
	static final int K = 3;
	static int N;
	static int[][] map;
	
    public static void main(String[] args) throws IOException {
    	InputStreamReader isr = new InputStreamReader(System.in);
    	BufferedReader br = new BufferedReader(isr);
    	StringTokenizer st;
    	
    	N = Integer.parseInt(br.readLine());
    	map = new int[N][N];
    	
    	for (int r = 0; r < N; r++) {
    		st = new StringTokenizer(br.readLine());
    		for (int c = 0; c < N; c++) {
    			map[r][c] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	int answer = 0;
    	
    	for (int r = 0; r < N; r++) {
    		for (int c = 0; c < N; c++) {
    			if (!isValid(r, c)) continue;
    			
    			answer = Math.max(answer, sumArea(r, c));
    		}
    	}
    	
    	System.out.print(answer);
    }
    
    static boolean isValid(int startRow, int startCol) {
    	return (startRow + K <= N && startCol + K <= N);
    }
    

    static int sumArea(int startRow, int startCol) {
    	int count = 0;
    	
    	for (int r = startRow; r < startRow + K; r++) {
    		for (int c = startCol; c < startCol + K; c++) {
    			count += map[r][c];
    		}
    	}
    	
    	return count;
    }
}