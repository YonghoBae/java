import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class VilageCounter {
	static int N,M;
	static List<List<Integer>> edges = new ArrayList<>();
	static boolean[] v;
	
	static Deque<Integer> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t=1;t<=T;++t) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			v=new boolean[N];
			
			edges.clear();
			for(int i=0;i<N;++i) {
				edges.add(new ArrayList<>());
			}
			
			for(int i=0;i<M;++i) {
				st = new StringTokenizer(in.readLine());
				int n1 = Integer.parseInt(st.nextToken())-1;
				int n2 = Integer.parseInt(st.nextToken())-1;
				edges.get(n1).add(n2);
				edges.get(n2).add(n1);
			}
			
			int cnt=0;
			for(int i=0;i<N;++i) {
				if(!v[i]) {
					bfs(i);
					cnt++;
				}
			}
			
			sb.append("#").append(t).append(" ").append(cnt).append("\n"); 
		}
		System.out.println(sb.toString());
	}
	
	private static void bfs(int start) {
		q.offer(start);
		v[start] = true;
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			
			for(Integer next:edges.get(curr)) {
				if(!v[next]) {
					q.offer(next);
					v[next] = true;
				}
			}
		}
	}
}
