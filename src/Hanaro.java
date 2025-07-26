import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Hanaro {
	static class Edge implements Comparable<Edge> {
		int start, end;
		double weight;

		public Edge(int start, int end, double weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight,o.weight);
		}
	}

	static int N;
	static double E;
	static int[] parents;
	static double[] x, y;
	static List<Edge> edges = new ArrayList<>();
	
	public static int findSet(int a) {
		if(a==parents[a]) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	private static boolean union(int a,int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot==bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb= new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; ++t) {
			N = Integer.parseInt(in.readLine());

			x = new double[N];
			y = new double[N];

			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; ++i) {
				x[i] = Double.parseDouble(st.nextToken());
			}
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; ++i) {
				y[i] = Double.parseDouble(st.nextToken());
			}
			E = Double.parseDouble((in.readLine()));

			edges.clear();
			for(int i=0;i<N;++i) {
				for(int j=0;j<N;++j) {
					if(i!=j) {
						edges.add(new Edge(i,j,(x[i]-x[j])*(x[i]-x[j])+(y[i]-y[j])*(y[i]-y[j])));
					}
				}
			}
			
			parents = new int[N];
			for(int i=0;i<N;++i) {
				parents[i]=i;
			}

			Collections.sort(edges);
			
			double minL = 0,count=0;
			for(Edge edge:edges) {
				if(union(edge.start,edge.end)) {
					minL += edge.weight;
					if(++count==N-1) break;
				}
			}


			long result = Math.round(E * minL);
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
}
//환경 부담금: E * L^2
//터널 길이 최소