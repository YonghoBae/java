import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class VilageCounterUnionFind {
	static int N, M;
	static int[] parent;
	static boolean[] v;

	static Deque<Integer> q = new ArrayDeque<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; ++t) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			parent = new int[N];
			for (int i = 0; i < N; ++i) {
				parent[i] = i;
			}

			int cnt=N;
			for (int i = 0; i < M; ++i) {
				st = new StringTokenizer(in.readLine());
				int n1 = Integer.parseInt(st.nextToken()) - 1;
				int n2 = Integer.parseInt(st.nextToken()) - 1;
				
				if(union(n1,n2)) {
					cnt--;
				}
			}

			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static int findSet(int a) {
		if(a==parent[a]) {
			return a;
		}
		return findSet(parent[a]);
	}
	
	private static boolean union(int a,int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot==bRoot) return false;
		
		parent[bRoot] = aRoot;
		return true;
	}
}
