package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1806 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int minNum = Integer.MAX_VALUE;
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] arr = new int[N+1];
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left=0;
		int right=0;
		int sum=0;
		while(right<=N) {
			if(sum<S) {
				sum+=arr[right];
				right++;
			}
			else if(sum>=S) {
				minNum=Math.min(minNum, right-left);
				sum-=arr[left];
				left++;
			}
			
		}
		System.out.println((minNum) == Integer.MAX_VALUE ? 0 : minNum);
	}
}
