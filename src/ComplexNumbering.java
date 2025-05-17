
import java.util.*;

// 2667번 단지번호붙이기
public class ComplexNumbering {
    static int[][] map;
    static int mcnt = 0;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.nextLine();

        map = new int[n][n];

        for (int i = 0; i < n; ++i) {
            String s = sc.nextLine();
            for (int j = 0; j < n; ++j) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (map[i][j] == 1) {
                    mcnt = 0;
                    dfs(i, j);
                    answer.add(mcnt);
                }
            }
        }

        System.out.println(answer.size());
        answer.sort(Comparator.naturalOrder());
        for (int count : answer) {
            System.out.println(count);
        }
    }

    public static void dfs(int x, int y) {
        map[x][y] = 0;
        mcnt++;

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < n && ny < n && map[nx][ny] == 1) {
                dfs(nx, ny);
            }
        }
    }
}
