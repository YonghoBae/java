package bfs_dfs;

// [D4] [Professional] 키 순서 - 5643
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXQsLWKd5cDFAUo
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S5643 {
    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();
    static List<List<Integer>> reverseGraph = new ArrayList<>();
    static Deque<Integer> q = new ArrayDeque<>();
    static boolean[] v;
    static boolean[] reverseV;

    static void bfs(int start) {
        q.offer(start);
        v[start] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (Integer next : graph.get(curr)) {
                if (!v[next]) {
                    q.offer(next);
                    v[next] = true;
                }
            }
        }
    }

    static void reverseBfs(int start) {
        q.offer(start);
        reverseV[start] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (Integer next : reverseGraph.get(curr)) {
                if (!reverseV[next]) {
                    q.offer(next);
                    reverseV[next] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());
        StringBuilder sb =new StringBuilder();
        for(int t=1;t<=T;++t){
            graph.clear();
            reverseGraph.clear();
            
            N = Integer.parseInt(in.readLine());
            M = Integer.parseInt(in.readLine());

            for (int i = 0; i < N; ++i) {
                graph.add(new ArrayList<>());
                reverseGraph.add(new ArrayList<>());
            }

            StringTokenizer st;
            for (int i = 0; i < M; ++i) {
                st = new StringTokenizer(in.readLine());
                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken()) - 1;

                graph.get(start).add(end);
                reverseGraph.get(end).add(start);
            }
            v = new boolean[N];
            reverseV = new boolean[N];

            int cnt = N;
            for (int i = 0; i < N; ++i) {

                bfs(i);
                reverseBfs(i);

                for(int j=0;j<N;++j){
                    if(!v[j] && !reverseV[j] ){
                        cnt--;
                        break;
                    }
                }

                Arrays.fill(v, false);
                Arrays.fill(reverseV,false);
            }

            sb.append("#").append(t).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb.toString());
    }
}
