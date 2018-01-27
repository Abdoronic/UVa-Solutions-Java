import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class UVa_10935_Throwing_cards_away_I {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		while(!false) {
			int n = Integer.parseInt(bf.readLine());
			if(n == 0) break; 
			Deque<Integer> deck = new LinkedList<>();
			for (int i = 1; i <= n; i++)
				deck.addLast(i);
			ArrayList<Integer> dis = new ArrayList<>(50);
			while(deck.size() > 1) {
				dis.add(deck.pollFirst());
				deck.addLast(deck.pollFirst());
			}
			pw.print("Discarded cards:");
			for (int i = 0; i < dis.size(); i++) {
				if(i == 0) pw.print(" ");
				if(i == dis.size() - 1)
					pw.print(dis.get(i));
				else
					pw.print(dis.get(i)+", ");
			}
			pw.println();
			pw.println("Remaining card: " + deck.pollFirst());
		}
		
		pw.close();
	}
}
