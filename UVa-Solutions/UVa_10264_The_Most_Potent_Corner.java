import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class UVa_10264_The_Most_Potent_Corner {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		String line;
		while((line = bf.readLine()) != null && line.length() != 0) {
			int n = Integer.parseInt(line);
			int corners = (int)Math.pow(2, n);
			int[] arr = new int[corners];
			int[] pot = new int[corners];
			for (int i = 0; i < arr.length; i++)
				arr[i] = Integer.parseInt(bf.readLine());
			for (int i = 0; i < arr.length; i++)
				for (int j = i+1; j < arr.length; j++)
					if(Integer.bitCount((i^j)) == 1) {
						pot[i] += arr[j];
						pot[j] += arr[i];
					}
			int max = 0;
			for (int i = 0; i < arr.length; i++)
				for (int j = i+1; j < arr.length; j++)
					if(Integer.bitCount((i^j)) == 1)
						max = Math.max(max, pot[i] + pot[j]);
			pw.println(max);
		}
		pw.close();
	}
}
