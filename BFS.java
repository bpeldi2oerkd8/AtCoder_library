package com.bpeldi2oerkd8.lib;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

public class BFS {

	//座標版
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		//読み込み

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

		//距離
		int[][] distance = new int[H][W];

		//visitedか
		boolean[][] visited = new boolean[H][W];
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				visited[i][j] = false;
			}
		}

		//動ける方向
		List<Point> direction = new ArrayList<>();
		Point down = new Point(-1, 0);
		Point left = new Point(0, -1);
		Point up = new Point(1, 0);
		Point right = new Point(0, 1);

		Queue<Point> queue = new ArrayDeque<>();
		queue.add(スタート地点);
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			int h = p.x;
			int w = p.y;

			if(終了条件) {

				break;
			}

			//訪問済み
			visited[h][w] = true;

			direction.clear();
			if(h-1 >= 0)
				direction.add(down);
			if(w-1 >= 0)
				direction.add(left);
			if(h+1 < H)
				direction.add(up);
			if(w+1 < W)
				direction.add(right);

			for(Point dir : direction) {
				int n_h = h + dir.x;
				int n_w = w + dir.y;

				Point n_p = new Point(n_h, n_w);
				if(!queue.contains(n_p) && !visited[n_h][n_w] && 壁でない) {
					queue.add(n_p);
					distance[n_h][n_w] = distance[h][w] + 1;
				}
			}
		}

		//答え表示

	}
}
