import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UVa_11933_Splitting_Numbers {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		while(!false) {
			int n = Integer.parseInt(bf.readLine());
			if(n ==0) break;
			char[] arr = Integer.toBinaryString(n).toCharArray();
			ArrayList<Integer> list = new ArrayList<>(32);
			for (int i = 0; i < arr.length; i++) {
				char c = arr[arr.length-1-i];
				if(c == '1')
					list.add(i);
			}
			int a = 0;
			int b = 0;
			for (int i = 0; i < list.size(); i++) {
				int x = 1 << list.get(i);
				if(i % 2 == 0)
					a |= x;
				else
					b |= x;
			}
			pw.println(a + " " + b);
		}
		pw.close();
	}
}
