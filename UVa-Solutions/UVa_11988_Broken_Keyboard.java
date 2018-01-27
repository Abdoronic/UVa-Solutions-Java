import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.LinkedList;

public class UVa_11988_Broken_Keyboard {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		String line;
		while ((line = bf.readLine()) != null && line.length() != 0) {
			char[] arr = line.toCharArray();
			Deque<String> main = new LinkedList<>();
			StringBuilder temp = new StringBuilder();
			boolean end = true;
			for (int i = 0; i < arr.length; i++) {
				char c = arr[i];
				if (c == '[') {
					if (end)
						main.addLast(temp.toString());
					else
						main.addFirst(temp.toString());
					temp = new StringBuilder();
					end = false;
					continue;
				} else 
					if (c == ']') {
						if (end)
							main.addLast(temp.toString());
						else
							main.addFirst(temp.toString());
						temp = new StringBuilder();
						end = true;
						continue;
					}
				temp.append(c);
			}
			if (end)
				main.addLast(temp.toString());
			else
				main.addFirst(temp.toString());
			while(!main.isEmpty())
				pw.print(main.removeFirst());
			pw.println();
		}
		pw.close();
	}
}
