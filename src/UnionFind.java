import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UnionFind {
	static int[] parent;
	static int[] size;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T= Integer.parseInt(in.readLine());
		StringBuilder sb =new StringBuilder();
		for(int t=1;t<=T;++t) {
			sb.append("#").append(t).append(" ");
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			parent = new int[N];
			size = new int[N];
			for(int i=0;i<N;++i) {
				parent[i] = i;
				size[i]=1;
			}
			
			for(int i=0;i<M;++i) {
				st = new StringTokenizer(in.readLine());
				int command = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				
				if(command==0) {
					//union
					union(a,b);
				}else if(command==1) {
					//find
					if(findSet(a)==findSet(b)) {
						sb.append(1);
					}else {
						sb.append(0);
					}
				}
			}
			
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static int findSet(int a) {
		if(a==parent[a]) return a;
		return parent[a] = findSet(parent[a]);
	}
	
	private static boolean union(int a,int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot==bRoot) return false;
		
		if(size[aRoot]<size[bRoot]) {
			parent[aRoot] = bRoot;
			size[bRoot] += size[aRoot];
		}else {
			parent[bRoot] = aRoot;
			size[aRoot] += size[bRoot];
		}
		return true;
	}
}
