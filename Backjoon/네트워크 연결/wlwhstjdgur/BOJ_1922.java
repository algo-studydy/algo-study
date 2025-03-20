package Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import Study.BOJ_1197.Vertex;

public class BOJ_1922 {
	static class Vertex implements Comparable<Vertex> {
		int e;
		int w;
		public Vertex(int e, int w) {
			super();
			this.e = e;
			this.w = w;
		}
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.w,o.w);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N,M;
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(bf.readLine());
		M = Integer.parseInt(st.nextToken());
		List<Vertex>[] adj = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			adj[i] = new ArrayList();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[a].add(new Vertex(b,c));
			adj[b].add(new Vertex(a,c));
		}
		
		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		PriorityQueue<Vertex>pq = new PriorityQueue<Vertex>();
		boolean[] visited = new boolean[N+1];
		pq.offer(new Vertex(1, 0));
		distance[1]=0;
		int sum=0;
		while(!pq.isEmpty()) {
			Vertex v = pq.poll();
			if(visited[v.e]==true) {
				continue;
			}
			visited[v.e] = true;
			sum+=v.w;
			for(Vertex temp : adj[v.e]) {
				if(visited[temp.e]==false&&distance[temp.e]>temp.w) {
					pq.add(temp);
					distance[temp.e] = temp.w;
				}
			}
		}
		System.out.println(sum);
	}
}
