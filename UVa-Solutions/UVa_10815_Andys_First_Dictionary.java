import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class UVa_10815_Andys_First_Dictionary {
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		String line;
		TreeSet<String> t = new TreeSet<>();
		while((line = bf.readLine()) != null) {
			if(line.length() == 0) continue;
			line = line.toLowerCase().replaceAll("[^a-z]", " ");
			st = new StringTokenizer(line);
			while(st.hasMoreTokens())
				t.add(st.nextToken());
		}
		for(String i : t)
			if(i != "")
				pw.println(i);
		pw.close();
	}
}
