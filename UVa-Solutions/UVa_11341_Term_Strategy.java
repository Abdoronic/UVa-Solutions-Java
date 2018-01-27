import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11341_Term_Strategy {
	
	static int n;
	static int m;
	static int[][] arr;
	static Integer[][] memo;
	static int inf = (int)1e9;
	
	public static int dp(int i, int m) {
		if(i == n) return 0;
		if(memo[i][m] != null) return memo[i][m];
		int ans = -inf;
		for (int j = 0; j < arr[i].length; j++) {
			if(m - (j+1) >= 0 && arr[i][j] >= 5)
				ans = Math.max(ans, arr[i][j] + dp(i+1, m - (j+1)));
		}
		return memo[i][m] = ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		int t = Integer.parseInt(bf.readLine());
		while(t-->0 ) {
			st = new StringTokenizer(bf.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			arr = new int[n][m];
			for (int i = 0; i < arr.length; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < arr[i].length; j++)
					arr[i][j] = Integer.parseInt(st.nextToken());
			}
			memo = new Integer[n+5][m+5];
			int max = dp(0, m);
			if(max < 0)
				pw.printf("Peter, you shouldn't have played billiard that much.\n");
			else
				pw.printf("Maximal possible average mark - %.2f.\n", (double) max / n);
		}
		pw.close();
	}
}
