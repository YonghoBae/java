import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class WorkOrder {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; ++t) {
            String line = in.readLine();
            if (line == null || line.isEmpty()) break;

            StringTokenizer st = new StringTokenizer(line);
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            int[] indegree = new int[V];
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < V; ++i) {
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < E; ++i) {
                int inV = Integer.parseInt(st.nextToken()) - 1;
                int toV = Integer.parseInt(st.nextToken()) - 1;
                indegree[toV]++;
                graph.get(inV).add(toV);
            }

            sb.append("#").append(t);

            Deque<Integer> deque = new ArrayDeque<>();

            for (int i = 0; i < V; ++i) {
                if (indegree[i] == 0) {
                    deque.offer(i);
                }
            }

            while (!deque.isEmpty()) {
                int v = deque.poll();
                sb.append(" ").append(v + 1);

                for (Integer nextV : graph.get(v)) {
                    indegree[nextV]--;
                    if (indegree[nextV] == 0) {
                        deque.offer(nextV);
                    }
                }
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}