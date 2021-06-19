import java.util.*;
import java.io.*;
public class E {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		int n = sc.nextInt();
		int k = sc.nextInt();
		String ss = sc.next();
		char[] s = (ss+ss).toCharArray();
		SuffixArray sa = new SuffixArray(s);
//		System.out.println(Arrays.toString(sa.rank));
		int r = sa.rank[0];
		int max = n;
		for(int i = 1; i < n; i++) {
			if(sa.rank[i] > r) {
				max = i; break;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < k; i++) {
			sb.append(s[i % max]);
		}
		PrintWriter pw = new PrintWriter(System.out);
		pw.println(sb.toString());
		pw.flush();
	}

	static class Pair implements Comparable<Pair>{
		int a, b;
		public Pair(int a, int b) {
			this.a = a; this.b = b;
		}
		@Override
		public int compareTo(Pair p) {
			if(b == p.b) return a - p.a;
			else return b - p.b;
		}
		public String toString() {
			return a+" "+b;
		}
	}
	static int[] sortby(int[] arr, int[] brr) {
		int n = arr.length;
    	Pair[] p = new Pair[n];
    	for(int i = 0; i < n; i++) {
			p[i] = new Pair(i, brr[i]);
		}
    	Arrays.sort(p);
    	int[] aux = new int[n];
    	for(int i = 0; i < n; i++) {
			aux[i] = arr[p[i].a];
		}
    	return aux;
	}
	
	static class SuffixArray{
		
		// ========================================================
		// Suffix Array Construction : Prefix Doubling + Radix Sort
		// ========================================================
		// Complexity: O(N*log(N))
		// references: https://www.cs.helsinki.fi/u/tpkarkka/opetus/10s/spa/lecture11.pdf
		// https://youtu.be/_TUeAdu-U_k
		// adapted from https://github.com/PabloMessina/Competitive-Programming-Material/blob/master/Strings/suffix_array.cpp
		
	    int n; int[] counts, rank, rank_, sa, sa_, lcp; // lcp is optional
	    int gr(int i) { return i < n ? rank[i]: 0; }
	    void csort(int maxv, int k) {
	    	counts = new int[maxv+1];
	    	for(int i = 0; i < n; i++) {
	    		counts[gr(i+k)]++;
			}
	    	for(int i = 1; i < maxv+1; i++) {
	    		counts[i] += counts[i-1];
			}
	    	for(int i = n-1; i >= 0; i--) {
	    		sa_[--counts[gr(sa[i]+k)]] = sa[i];
			}
	    	int[] aux = sa; sa = sa_; sa_ = aux;
	    }
	    void get_sa(int[] s) {
	    	for(int i = 0; i < n; i++) {
	        	sa[i] = i;
	        }
	    	sa = sortby(sa, s);
	        int r = rank[sa[0]] = 1;
	        for(int i = 1; i < n; i++) {
	        	rank[sa[i]] = (s[sa[i]] != s[sa[i-1]]) ? ++r : r;
			}
	        for (int h=1; h < n && r < n; h <<= 1) {
	            csort(r, h); csort(r, 0); r = rank_[sa[0]] = 1;
	            for(int i = 1; i < n; i++) {
	                if (rank[sa[i]] != rank[sa[i-1]] ||
	                    gr(sa[i]+h) != gr(sa[i-1]+h)) ++r;
	                rank_[sa[i]] = r;
				}
	            int[] aux = rank; rank = rank_; rank_ = aux;
	        }
	    }
	    void get_lcp(int[] s) { // lcp is optional
	    	lcp = new int[n];
	    	int k = 0;
	    	for(int i = 0; i < n; i++) {
	            int r = rank[i]-1;
	            if (r == n-1) { k = 0; continue; }
	            int j = sa[r+1];
	            while (i+k<n && j+k<n && s[i+k] == s[j+k]) k++;
	            lcp[r] = k;
	            if(k > 0) k--;
			}
	    }
	    SuffixArray(int[] s) {
	        n = s.length;
	        rank = new int[n];
	        rank_ = new int[n];
	        sa = new int[n];
	        sa_ = new int[n];
	        get_sa(s); //get_lcp(s); // lcp is optional
	    }
	    static int[] convert(char[] s) {
	    	int[] res = new int[s.length];
	    	for(int i = 0; i < s.length; i++) {
				res[i] = s[i];
			}
	    	return res;
	    }
	    SuffixArray(char[] s) {
	    	this(convert(s));
	    }
	}
	
	static int[] suffix_array_faster(char[] s) {//n log n
		int n = s.length;
		int[] rank = new int[n];
		for(int i = 0; i < n; i++) rank[i] = s[i]-'a';
		for(int d = 1; d < n; d <<= 1) {
			int m = Math.max(26, n);
			int[] count = new int[m];
			int[] temp = new int[m];
			for(int i = 0; i < n; i++) {
				int r = i+d < n ? rank[i+d]+1 : 0;
				count[r]++;
			}
			for(int i = 1; i < m; i++) {
				count[i] += count[i-1];
			}
			for(int i = 0; i < n; i++) {
				int r = i+d < n ? rank[i+d]+1 : 0;
				temp[count[r]] = i;
			}
//			for(int i = 0; )
		}
		return rank;
	}
	
	
	
	static class FastScanner {
		public BufferedReader reader;
		public StringTokenizer tokenizer;
		public FastScanner() {
			reader = new BufferedReader(new InputStreamReader(System.in), 32768);
			tokenizer = null;
		}
		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}
		public int nextInt() {
			return Integer.parseInt(next());
		}
		public long nextLong() {
			return Long.parseLong(next());
		}
		public double nextDouble() {
			return Double.parseDouble(next());
		}
		public String nextLine() {
			try {
				return reader.readLine();
			} catch(IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
