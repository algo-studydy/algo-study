package Study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.PriorityQueue;

public class BOJ_1197 {

	static class Vertex implements Comparable<Vertex>{
		int e;
		long w;
		public Vertex(int e, int w) {
			super();
			this.e = e;
			this.w = w;
		}
		@Override
		public int compareTo(Vertex o) {
			// TODO Auto-generated method stub
			return Long.compare(this.w, o.w);
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		List<Vertex>[] adj = new ArrayList[V+1];
		for(int i=0; i<V+1; i++) {
			adj[i] = new ArrayList();
		}
		for(int i=0;i<E;i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();
			adj[u].add(new Vertex(v,w));
			adj[v].add(new Vertex(u,w));
		}
		
		//최소값 저장
		long[] distance = new long[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		//방문했는가
		boolean[] visited =  new boolean[V+1];
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		
		q.add(new Vertex(1,0));
		distance[1]=0;
		int sum=0;
		while(!q.isEmpty()) {
			Vertex vers = q.poll();
			if(visited[vers.e]) {
				continue;
			}
			visited[vers.e]=true;
			sum+=vers.w;
			for(Vertex temp : adj[vers.e]) {
				if(visited[temp.e]==false&&distance[temp.e]>temp.w) {
					q.add(temp);
					distance[temp.e] = temp.w;
				}
			}
		}
		System.out.println(sum);
	}

}