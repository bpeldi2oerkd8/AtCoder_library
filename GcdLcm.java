package com.bpeldi2oerkd8.lib;

public class GcdLcm {

	//最大公約数
	public static int gcd(int a, int b) {
		int big = Math.max(a, b);
		int small = Math.min(a, b);

		if(small == 0) {
			return big;
		}

		return gcd(small, big % small);
	}

	//最小公倍数
	public static int lcm(int a, int b) {
		return (a * b) / gcd(a, b);
	}

}
