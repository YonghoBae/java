import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessConnector {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());

        for (int t = 1; t <= T; ++t) {
            int N = Integer.parseInt(in.readLine());
            int[][] cell = new int[N][N];
            for (int i = 0; i < N; ++i) {
                String line = in.readLine();
                for (int j = 0; j < N; ++j) {
                    cell[i][j] = line.charAt(j) - '0';
                }
            }


        }
    }
}
