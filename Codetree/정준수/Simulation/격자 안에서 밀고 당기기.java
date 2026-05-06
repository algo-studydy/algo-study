import java.io.*;
import java.util.*;

public class ct0205 {
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st;

		String originText = br.readLine();
		int size = originText.length();
		String[] arr = originText.split("");
		
		int shiftCount = 0;
		int minSize = Integer.MAX_VALUE;
		while (shiftCount < size) {
			int startIdx = (size - shiftCount) % size;
			
			StringBuilder sb = new StringBuilder();
			for (int i=0;i<size;i++) {
				int idx = (startIdx + i) % size;
				String curr = arr[idx];
				
				sb.append(arr[idx]);
				int count = 1;
				i++;
				while (i<size) {
					int nextIdx = (startIdx + i) % size;
					String now = arr[nextIdx];
					
					if (curr.equals(now)) {
						count += 1;
						i++;
					} else {
						i--;
						break;
					}
				}
				
				sb.append(count);
			}
			
			minSize = Math.min(minSize, sb.toString().length());
			shiftCount += 1;
		}
		
		System.out.print(minSize);
	}
}
