import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ProcessorConector {
	static int N,minLen,maxCnt;
	static int[][] grid;
	static List<int[]> processList = new ArrayList<>();
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			N = Integer.parseInt(in.readLine());
			grid = new int[N][N];
			processList.clear();
			for (int i = 0; i < N; ++i) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; ++j) {
					grid[i][j] = Integer.parseInt(st.nextToken());
					if (grid[i][j] > 0 && i != 0 && i != N - 1 && j != 0 && j != N - 1) {
						processList.add(new int[] { i, j });
					}
				}
			}

			minLen = Integer.MAX_VALUE;
			maxCnt = Integer.MIN_VALUE;
			dfs(0,0,0);
			
			sb.append(minLen).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int depth, int cnt,int lenSum) {
		if (depth == processList.size()) {
			if(maxCnt<cnt) {
				maxCnt = cnt;
				minLen = lenSum;
			}else if(maxCnt==cnt) {
				minLen = Math.min(minLen, lenSum);
			}
			return;
		}

		int[] curr = processList.get(depth);
		for (int d = 0; d < 4; ++d) {
			int nr = curr[0] + dr[d];
			int nc = curr[1] + dc[d];
			int len = 0;

			boolean flag = false;
			while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if (grid[nr][nc] >= 1) {
					flag = true;
					break;
				}
				grid[nr][nc] = depth+2;
				
				nr += dr[d];
				nc += dc[d];
				len++;
			}
			
			if(!flag) {
				dfs(depth+1,cnt+1,lenSum+len);
			}
			
			nr -= dr[d];
			nc -= dc[d];
			for(int i=0;i<len;++i) {
				grid[nr][nc] =0;
				nr -= dr[d];
				nc -= dc[d];
			}
		}
		dfs(depth+1,cnt,lenSum);
	}
}
