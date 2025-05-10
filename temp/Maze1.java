import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Maze1 {
	private static int arrive;
	private static int[] dx = { 1, -1, 0, 0 };
	private static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] maze = new String[16];
		for (int t = 1; t <= 10; ++t) {
			sb.append("#").append(t).append(" ");
			arrive = 0;
			br.readLine();

			for (int i = 0; i < 16; ++i) {
				maze[i] = br.readLine();
			}

			for (int i = 0; i < 16; ++i) {
				for (int j = 0; j < 16; ++j) {
					if (maze[i].charAt(j) == '2') {
						int[][] v = new int[16][16];
						isArrive(maze, v, i, j);
						break;
					}
				}
			}
			sb.append(arrive).append("\n");

		}

		System.out.println(sb.toString());
	}

	private static void isArrive(String[] maze, int[][] v, int x, int y) {
		if (maze[x].charAt(y) == '3') {
			arrive = 1;
			return;
		}

		v[x][y] = 1;

		int nx, ny;
		for (int i = 0; i < 4; ++i) {
			nx = x + dx[i];
			ny = y + dy[i];
			if (nx >= 16 || ny >= 16 || nx < 0 || ny < 0) {
				continue;
			}

			if (v[nx][ny] == 1) {
				continue;
			}

			if (maze[nx].charAt(ny) != '1') {
				isArrive(maze, v, nx, ny);
			}
		}
	}
}
