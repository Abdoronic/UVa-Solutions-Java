import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11137_Ingenuous_Cubrency {
	
	static int n;
	static int[] arr;
	static long[][] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		String line;
		arr = new int[21];
		for (int i = 0; i < arr.length; i++)
			arr[i] = (int)Math.pow(i+1, 3);
		memo = new long[22][10000];
		for (int i = 0; i < 10000; i++) 
			memo[21][i] = 0;
		for (int i = 0; i < 22; i++) 
			memo[i][0] = 1;
		for (int i = 20; i >= 0; i--) {
			for (int j = 1; j < 10000; j++) {
				if(j - arr[i] >= 0)
					memo[i][j] += memo[i][j-arr[i]];
				memo[i][j] += memo[i+1][j];
			}
		}
		while((line = bf.readLine()) != null && line.length() != 0) {
			StringTokenizer st = new StringTokenizer(line);
			if(st.hasMoreTokens()) {
				int n = Integer.parseInt(st.nextToken());
				pw.println(memo[0][n]);
			}
		}
		pw.close();
	}
}
