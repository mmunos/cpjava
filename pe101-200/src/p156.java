
public class p156 {

	public static void main(String[] args) {
		int[] count = new int[10];
		long n = 20000000000L;
		long[] res = new long[10];
		for(long i = 0; i <= n; i++) {
			long aux = i;
			while(aux > 0) {
				count[(int)(aux % 10L)]++;
				aux /= 10;
			}
			for(int d = 1; d <= 9; d++) {
				if(count[d] == i) {
					System.out.println(d+" "+i);
					res[d] += i;
				}
			}
		}
		System.out.println("SOLS");
		long ress = 0;
		for(int d = 1; d <= 9; d++) {
			System.out.println(d+" "+res[d]);
			ress += res[d];
		}
		System.out.println(ress);
	}

}
