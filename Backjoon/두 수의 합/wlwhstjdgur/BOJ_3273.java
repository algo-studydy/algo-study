package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3273 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int cnt=0;
		int n,x;
		int [] arr;
		n =Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(bf.readLine());
		x = Integer.parseInt(st.nextToken());

		Arrays.sort(arr);
		//정렬후 양쪽 끝단에서 더하고 만들어야 될 수보다
		//작으면 왼쪽 끝을 당긴다
		//크면 오른쪽 끝을 당긴다
		int left=0;
		int right=n-1;
		while(left<right) {
			int sum = arr[left]+arr[right];
			if(sum==x) {
				cnt++;
			}
			if(sum<x) {
				left++;
			}
			else {
				right--;
			}
			
		}
		System.out.println(cnt);
	}
}
