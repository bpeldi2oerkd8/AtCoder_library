package com.bpeldi2oerkd8.lib;

public class BinarySearch {

	//okの条件（ここを問題ごとに変える）(int)
	public static boolean isOK(Integer[] a, int index, int key) {
		if(a[index].intValue() >= key)
			return true;
		else
			return false;
	}

	//okの条件（ここを問題ごとに変える）(long)
	public static boolean isOK(Long[] a, int index, long key) {
		if(a[index].longValue() >= key)
			return true;
		else
			return false;
	}

	//めぐる式二分探索(デフォルトはkey以上を満たす最小のindexを返す)(int)
	public static int binary_search(Integer[] a, int key) {
		int ng = -1;
		int ok = a.length;

		while(Math.abs(ok-ng) > 1) {
			int mid = (ok+ng) / 2;

			if(isOK(a, mid, key))
				ok = mid;
			else
				ng = mid;
		}

		return ok;
	}

	//めぐる式二分探索(デフォルトはkey以上を満たす最小のindexを返す)(long)
	public static int binary_search(Long[] a, long key) {
		int ng = -1;
		int ok = a.length;

		while(Math.abs(ok-ng) > 1) {
			int mid = (ok+ng) / 2;

			if(isOK(a, mid, key))
				ok = mid;
			else
				ng = mid;
		}

		return ok;
	}

}
