import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10703_Free_spots {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st;
		while(!false) {
			st = new StringTokenizer(bf.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			if(w == 0) break;
			boolean[][] arr = new boolean[h][w];
			while(n-->0) {
				st = new StringTokenizer(bf.readLine());
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				int startI = Math.min(y1, y2)-1;
				int startJ = Math.min(x1, x2)-1;
				int endI = Math.max(y1, y2);
				int endJ = Math.max(x1, x2);
				for (int i = startI; i < endI; i++)
					for (int j = startJ; j < endJ; j++)
						arr[i][j] = true;
			}
			bf.readLine();
			int count = 0;
			for (int i = 0; i < arr.length; i++)
				for (int j = 0; j < arr[i].length; j++)
					if(!arr[i][j]) count++;
			if(count == 0)
				pw.println("There is no empty spots.");
			else
				if(count == 1)
					pw.println("There is one empty spot.");
				else
					pw.println("There are " + count + " empty spots.");
		}
		pw.close();
	}
}
