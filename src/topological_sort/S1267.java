package topological_sort;

// [D6] [S/W 문제해결 응용] 10일차 - 작업순서 - 1267
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV18TrIqIwUCFAZN
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S1267 {
    static int V, E;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] dist;
    static Deque<Integer> q= new ArrayDeque<>();
    static List<Integer> result = new ArrayList<>();

    static void bfs() {
        while(!q.isEmpty()){
            int curr = q.poll();
            result.add(curr+1);

            for(Integer next:graph.get(curr)){
                if(dist[next]==0){
                    continue;
                }

                if(dist[next]>0){
                    dist[next]--;
                    if(dist[next]==0){
                        q.offer(next);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        int T = 10;
        for(int t=1;t<=T;++t) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            for(int i=0;i<V;++i){
                graph.add(new ArrayList<>());
            }

            dist = new int[V];

            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < E; ++i) {
                int from = Integer.parseInt(st.nextToken())-1;
                int to = Integer.parseInt(st.nextToken())-1;
                graph.get(from).add(to);
                dist[to]++;
            }

            for(int i=0;i<V;++i){
                if(dist[i]==0){
                    q.offer(i);

                }
            }

            bfs();

            sb.append("#").append(t).append(" ");
            for(Integer order:result){
                sb.append(order).append(" ");
            }
            sb.append("\n");

            result.clear();
            graph.clear();
        }

        System.out.println(sb.toString());
    }
}
