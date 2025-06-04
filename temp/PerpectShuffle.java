import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class PerpectShuffle {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=1;tc<=t;++tc) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			Deque<String> deque1 = new ArrayDeque<>();
			Deque<String> deque2 = new ArrayDeque<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int n=0;n<N;++n) {
				if(n<=(N-1)/2) {
					deque1.add(st.nextToken());
				}else {
					deque2.add(st.nextToken());
				}
			}
			
			for(int n=0;n<N;++n) {
				if(n%2!=1) {
					sb.append(deque1.pop()).append(" ");
				}else {
					sb.append(deque2.pop()).append(" ");
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
