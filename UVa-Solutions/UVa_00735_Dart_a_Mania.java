import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeSet;

public class UVa_00735_Dart_a_Mania {
	
	static int n;
	static Long[][][] combMemo;
	static Long[][][] perMemo;
	static ArrayList<Integer> arr;
	
	public static long dpComb(int i, int n, int c) {
		if(n == 0 && c == 0)
			return 1;
		if(combMemo[i][n][c] != null) return combMemo[i][n][c];
		long ans = 0;
		if(n - arr.get(i) >= 0 && c - 1 >= 0)
			ans += dpComb(i, n- arr.get(i), c-1);
		
		if(i + 1 < arr.size())
			ans += dpComb(i+1, n, c);
		return combMemo[i][n][c] = ans;
	}
	
	public static long dpPer(int i, int n, int c) {
		if(n == 0 && c == 0)
			return 1;
		if(perMemo[i][n][c] != null) return perMemo[i][n][c];
		long ans = 0;
		if(n - arr.get(i) >= 0 && c - 1 >= 0)
			ans += dpPer(0, n- arr.get(i), c-1);
		
		if(i + 1 < arr.size())
			ans += dpPer(i+1, n, c);
		return perMemo[i][n][c] = ans;
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		arr = new ArrayList<>(100);
		TreeSet<Integer> t = new TreeSet<>();
		t.add(0);
		t.add(50);
		for (int i = 1; i <= 20; i++) {
			t.add(i);
			t.add(i*2);
			t.add(i*3);
		}
		for(Integer x : t)
			arr.add(x);
		perMemo = new Long[100][1005][5];
		combMemo = new Long[100][1005][5];
		dpComb(0, 1000, 3);
		dpPer(0, 1000, 3);
		while(true) {
			n = Integer.parseInt(bf.readLine());
			if(n <= 0) break;
			long comb = dpComb(0, n, 3);
			long per = dpPer(0, n, 3);
			if(comb == 0)
				pw.println("THE SCORE OF " + n +" CANNOT BE MADE WITH THREE DARTS.");
			else {
				pw.println("NUMBER OF COMBINATIONS THAT SCORES "+ n +" IS " + comb + ".");
				pw.println("NUMBER OF PERMUTATIONS THAT SCORES "+ n +" IS " + per + ".");
			}
			pw.println("**********************************************************************");
		}
		pw.println("END OF OUTPUT");
		pw.close();
	}
}
