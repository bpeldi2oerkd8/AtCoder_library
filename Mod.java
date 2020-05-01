package com.bpeldi2oerkd8.lib;

public class Mod {

	//modを指定するパターン
	public static final int MOD = 1000000007;
	public static final int MAX = 510000;

	//負の数に対応したあまり
	public static long mod(long a) {
		long res = a % MOD;
		if(res < 0)
			res += MOD;

		return res;
	}

	//MODの足し算
	public static long add(long a, long b) {
		long res = a + b;
		if(res > MOD)
			res %= MOD;

		return res;
	}

	//MODの引き算
	public static long sub(long a, long b) {
		return mod(a - b);
	}

	//MODの掛け算
	public static long mul(long a, long b) {
		return a * b % MOD;
	}

	//MODの割り算
	public static long div(long a, long b) {
		return a * modinv(b) % MOD;
	}

	//MODの累乗(二分累乗法)
	public static long modpow(long a, long n) {
		long res = 1;
		while (n > 0) {
			if ((n & 1) == 1)
				res = res * a % MOD;

			a = a * a % MOD;
			n >>= 1;
	    }

	    return res;
	}

	//逆元（拡張ユークリッドの互除法）
	public static long modinv(long a) {
		long b = MOD;
		long u = 1;
		long v = 0;

		long tmp;
		while(b != 0) {
			long t = a / b;
			a -= t * b;
			//swap
			tmp = a;
			a = b;
			b = tmp;

			u -= t * v;
			//swap
			tmp = u;
			u = v;
			v = tmp;
		}

		u %= MOD;
		if(u < 0)
			u += MOD;
		return u;
	}

	public static long[] fac = new long[MAX];
	public static long[] finv = new long[MAX];
	public static long[] inv = new long[MAX];

	//MODのnCr計算の前処理(nCrを計算するとき必ず前に入れる)
	public static void ComInit() {
		fac[0] = 1;
		fac[1] = 1;
		finv[0] = 1;
		finv[1] = 1;
		inv[1] = 1;

		for(int i=2; i<MAX; i++) {
			fac[i] = fac[i - 1] * i % MOD;
	        inv[i] = MOD - inv[MOD%i] * (MOD / i) % MOD;
	        finv[i] = finv[i - 1] * inv[i] % MOD;
		}
	}

	//MODのnCr計算
	public static long Com(int n, int r) {
		if(n < r)
			return 0;

		if(n < 0 || r < 0)
			return 0;

		return fac[n] * (finv[r] * finv[n - r] % MOD) % MOD;
	}

	//modが自由なパターン

	//負の数に対応したあまり
	public static long mod(long a, long mod) {
		long res = a % mod;
		if(res < 0)
			res += mod;

		return res;
	}

	//MODの足し算
	public static long add(long a, long b, long mod) {
		long res = a + b;
		if(res > mod)
			res %= mod;

		return res;
	}

	//MODの引き算
	public static long sub(long a, long b, long mod) {
		return mod(a - b, mod);
	}

	//MODの掛け算
	public static long mul(long a, long b, long mod) {
		return a * b % mod;
	}

	//MODの割り算
	public static long div(long a, long b, long mod) {
		return a * modinv(b, mod) % mod;
	}

	//MODの累乗(二分累乗法)
	public static long modpow(long a, long n, long mod) {
		long res = 1;
		while (n > 0) {
			if ((n & 1) == 1)
		        res = res * a % mod;

			a = a * a % mod;
			n >>= 1;
		}

		return res;
	}

	//逆元（拡張ユークリッドの互除法）
	public static long modinv(long a, long mod) {
		long b = mod;
		long u = 1;
		long v = 0;

		long tmp;
		while(b != 0) {
			long t = a / b;

			a -= t * b;
			//swap
			tmp = a;
			a = b;
			b = tmp;

			u -= t * v;
			//swap
			tmp = u;
			u = v;
			v = tmp;
		}

		u %= mod;
		if(u < 0)
			u += mod;
		return u;
	}
}
