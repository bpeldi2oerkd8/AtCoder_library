package com.bpeldi2oerkd8.lib;

import java.util.Arrays;

public class NextPermutation {

	//階乗
	public static int factorial(int n) {
		if(n == 0)
			return 1;

		return n * factorial(n-1);
	}

	//次の順列を生成する
	public static boolean next_permutation(int[] array, int start, int end) {

		if(array == null || start > end || start < 0 || end > array.length) {
			System.out.println("Error: 引数が正しくありません。");
			return false;
		}

		for(int i=end-2; i>=start; i--) {
			if(array[i] < array[i+1]) {
				int j = end - 1;
				while(array[i] >= array[j]) {
					j--;
				}

				//swap
				int tmp = array[j];
				array[j] = array[i];
				array[i] = tmp;

				Arrays.sort(array, i+1, end);

				return true;
			}
		}

		return false;
	}
}
