import java.io.*;
import java.util.*;

public class UVa_11284_Shopping_Trip {
	
	static int n;
	static int m;
	static int p;
	static double inf = 1e9;
	static ArrayList<Pair> arr;
	static ArrayList<Pair>[] adj;
	static double[][] path;
	static Double[][] memo;
	
	static boolean vis(int mask, int i) { return (mask & (1 << i)) != 0; }
	
	static int set(int mask, int i) { return mask |= (1 << i); }
	
	static double getPath(int i, int j) {
		PriorityQueue<Pair> q = new PriorityQueue<>();
		boolean[] vis = new boolean[n+1];
		q.add(new Pair(0, i));
		while(!q.isEmpty()) {
			Pair p = q.poll();
			int idx = p.y;
			double val = p.x;
			if(idx == j) return p.x;
			if(!vis[idx]) {
				vis[idx] = true;
				for (int k = 0; k < adj[p.y].size(); k++) {
					Pair x = adj[idx].get(k);
					q.add(new Pair(x.x + val, x.y));
				}
			}
		}
		return -1;
	}
	
	static void fillPath() {
		for (int i = 0; i < arr.size(); i++)
			for (int j = i+1; j < arr.size(); j++) {
				int n1 = arr.get(i).y; int n2 = arr.get(j).y;
				double dist = getPath(n1, n2);
				path[n1][n2] = dist;
				path[n2][n1] = dist;
			}
		for (int i = 0; i < arr.size(); i++) {
			int n1 = arr.get(i).y; int n2 = 0;
			double dist = getPath(n1, n2);
			path[n1][n2] = dist;
			path[n2][n1] = dist;
		}
	}
	
	public static double dp(int i, int mask) {
		if(Integer.bitCount(mask) == p)
			return -path[i][0];
		if(memo[i][mask] != null) return memo[i][mask];
		double ans = -inf;
		for (int j = 0; j < p; j++) {
			if(!vis(mask, j)) {
				int idx = arr.get(j).y;
				double val = arr.get(j).x;
				ans = Math.max(ans, val - path[i][idx] + dp(idx, set(mask, j)));
			}
		}
		if(Integer.bitCount(mask) >= 1)
			ans = Math.max(ans, -path[i][0]);
		return memo[i][mask] = ans;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner("/Users/abdulrahmanibrahim/io/input.in");
		PrintWriter pw = new PrintWriter(System.out);
		int t = sc.nextInt();
		while(t-->0) {
			n = sc.nextInt();
			m = sc.nextInt();
			adj = new ArrayList[n+1];
			path = new double[n+1][n+1];
			for (int i = 0; i <  n+1; i++)
				adj[i] = new ArrayList<Pair>(n);
			while(m-->0) {
				int i = sc.nextInt();
				int j = sc.nextInt();
				double x = sc.nextDouble();
				adj[i].add(new Pair(x, j));
				adj[j].add(new Pair(x, i));
			}
			p = sc.nextInt();
			int end = (int)Math.pow(2, p);
			memo = new Double[n+5][end+5];
			arr = new ArrayList<>(p);
			for (int i = 0; i < p; i++) {
				int y = sc.nextInt();
				arr.add(new Pair(sc.nextDouble(), y));
			}
			fillPath();
			double ans = dp(0, 0);
			if(ans < 0 || Math.abs(ans) < 1e-9)
				pw.printf("Don't leave the house\n");
			else
				pw.printf("Daniel can save $%.2f\n", ans);
		}
		pw.close();
	}
	
	static class Pair implements Comparable<Pair> {
		int y;
		double x;
		public Pair(double x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			Pair p = (Pair) obj;
			return this.x == p.x && this.y == p.y;
		}

		@Override
		public int compareTo(Pair o) {
			return Double.compare(this.x, o.x);
		}

		@Override
		public String toString() {
			return "(" + x + " : " + y + ")";
		}
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
