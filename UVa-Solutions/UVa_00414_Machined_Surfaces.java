import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_00414_Machined_Surfaces {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		while(!false) {
			int n = Integer.parseInt(bf.readLine());
			if(n == 0) break;
			int[] arr = new int[n];
			int max = 0;
			for (int i = 0; i < arr.length; i++) {
				st = new StringTokenizer(bf.readLine());
				arr[i] = st.nextToken().length();
				if(st.hasMoreTokens())
					arr[i] += st.nextToken().length();
				max = Math.max(max, arr[i]);
			}
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				sum += (max-arr[i]);
			}
			pw.println(sum);
		}
		pw.close();
	}
}
