import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10667_Largest_Block {
	
	static int[][] arr;
	static int[][] c;
	static int n;
	static int inf = (int)1e5;
	
	public static void fill(String s) {
		StringTokenizer st = new StringTokenizer(s);
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		for (int i = r1-1; i < r2; i++)
			for (int j = c1 -1 ; j < c2; j++)
				arr[i][j] = -inf;
	}
	
	public static int calc(int i1, int i2, int j1, int j2) {
		return c[i2][j2] - c[i1-1][j2] - c[i2][j1 - 1] + c[i1-1][j1-1];
	}
	
	//not accepted
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int t = Integer.parseInt(bf.readLine());
		while(t-->0) {
			n = Integer.parseInt(bf.readLine());
			arr = new int[n][n];
			for (int i = 0; i < arr.length; i++)
				Arrays.fill(arr[i], 1);
			int m = Integer.parseInt(bf.readLine());
			while(m-->0) 
				fill(bf.readLine());
			c = new int[n+1][n+1];
			for (int i = 1; i < c.length; i++)
				for (int j = 1; j < c[i].length; j++)
					c[i][j] = c[i-1][j] + c[i][j-1] - c[i-1][j-1] + arr[i-1][j-1];
			int max = 0;
			for (int i1 = 1; i1 < c.length; i1++)
				for (int j1 = 1; j1 < c[i1].length; j1++)
					for (int i2 = i1; i2 < c.length; i2++)
						for (int j2 = j1; j2 < c[i2].length; j2++) {
							max = Math.max(max, calc(i1, i2, j1, j2));
						}
			pw.println(max);
		}
		pw.close();
	}
}
