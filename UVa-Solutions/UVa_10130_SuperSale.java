import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10130_SuperSale {
	
	static int[] weight;
	static int[] price;
	static Integer[][] memo;
	static int inf = (int)1e9;
	static int n;
	
	public static int dp(int i, int m) {
		if(i == n) return 0;
		if(memo[i][m] != null) return memo[i][m];
		int ans = 0;
		if(m - weight[i] >= 0)
			ans = Math.max(ans, price[i] + dp(i+1, m - weight[i]));
		ans = Math.max(ans,dp(i+1, m));
		return memo[i][m] = ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		int t = Integer.parseInt(bf.readLine());
		while(t-->0) {
			n = Integer.parseInt(bf.readLine());
			weight = new int[n];
			price = new int[n];
			memo = new Integer[1005][35];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(bf.readLine());
				price[i] = Integer.parseInt(st.nextToken());
				weight[i] = Integer.parseInt(st.nextToken());
			}
			int g = Integer.parseInt(bf.readLine());
			int max = 0;
			while(g-->0) {
				int x = Integer.parseInt(bf.readLine());
				max += dp(0, x);
			}
			pw.println(max);
		}
		pw.close();
	}
}
