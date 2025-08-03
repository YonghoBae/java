import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MST_Kruskal {
	static class Edge implements Comparable<Edge> {
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static Edge[] edgeList;
	static int[] parents;
	static int V, E;

	static void makeSets() {
		parents = new int[V];
		for (int i = 0; i < V; ++i) {
			parents[i] = i;
		}
	}

	static int findSet(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = findSet(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		edgeList = new Edge[E];
		for (int i = 0; i < E; ++i) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(start, end, weight);
		} // 간선 리스트 생성

		// 간선 가중치 기준 오름차순 정렬
		Arrays.sort(edgeList);

		// V개의 단위 서로소 집합(트리)로 만듦
		makeSets();

		// 사용하지 않은 간선 중 가장 비용이 작은 간선들 사용하며 처리
		int count = 0, result = 0;
		for (Edge edge : edgeList) {
			if (union(edge.start, edge.end)) { //선택한 간선이 사이클을 발생시키지 않았다면
				result += edge.weight;
				if(++count==V-1) break;
			}
		}
		
		System.out.println(result);
	}
}
