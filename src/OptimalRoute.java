import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class OptimalRoute{
	static int N,minDist;
	static int[][] node;
	static int[] company;
	static boolean[] v;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; ++t) {
			N = Integer.parseInt(in.readLine());

			node = new int[N+1][2];
			v = new boolean[N+1];
			StringTokenizer st = new StringTokenizer(in.readLine());
			company = new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
			node[0] = new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
			
			
			for(int i=1;i<N+1;++i) {
				node[i][0] = Integer.parseInt(st.nextToken());
				node[i][1] = Integer.parseInt(st.nextToken());
			}
			
			v[0] = true;
			minDist = Integer.MAX_VALUE;
			dfs(0,0,0);
			sb.append("#").append(t).append(" ").append(minDist).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void dfs(int depth, int curr,int dist) {
		if(dist>=minDist) return;
		
		if(depth==N) {
			minDist = Math.min(minDist, dist+Math.abs(node[curr][0]-company[0])+Math.abs(node[curr][1]-company[1]));
			return;
		}
		
		for(int i=1;i<N+1;++i) {
			if(!v[i]) {
				int distance = Math.abs(node[curr][0]-node[i][0])+Math.abs(node[curr][1]-node[i][1]);
				v[i] = true;
				dfs(depth+1,i,dist+distance);
				v[i] = false;
			}
		}
	}
}
