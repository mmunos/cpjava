import java.util.*;
import java.io.*;
public class A {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		while(T-->0) {
			int n = sc.nextInt();
			int[] conts = new int[101];
			for(int i = 0; i < n; i++) {
				conts[sc.nextInt()]++;
			}	
			for(int i = 0; i <= 100; i++) {
				if(conts[i] > 0) {
					conts[i]--;
					sb.append(i+" ");
				}
				else {
					break;
				}
			}
			for(int i = 0; i <= 100; i++) {
				for(int r = 0; r < conts[i]; r++) {
					sb.append(i+" ");
				}
			}
			sb.replace(sb.length()-1, sb.length(), "\n");
		}
		PrintWriter pw = new PrintWriter(System.out);
		pw.println(sb.toString().trim());
		pw.flush();
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
