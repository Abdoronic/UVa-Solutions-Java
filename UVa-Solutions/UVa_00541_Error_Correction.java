import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_00541_Error_Correction {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		while(!false) {
			int n = Integer.parseInt(bf.readLine());
			if(n == 0) break;
			int[][] arr = new int[n+1][n+1];
			int sum = 0;
			int oddRow = 0;
			int oddCol = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(bf.readLine());
				sum =0;
				for (int j = 0; j < n+1; j++) {
					if(j != n) {
						arr[i][j] = Integer.parseInt(st.nextToken());
						sum += arr[i][j];
					} else 
						arr[i][j] = sum;
				}
			}
			for (int j = 0; j < n; j++) {
				sum =0;
				for (int i = 0; i < n+1; i++) {
					if(i != n)
						sum += arr[i][j];
					else
						arr[i][j] = sum;
				}
			}
			int oddRows = 0;
			int oddCols = 0;
			for (int i = 0; i < n; i++)
				if(arr[i][n] % 2 != 0) {
					oddRows++;
					oddRow = i;
				}
			for (int j = 0; j < n; j++)
				if(arr[n][j] % 2 != 0) {
					oddCols++;
					oddCol = j;
				}
			if(oddRows == 0 && oddCols == 0) 
				pw.println("OK");
			else
				if(oddRows == 1 && oddCols == 1)
					pw.println("Change bit (" + ++oddRow + "," + ++oddCol +")");
				else
					pw.println("Corrupt");
				
		}
		pw.close();
	}
}
