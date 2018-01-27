import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_00990_Diving_for_gold {
	
	static int t;
	static int w;
	static int n;
	static int[] depth;
	static int[] value;
	static Pair[][] memo;
	static PrintWriter pw = new PrintWriter(System.out);
	
	public static Pair dp(int i, int t) {
		if(i == n)
			return new Pair(0, 0);
		if(memo[i][t] != null) return memo[i][t];
		Pair take = new Pair(0, 0);
		Pair leave = new Pair(0, 0);
		int time = 3*w*depth[i];
		if(t - time >= 0)
			take = dp(i+1, t - time).add(new Pair(value[i], 1));
		leave = dp(i+1, t);
		int comp = take.compareTo(leave);
		if(comp >= 0)
			return memo[i][t] = take;
		return memo[i][t] = leave;
		
	}
	
	public static void print(int i, int t) {
		if(i == n)
			return;
		Pair take = new Pair(0, 0);
		int time = 3*w*depth[i];
		if(t - time >= 0)
			take = dp(i+1, t - time).add(new Pair(value[i], 1));
		Pair ans = dp(i, t);
		if(ans.equals(take) && t - time >= 0) {
			pw.println(depth[i] + " " + value[i]);
			print(i+1, t - time);
		} else {
			print(i+1, t);
		}
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String line;
		int count = 0;
		while((line = bf.readLine()) != null && line.length() != 0) {
			if(count != 0) {
				pw.println();
			} else {
				count++;
			}
			st = new StringTokenizer(line);
			t = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(bf.readLine());
			depth = new int[n];
			value = new int[n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(bf.readLine());
				depth[i] = Integer.parseInt(st.nextToken());
				value[i] = Integer.parseInt(st.nextToken());
			}
			bf.readLine();
			memo = new Pair[n+1][t+5];
			Pair ans = dp(0, t);
			pw.println(ans.x);
			pw.println(ans.y);
			print(0, t);
		}
		pw.close();
	}
	
	static class Pair implements Comparable<Pair> {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public  Pair add( Pair a) {
			return new Pair(this.x + a.x, this.y + a.y);
		}
		
		@Override
		public int hashCode() {
			StringBuilder sb = new StringBuilder(x).append("#").append(y);
			return sb.toString().hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			Pair p = (Pair) obj;
			return this.x == p.x && this.y == p.y;
		}

		@Override
		public int compareTo(Pair o) {
			return this.x - o.x;
		}

		@Override
		public String toString() {
			return "(" + x + " : " + y + ")";
		}
	}

}
