package com.bpeldi2oerkd8.lib;

import java.util.Arrays;

public class WarshallFloyd {

	//ワーシャルフロイド法
	public static class WF{
		long[][] d;
		long INF;

		public WF(long[][] d, long INF) {
			this.d = d;
			this.INF = INF;

			//初期化
			for(int i=0; i<d.length; i++) {
				Arrays.fill(d[i], INF);
			}

			//自分自身との距離を0に設定
			for(int i=0; i<d.length; i++) {
				d[i][i] = 0;
			}
		}

		//全頂点間の最短距離(重みに負の値あり)(ワーシャルフロイド法)(O(V^3))
		public long[][] getAllDis(){

			for(int k=0; k<d.length; k++) {
				for(int i=0; i<d.length; i++) {
					for(int j=0; j<d.length; j++) {
						if(d[i][k] != INF && d[k][j] != INF) {
							d[i][j] = Math.min(d[i][j], d[i][k]+d[k][j]);
						}
					}
				}
			}

			return d;
		}

		//負の閉路検出(O(V))
		public boolean isNegativeCycle() {
			for(int i=0; i<d.length; i++) {
				if(d[i][i] < 0) {
					return true;
				}
			}

			return false;
		}
	}

}
