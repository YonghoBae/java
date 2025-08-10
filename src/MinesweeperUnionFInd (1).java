import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MinesweeperUnionFInd {
	static int N;
	static int[][] grid;
	static Node[][] parent;
	static int[] dr = { -1, 1, 0, 0, -1, 1, -1, 1 };
	static int[] dc = { 0, 0, -1, 1, 1, -1, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; ++t) {
			N = Integer.parseInt(in.readLine());
			grid = new int[N][N];
			parent = new Node[N][N];

			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					parent[i][j] = new Node(i, j, 1);
				}
			}

			for (int i = 0; i < N; ++i) {
				String line = in.readLine();
				for (int j = 0; j < N; ++j) {
					grid[i][j] = line.charAt(j);
				}
			}

			
//			for (int i = 0; i < N; ++i) {
//				for (int j = 0; j < N; ++j) {
//					if(union())
//				}
//			}
		}
	}
	
	private static Node findSet(Node a) {
		Node node = parent[a.r][a.c];
		if(a.r==node.r&&a.c==node.c) return node;
		
		return parent[a.r][a.c]=findSet(node).copy();
	}
	
	private static boolean union(Node a,Node b) {
		Node aRoot = findSet(a);
		Node bRoot = findSet(b);
		
		if(aRoot.r==bRoot.r&&aRoot.c==bRoot.c) return false;
		
		parent[aRoot.r][bRoot.c] = bRoot.copy();
		
		return true;
	}

	private static class Node {
		int r, c, size;

		public Node(int r, int c, int size) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
		}
		
		public Node copy() {
			return new Node(r,c,size);
		}
	}
}
