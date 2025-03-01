import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BalloonShootingGame {
    static int N, result, maxResult;
    static List<Integer> balloon = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // T가 없는 경우 에러 방지를 위해 readLine 확인 필요
        String line = in.readLine();
        if (line == null) return;

        int T = Integer.parseInt(line);

        StringTokenizer st;
        for (int t = 1; t <= T; ++t) {
            N = Integer.parseInt(in.readLine());
            st = new StringTokenizer(in.readLine());

            balloon.clear();
            for (int i = 0; i < N; ++i) {
                balloon.add(Integer.parseInt(st.nextToken()));
            }

            result = 0;
            maxResult = Integer.MIN_VALUE;
            dfs();

            System.out.println(maxResult);
        }
    }

    public static void dfs() {
        int size = balloon.size();
        if (size == 1) {
            // getFirst() -> get(0)
            int val = balloon.get(0);
            result += val;
            maxResult = Math.max(maxResult, result);
            result -= val;
            return;
        }

        for (int i = 0; i < size; ++i) {
            int temp = balloon.get(i);
            if (i == 0) {
                result += balloon.get(1);
                // removeFirst() -> remove(0)
                balloon.remove(0);
                dfs();
                // addFirst() -> add(0, temp)
                balloon.add(0, temp);
                result -= balloon.get(1);
            } else if (i == size - 1) {
                result += balloon.get(size - 2);
                // removeLast() -> remove(size - 1)
                balloon.remove(size - 1);
                dfs();
                // addLast() -> add(temp)
                balloon.add(temp);
                result -= balloon.get(size - 2);
            } else {
                result += balloon.get(i - 1) * balloon.get(i + 1);
                balloon.remove(i);
                dfs();
                balloon.add(i, temp);
                result -= balloon.get(i - 1) * balloon.get(i + 1);
            }
        }
    }
}