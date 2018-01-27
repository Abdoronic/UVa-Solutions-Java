import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SuperSale_MemoryReduction {
	
	static int[] weight;
	static int[] price;
	static int[][] memo;
	static int inf = (int)1e9;
	static int n;
	
	public static int dpB(int x) {
		int r = 0;
		for (int i = n-1; i >= 0; i--) {
			r = 1 - r;
			for (int j = 1; j <= x; j++) {
				if(j - weight[i] >= 0)
					memo[r][j] = Math.max(memo[r][j], price[i] + memo[1-r][j - weight[i]]);
				memo[r][j] = Math.max(memo[r][j], memo[1-r][j]);
			}
		}
		return memo[r][x];
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
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(bf.readLine());
				price[i] = Integer.parseInt(st.nextToken());
				weight[i] = Integer.parseInt(st.nextToken());
			}
			int g = Integer.parseInt(bf.readLine());
			int max = 0;
			while(g-->0) {
				int x = Integer.parseInt(bf.readLine());
				memo = new int[2][31];
				for (int i = 0; i < 31; i++)
					memo[0][i] = 0;
				max += dpB(x);
			}
			pw.println(max);
		}
		pw.close();
	}
}
