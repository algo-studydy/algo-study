package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13335 {
		public static void main(String[] args) throws IOException{
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(bf.readLine());
			Queue<Integer> truckQ = new LinkedList<Integer>();
			Queue<Integer> bridgeQ = new LinkedList<Integer>();
			int n,w,L;
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < n; i++) {
				truckQ.offer(Integer.parseInt(st.nextToken()));
			}
			int time = 0; 
			int bridgeWeight=0;
			for(int i =0; i<w ; i++) {
				bridgeQ.offer(0);
			}
			while(!bridgeQ.isEmpty()) {
				time++;
				bridgeWeight-=bridgeQ.poll();
				if(!truckQ.isEmpty()) {
					if(truckQ.peek()+bridgeWeight<=L) {
						bridgeWeight+=truckQ.peek();
						bridgeQ.offer(truckQ.poll());
					}else {
						bridgeQ.offer(0);
					}
				}
			}
			System.out.println(time);
		}
}
