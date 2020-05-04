package com.bpeldi2oerkd8.lib;

public class BinarySearch {

	//okの条件（ここを問題ごとに変える）(int)
	public static boolean isOK(Integer[] a, int index, int key) {
		if(a[index] >= key)
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
	public static int binary_search(Integer[] a, int key, int ng, int ok) {
//		int ng = -1;
//		int ok = a.length;
		if(ng < ok)
			ng--;
		else
			ok--;

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
	public static int binary_search(Long[] a, long key, int ng, int ok) {
//		int ng = -1;
//		int ok = a.length;
		if(ng < ok)
			ng--;
		else
			ok--;

		while(Math.abs(ok-ng) > 1) {
			int mid = (ok+ng) / 2;

			if(isOK(a, mid, key))
				ok = mid;
			else
				ng = mid;
		}

		return ok;
	}

	//keyが含まれているか(int)
	public static boolean containsValue(Integer[] a, int key) {
		int index = binary_search(a, key, 0, a.length);

		if(index < 0 || index >= a.length || a[index] != key)
			return false;
		else
			return true;
	}

	//keyが含まれている個数(int)
	public static int countValue(Integer[] a, int key) {
		int index1 = binary_search(a, key, 0, a.length);
		int index2 = binary_search(a, key+1, 0, a.length);

		if(index1 < 0 || index1 >= a.length || a[index1] != key)
			return 0;

		return index2 - index1;
	}

	//keyが含まれているか(long)
	public static boolean containsValue(Long[] a, long key) {
		int index = binary_search(a, key, 0, a.length);

		if(index < 0 || index >= a.length || a[index] != key)
			return false;
		else
			return true;
	}

	//keyが含まれている個数(long)
	public static int countValue(Long[] a, long key) {
		int index1 = binary_search(a, key, 0, a.length);
		int index2 = binary_search(a, key+1, 0, a.length);

		if(index1 < 0 || index1 >= a.length || a[index1] != key)
			return 0;

		return index2 - index1;
	}


}
