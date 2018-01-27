import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class UVa_10405_Longest_Common_Subsequence {
	
	static char[] s1;
	static char[] s2;
	static int[][] memo;
	static int n1;
	static int n2;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		String line;
		while((line = bf.readLine()) != null && line.length() != 0) {
			s1 = line.toCharArray();
			s2 = bf.readLine().toCharArray();
			n1 = s1.length;
			n2 = s2.length;
			memo = new int[n1+1][n2+1];
			for (int i = n1-1; i >= 0; i--) {
				for (int j = n2-1; j >= 0; j--) {
					if(s1[i] == s2[j])
						memo[i][j] = Math.max(memo[i][j], 1 + memo[i + 1][j + 1]);
					memo[i][j] = Math.max(memo[i][j], memo[i + 1][j]);
					memo[i][j] = Math.max(memo[i][j], memo[i][j + 1]);
				}
			}
			pw.println(memo[0][0]);
		}
		pw.close();
	}
}
