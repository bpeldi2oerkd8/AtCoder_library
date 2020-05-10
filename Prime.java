package com.bpeldi2oerkd8.lib;

import java.util.Arrays;

public class Prime {

	//高速な素数判定
	public static boolean isPrime(int n) {
		if(n < 2)
			return false;
		if(n == 2)
			return true;
		if(n % 2 == 0)
			return false;

		for(int i=2; i<=Math.sqrt(n); i++) {
			if(n % i == 0)
				return false;
		}

		return true;
	}

	//最小の約数を返す(素因数分解用)
	public static int fact(int n){
		if(n < 2)
			return -1;

		for(int i=2; i<=Math.sqrt(n); i++) {
			if(n % i == 0)
				return i;
		}

		return n;
	}

	//n以下の素数の個数(エラトステネスの篩)
	public static int primeN(int n) {
		if(n < 2)
			return 0;

		boolean[] isPrime = new boolean[n+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;

		int sqrt = (int)Math.sqrt(n);

		//割れる数はsqrt以下
		for(int i=2; i<=sqrt; i++) {
			if(isPrime[i]) {
				for(int j=i*i; j<=n; j+=i) {
					isPrime[j] = false;
				}
			}
		}

		int count = 0;
		for(int i=2; i<=n; i++) {
			if(isPrime[i])
				count++;
		}

		return count;
	}

	//n以下のすべての数の高速な素数判定(エラトステネスの篩)
	public static boolean[] isPrimeN(int n) {

		boolean[] isPrime = new boolean[n+1];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;

		int sqrt = (int)Math.sqrt(n);

		//割れる数はsqrt以下
		for(int i=2; i<=sqrt; i++) {
			if(isPrime[i]) {
				for(int j=i*i; j<=n; j+=i) {
					isPrime[j] = false;
				}
			}
		}

		return isPrime;
	}

}
