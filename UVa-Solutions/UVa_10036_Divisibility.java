import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10036_Divisibility {
	
	static int n;
	static int k;
	static int arr[];
	static Boolean[][] memo;
	
	public static boolean dp(int i, int s) {
		if(i == n)
			if(s == 0)
				return true;
			else
				return false;
		if(memo[i][s] != null) return memo[i][s];
		boolean ans = true;
		ans = dp(i + 1, Math.abs(s - arr[i])%k) || dp(i + 1, Math.abs(s + arr[i])%k);
		return memo[i][s] = ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		int t = Integer.parseInt(bf.readLine());
		while(t-->0) {
			st = new StringTokenizer(bf.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			arr = new int[n];
			st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < n; i++) 
				arr[i] = Integer.parseInt(st.nextToken());
			memo = new Boolean[n+5][1000];
			boolean can = dp(1, Math.abs(arr[0])%k );
			if(can)
				pw.println("Divisible");
			else
				pw.println("Not divisible");
		}
		pw.close();
	}
}
