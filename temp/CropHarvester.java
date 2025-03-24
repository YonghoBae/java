import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CropHarvester {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; ++t) {
            int N = Integer.parseInt(in.readLine());

            int width = 1;
            String line;

            int[][] crops = new int[N][N];
            for (int i = 0; i < N; ++i) {
                line = in.readLine();

                for (int j = 0; j < N; ++j) {
                    int crop = line.charAt(j) - '0';
                    crops[i][j] = crop;
                }
            }

            int sum = 0;
            for (int i = 0; i < N; ++i) {
                if (N / 2 > i) {
                    int index = (N - width) / 2;
                    for (int k = index; k < (index + width); ++k) {
                        sum += crops[i][k];
                    }

                    width += 2;
                } else if(N/2==i){
                    int index = (N - width) / 2;
                    for (int k = index; k < (index + width); ++k) {
                        sum += crops[i][k];
                    }
                }else {
                    width -= 2;

                    int index = (N - width) / 2;
                    for (int k = index; k < (index + width); ++k) {
                        sum += crops[i][k];
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(sum).append("\n");
        }

        System.out.println(sb.toString());
    }
}