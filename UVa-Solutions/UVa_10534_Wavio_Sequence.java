import java.io.*;
import java.util.*;

public class UVa_10534_Wavio_Sequence {
	
	static int n;
	static int[] arr1;
	static int[] arr2;
	static int[] memo1;
	static int[] memo2;
	
	public static int[] lis(int[] arr) {
		int[] res = new int[arr.length];
		ArrayList<Integer> lis = new ArrayList<>(arr.length);
		for (int i = 0; i < arr.length; i++) {
			int x = Collections.binarySearch(lis, arr[i]);
			if(x < 0) {
				x++;
				x*=-1;
			}
			if (x >= lis.size()) 
				lis.add(arr[i]);
			else
				lis.set(x, arr[i]);
			res[i] = x + 1;
		}
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(System.out);
		while(sc.ready()) {
			n = sc.nextInt();
			arr1 = new int[n];
			arr2 = new int[n];
			for (int i = 0; i < n; i++) {
				int x = sc.nextInt();
				arr1[i] = x;
				arr2[n-1-i] = x;
			}
			memo1 = lis(arr1);
			memo2 = lis(arr2);
			int[] res = new int[n];
			int max = 0;
			for (int i = 0; i < n; i++) {
				res[i] = Math.min(memo1[i], memo2[n-1-i])*2 - 1;
				max = Math.max(max, res[i]);
			}
			pw.println(max);
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
