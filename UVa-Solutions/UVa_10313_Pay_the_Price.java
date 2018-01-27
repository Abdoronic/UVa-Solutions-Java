import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UVa_10313_Pay_the_Price {
	
	static int n;
	static int coins[];
	static long arr[];
	static long cumulative[];
	static Long memo[][][];
	static ArrayList<Integer> bounds;
	
	public static long dp(int i, int n, int c) {
		if(n == 0 && c == 0) return 1;
		if(memo[i][n][c] != null) return memo[i][n][c];
		long ans = 0;
		if (n - coins[i] >= 0 && c - 1 >= 0)
			ans += dp(i , n - coins[i], c -1);
		if (i + 1 < n)
			ans += dp(i+1, n, c);
		return memo[i][n][c] = ans;
	}
	
	public static long solve() {
		long ans = 0;
		if(bounds.size() == 0) 
			return ans = (cumulative[n]);
		int L1, L2;
		if(bounds.size() == 1) {
			L2 = bounds.get(0);
			L1 = 1;
			if(L2 > n)
				ans = (cumulative[n]-cumulative[L1-1]);
			else
				ans = (cumulative[L2]-cumulative[L1-1]);
		} else {
			L1 = bounds.get(0);
			L2 = bounds.get(1);
			if(L1 == 0) L1++;
			if(L1 > n)
				ans = (0);
			else 
				if(L2 > n)
					ans = (cumulative[n]-cumulative[L1-1]);
				else
					ans = (cumulative[L2]-cumulative[L1-1]);
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		String line;
		n = 300;
		coins = new int[n];
		for (int i = 1; i <= n; i++)
			coins[i-1] = i;
		
		memo = new Long[n+5][n+5][n+5];
		
		dp(0, 300, 300);
		
		while((line = bf.readLine()) != null && line.length() != 0) {
			st = new StringTokenizer(line);
			n = Integer.parseInt(st.nextToken());
			if(n == 0) {
				bounds = new ArrayList<>();
				while(st.hasMoreTokens())
					bounds.add(Integer.parseInt(st.nextToken()));
				if(bounds.size() == 0 || bounds.size() == 1)
					pw.println(1);
				else
					if(bounds.get(0) > 0)
						pw.println(0);
					else
						pw.println(1);
				continue;
			}
			arr = new long[n];
			cumulative = new long[n+1];
			
			for (int i = 0; i < n; i++)
				arr[i] = dp(0, n, i+1);
			
			for (int i = 0; i < arr.length; i++) 
				cumulative[i+1] = cumulative[i] + arr[i];
			
			bounds = new ArrayList<>();
			while(st.hasMoreTokens())
				bounds.add(Integer.parseInt(st.nextToken()));
			pw.println(solve());
		}
		pw.close();
	}
}
