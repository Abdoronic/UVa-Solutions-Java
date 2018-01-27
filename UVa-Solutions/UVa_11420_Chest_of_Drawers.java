import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11420_Chest_of_Drawers {
	
	static int n;
	static int s;
	static Long memo[][][];
	
	public static long dp(int i, int s, int pre) {
		if(i < 0 || s < 0) return 0;
		if(i == 0 && s == 0) return 1;
		if(memo[i][s][pre] != null) return memo[i][s][pre];
		long ans = 0;
		if(pre == 1) {
			ans += dp(i-1, s-1, 1);
			ans += dp(i-1, s, 0);
		} else {
			ans += dp(i-1, s, 1);
			ans += dp(i-1, s, 0);
		}
		return memo[i][s][pre] = ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(bf.readLine());
			n = Integer.parseInt(st.nextToken());
			if(n < 0) break;
			s = Integer.parseInt(st.nextToken());
			memo = new Long[n+5][s+5][5];
			pw.println(dp(n, s, 1));
		}
		pw.close();
	}
}
