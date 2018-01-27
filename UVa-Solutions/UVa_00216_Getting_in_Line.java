import java.io.*;
import java.util.*;

public class UVa_00216_Getting_in_Line {
	
	static int n;
	static int[] arrx;
	static int[] arry;
	static Double memo[][];
	static PrintWriter pw = new PrintWriter(System.out);
	
	public static boolean vis(int mask, int idx) { return (mask & (1 << idx)) != 0; }
	
	public static int set(int mask, int idx) { return mask |= (1 << idx); }
	
	public static double dist(int x1, int y1, int x2, int y2) { return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)); }
	
	public static double dp(int i, int mask) {
		if(Integer.bitCount(mask) == n-1)
			return 0;
		if(memo[i][mask] != null) return memo[i][mask];
		double ans = 1e9;
		for (int j = 0; j < n; j++) {
			if(!vis(mask, j) && j != i) {
				ans = Math.min(ans, dist(arrx[i], arry[i], arrx[j], arry[j]) + 16 + dp(j, set(mask, i)));
			}
		}
		return memo[i][mask] = ans;
	}
	
	public static void print(int i, int mask) {
		if(Integer.bitCount(mask) == n-1)
			return;
		double ans = dp(i, mask);
		for (int j = 0; j < n; j++) {
			if(!vis(mask, j) && Math.abs(ans - (dist(arrx[i], arry[i], arrx[j], arry[j]) + 16 + dp(j, set(mask, i)))) < 1e-9) {
				pw.printf("Cable requirement to connect (%d,%d) to (%d,%d) is %.2f feet.\n", 
						arrx[i], arry[i], arrx[j], arry[j], 16 + dist(arrx[i], arry[i], arrx[j], arry[j]));
				print(j, set(mask, i));
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner("/Users/abdulrahmanibrahim/io/input.in");
		for(int t = 1; true ; t++) {
			n = sc.nextInt();
			if(n == 0) break;
			arrx = new int[n];
			arry = new int[n];
			for (int i = 0; i < n; i++) {
				arrx[i] = sc.nextInt();
				arry[i] = sc.nextInt();
			}
			int end = (int)Math.pow(2, n)-1;
			memo = new Double[n+5][end+5];
			double ans = 1e9;
			int start = 0;
			for (int i = 0; i < n; i++)
				if(ans > dp(i, 0)) {
					ans = dp(i, 0);
					start = i;
				}
			pw.println("**********************************************************");
			pw.println("Network #" + t);
			print(start, 0);
			pw.printf("Number of feet of cable required is %.2f.\n", ans);
		}
		pw.close();
	}

	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s) {
			br = new BufferedReader(new InputStreamReader(s));
		}

		public Scanner(String file) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(file));
		}

		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public String nextLine() throws IOException {
			return br.readLine();
		}

		public double nextDouble() throws IOException {
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if (x.charAt(0) == '-') {
				neg = true;
				start++;
			}
			for (int i = start; i < x.length(); i++)
				if (x.charAt(i) == '.') {
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				} else {
					sb.append(x.charAt(i));
					if (dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg ? -1 : 1);
		}

		public boolean ready() throws IOException {
			return br.ready();
		}

	}
}
