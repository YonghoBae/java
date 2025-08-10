import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Minesweeper {
	static int N;
	static int[][] grid;
	static int[] dr = { -1, 1, 0, 0, -1, 1, -1, 1 };
	static int[] dc = { 0, 0, -1, 1, 1, -1, -1, 1 };

	static Deque<Node> q = new ArrayDeque<>();
	static boolean[][] v;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; ++t) {
			N = Integer.parseInt(in.readLine());
			grid = new int[N][N];
			v = new boolean[N][N];

			for (int i = 0; i < N; ++i) {
				String line = in.readLine();
				for (int j = 0; j < N; ++j) {
					grid[i][j] = line.charAt(j);
				}
			}

			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (grid[i][j] == '.') {
						int cnt = 0;
						for (int d = 0; d < 8; ++d) {
							int nr = i + dr[d];
							int nc = j + dc[d];
							if (nr < 0 || nr >= N || nc < 0 || nc >= N)
								continue;

							if (grid[nr][nc] == '*')
								cnt++;
						}

						grid[i][j] = cnt;
					}
				}
			}

			int cnt = 0;
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (!v[i][j] && grid[i][j] != '*') {
						bfs(new Node(i, j));
						cnt++;
					}
				}
			}

			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void bfs(Node start) {
		q.offer(start);
		v[start.r][start.c] = true;

		while (!q.isEmpty()) {
			Node curr = q.poll();

			for (int d = 0; d < 8; ++d) {
				int nr = curr.r + dr[d];
				int nc = curr.c + dc[d];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;

				if (!v[nr][nc]) {
					
					if (grid[nr][nc] == 0) {
						v[nr][nc] = true;
						q.offer(new Node(nr, nc));
					}
					else if (grid[curr.r][curr.c]==0&&grid[nr][nc] != '*') {
						v[nr][nc] = true;
					}
				}
			}
		}
	}

	private static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
}
