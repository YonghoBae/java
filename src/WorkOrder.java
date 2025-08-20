import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class WorkOrder {
    static int V,E;
    static int[] inDgree;
    static List<List<Integer>> edges;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;
        StringTokenizer st;
        for(int t=1;t<=T;++t){
            st = new StringTokenizer(in.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();
            for(int i=0;i<V;++i){
                edges.add(new ArrayList<>());
            }

            inDgree = new int[V];
            st = new StringTokenizer(in.readLine());
            for(int i=0;i<E;++i){
                int from = Integer.parseInt(st.nextToken())-1;
                int to = Integer.parseInt(st.nextToken())-1;
                edges.get(from).add(to);
                inDgree[to]++;
            }

            sb.append("#").append(t);
            findOrder();
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void findOrder() {
        Deque<Integer> q =new ArrayDeque<>();

        for(int i=0;i<V;++i){
            if(inDgree[i]==0){
                q.offer(i);
            }
        }

        while(!q.isEmpty()){
            int curr = q.poll();
            List<Integer> edge = edges.get(curr);
            sb.append(" ").append(curr+1);

            for(Integer next:edge){
                inDgree[next]--;
                if(inDgree[next]==0){
                    q.offer(next);
                }
            }
        }
    }
}
