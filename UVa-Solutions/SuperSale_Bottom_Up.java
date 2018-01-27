import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SuperSale_Bottom_Up {
	
	static int[] weight;
	static int[] price;
	static int[][] memo;
	static int inf = (int)1e9;
	static int n;
	
	public static void dpB(int x) {
		for (int i = n-1; i >= 0; i--) {
			for (int j = 1; j <= x; j++) {
				if(j - weight[i] >= 0)
					memo[i][j] = Math.max(memo[i][j], price[i] + memo[i+1][j - weight[i]]);
				memo[i][j] = Math.max(memo[i][j], memo[i+1][j]);
			}
		}
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
			memo = new int[n+1][31];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(bf.readLine());
				price[i] = Integer.parseInt(st.nextToken());
				weight[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < n+1; i++)
				memo[i][0] = 0;
			for (int i = 0; i < 31; i++)
				memo[n][i] = 0;
			int g = Integer.parseInt(bf.readLine());
			int max = 0;
			dpB(30);
			while(g-->0) {
				int x = Integer.parseInt(bf.readLine());
				max += memo[0][x];
			}
			pw.println(max);
		}
		pw.close();
	}
}
