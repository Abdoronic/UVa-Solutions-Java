import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11496_Musical_Loop {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		while(!false) {
			int n = Integer.parseInt(bf.readLine());
			if(n == 0) break;
			if(n == 1 ) {
				pw.println(1);
				continue;
			}
			int[] arr = new int[n];
			st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < arr.length; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			int count = 	0;
			int pre, curr, nxt;
			pre = arr[n-1];
			curr = arr[0];
			nxt = arr[1];
			if((curr < pre && curr < nxt) || (curr > pre && curr > nxt)) count++;
			for (int i = 1; i < arr.length-1; i++) {
				pre = arr[i-1];
				curr = arr[i];
				nxt = arr[i+1];
				if((curr < pre && curr < nxt) || (curr > pre && curr > nxt)) count++;
			}
			curr = arr[n-1];
			pre = arr[n-2];
			nxt = arr[0];
			if((curr < pre && curr < nxt) || (curr > pre && curr > nxt)) count++;
			pw.println(count);
		}
		pw.close();
	}
}
