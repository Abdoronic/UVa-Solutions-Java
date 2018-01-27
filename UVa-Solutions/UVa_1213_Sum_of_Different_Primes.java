import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_1213_Sum_of_Different_Primes {
	
	static ArrayList<Integer> arr = new ArrayList<>(1000);
	static Integer[][][] memo = new Integer[1125][20][1000];
	
	public static int dp(int n, int k, int i) {
		if(n == 0 && k == 0) return 1;
		if(memo[n][k][i] != null) return memo[n][k][i];
		int ans = 0;
		if(n-arr.get(i) >= 0 && k-1 >= 0 && i+1 < arr.size())
			ans += dp(n-arr.get(i), k-1, i+1);
		if(i+1 < arr.size())
			ans += dp(n, k, i+1);
		return memo[n][k][i] = ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		boolean[] isPrime = new boolean[1125];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		for (int i = 2; i < isPrime.length; i++)
			if(isPrime[i]) {
				arr.add(i);
				for (int j = i; j*i < isPrime.length; j++)
					isPrime[j*i] = false;
			}
		while(!false) {
			st = new StringTokenizer(bf.readLine());
			int n = Integer.parseInt(st.nextToken());
			if(n == 0) break;
			int k = Integer.parseInt(st.nextToken());
			pw.println(dp(n, k, 0));
		}
		pw.close();
	}
}
