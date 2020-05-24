package com.bpeldi2oerkd8.lib;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstraTools {

	public static class Dijkstra{
		Graph g;
		long[] weights;

		public Dijkstra(Graph g) {
			this.g = g;
			weights = new long[g.n];
		}

		//最短距離の重み（O(ElogV)）
		public long getMinWeight(int start, int goal) {
			Arrays.fill(weights, Long.MAX_VALUE);
			weights[start-1] = 0;

			Queue<Pair> pq = new PriorityQueue<>((a, b) -> Long.compare(a.weight, b.weight));
			pq.add(new Pair(start, 0L));
			while(!pq.isEmpty()) {
				Pair p = pq.poll();

				//もしこの重みのほうが大きければ
				if(p.weight > weights[p.num-1])
					continue;

				for(Edge e : g.getEdges(p.num)) {
					//最短距離が更新できる場合
					if(weights[e.to-1] > p.weight + e.weight) {
						weights[e.to-1] = p.weight + e.weight;
						pq.add(new Pair(e.to, weights[e.to-1]));
					}
				}
			}

			return weights[goal-1];

		}

		//最短距離の重みの集合（O(ElogV)）
		public long[] getMinWeightArray(int start) {
			Arrays.fill(weights, Long.MAX_VALUE);
			weights[start-1] = 0;

			Queue<Pair> pq = new PriorityQueue<>((a, b) -> Long.compare(a.weight, b.weight));
			pq.add(new Pair(start, 0L));
			while(!pq.isEmpty()) {
				Pair p = pq.poll();

				//もしこの重みのほうが大きければ
				if(p.weight > weights[p.num-1])
					continue;

				for(Edge e : g.getEdges(p.num)) {
					//最短距離が更新できる場合
					if(weights[e.to-1] > p.weight + e.weight) {
						weights[e.to-1] = p.weight + e.weight;
						pq.add(new Pair(e.to, weights[e.to-1]));
					}
				}
			}

			return weights;

		}

		//最短経路（O(ElogV)）
		public Deque<Integer> getMinPath(int start, int goal) {
			Arrays.fill(weights, Long.MAX_VALUE);
			weights[start-1] = 0;

			Queue<Pair> pq = new PriorityQueue<>((a, b) -> Long.compare(a.weight, b.weight));
			pq.add(new Pair(start, 0L));
			while(!pq.isEmpty()) {
				Pair p = pq.poll();

				//もしこの重みのほうが大きければ
				if(p.weight > weights[p.num-1])
					continue;

				for(Edge e : g.getEdges(p.num)) {
					//最短距離が更新できる場合
					if(weights[e.to-1] > p.weight + e.weight) {
						weights[e.to-1] = p.weight + e.weight;
						pq.add(new Pair(e.to, weights[e.to-1]));
						g.setRoot(e.to, p.num);
					}
				}
			}

			if(weights[goal-1] == Long.MAX_VALUE)
				return null;

			Deque<Integer> path = new ArrayDeque<>();
			int now = goal;
			path.push(now);
			while(now != start) {
				now = g.getRoot(now);
				path.push(now);
			}

			return path;

		}
	}

	//ノードの番号と重みのペア（一時保存用）
	public static class Pair{
		int num;
		long weight;

		public Pair(int num, long weight) {
			this.num = num;
			this.weight = weight;
		}
	}

	//無向グラフ(UnDirected Graph)
	public static class UDGraph extends Graph{
		public UDGraph(int n) {
			super(n);
		}

		@Override
		public void addEdge(int from, int to) {
			//重みが1のグラフの場合(重みが均等な場合にも場合によっては使える)
			super.addEdge(from, to);
			super.addEdge(to, from);
		}

		@Override
		public void addEdge(int from, int to, long weight) {
			//エッジの重みが異なる場合
			super.addEdge(from, to, weight);
			super.addEdge(to, from, weight);
		}
	}

	//一般的なグラフ
	public static class Graph{
		int n;
		Map<Integer, List<Edge>> edges;
		boolean[] visited;
		int[] root;

		//1~Nまでをもつグラフ
		public Graph(int n) {
			this.n = n;
			edges = new HashMap<>(n);
			for(int i=0; i<n; i++) {
				List<Edge> e_list = new ArrayList<>();
				edges.put(i, e_list);
			}

			visited = new boolean[n];
			Arrays.fill(visited, false);

			root = new int[n];
			Arrays.fill(root, -1);
		}

		public void addEdge(int from, int to) {
			//重みが1のグラフの場合(重みが均等な場合にも場合によっては使える)
			edges.get(from-1).add(new Edge(to, 1));
		}

		public void addEdge(int from, int to, long weight) {
			//エッジの重みが異なる場合
			edges.get(from-1).add(new Edge(to, weight));
		}

		public void removeEdge(int from, int to) {
			//指定したノード間のエッジを削除
			edges.get(from-1).remove(new Edge(to, 1));
		}

		//ノード番号numのエッジの集合
		public List<Edge> getEdges(int num) {
			return edges.get(num-1);
		}

		//ノード番号numのルートノードのset
		public void setRoot(int num, int root_n) {
			root[num-1] = root_n;
		}

		//ノード番号numのルートノード
		public int getRoot(int num) {
			return root[num-1];
		}

		//ノード番号numのルートノードのset
		public void setVisited(int num) {
			visited[num-1] = true;
		}

		public boolean getVisited(int num) {
			return visited[num-1];
		}

		public void resetVisited() {
			Arrays.fill(visited, false);
		}
	}

	//エッジを表すクラス
	public static class Edge{
		int to;		//行き先
		long weight;		//重み

		public Edge(int to, long weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Edge) {
				Edge e = (Edge)obj;
				if(this.to == e.to)
					return true;
				else
					return false;
			}

			return false;
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.to);
		}
	}

}
