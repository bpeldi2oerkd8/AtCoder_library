package com.bpeldi2oerkd8.lib;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//座標
class Point{
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Point) {
			Point p = (Point)obj;

			if(this.x == p.x && this.y == p.y)
				return true;
			else
				return false;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

}

public class GraphTools {

	//一般的なグラフ
	public static class Graph{
		int n;
		Map<Integer, Node> nodes;	//(ノード番号, ノード)

		//1~Nまでをもつグラフ
		public Graph(int n) {
			this.n = n;
			nodes = new HashMap<>(n);

			for(int i=0; i<n; i++) {
				nodes.put(i+1, new Node());
			}
		}

		public Graph(int n, int[] values) {
			this.n = n;
			nodes = new HashMap<>(n);

			for(int i=0; i<n; i++) {
				nodes.put(values[i], new Node(values[i]));
			}
		}

		public Node getNode(int node_n) {
			//ノード番号を指定してノードを取得
			return nodes.get(node_n);
		}

		public void addEdge(Node from, Node to) {
			//重みが1のグラフの場合(重みが均等な場合にも場合によっては使える)
			from.edges.add(new Edge(to, 1));
		}

		public void addEdge(Node from, Node to, int weight) {
			//エッジの重みが異なる場合
			from.edges.add(new Edge(to, weight));
		}

		public void removeEdge(Node from, Node to) {
			//指定したノード間のエッジを削除
			from.edges.remove(new Edge(to, 1));
		}
	}

	public static int count = 0;
	//ノードを表すクラス
	public static class Node{

		int value;
		List<Edge> edges;
		boolean visited = false;
		Node root;

		public Node(){
			value = count+1;
			edges = new LinkedList<Edge>();
			count++;
		}

		public Node(int value) {
			this.value = value;
			edges = new LinkedList<Edge>();
		}

		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Node) {
				Node n = (Node)obj;
				if(this.value == n.value)
					return true;
				else
					return false;
			}

			return false;
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.value);
		}
	}

	//エッジを表すクラス
	public static class Edge{
		Node to;		//行き先
		int weight;		//重み

		public Edge(Node to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Edge) {
				Edge e = (Edge)obj;
				if(this.to.equals(e.to))
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
