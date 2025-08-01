import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MST {
    static class Edge implements Comparable<Edge>{
        int start,end,weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight,o.weight);
        }
    }

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        StringBuilder sb =new StringBuilder();
        for(int t=1;t<=T;++t){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            parents = new int[V];
            for(int i=0;i<V;++i){
                parents[i] = i;
            }

            List<Edge> edges = new ArrayList<>();
            for(int e=0;e<E;++e){
                st = new StringTokenizer(in.readLine());
                int start = Integer.parseInt(st.nextToken())-1;
                int end = Integer.parseInt(st.nextToken())-1;
                int weight = Integer.parseInt(st.nextToken());
                edges.add(new Edge(start,end,weight));
            }

            Collections.sort(edges);

            long minWeight=0;
            for(int i=0;i<E;++i){
                Edge e = edges.get(i);

                if(union(e.start,e.end)){
                    minWeight+=e.weight;
                }
            }

            sb.append("#").append(t).append(" ").append(minWeight).append("\n");
        }
        System.out.println(sb.toString());
    }

    static int findSet(int a){
        if(parents[a]==a) return a;
        return parents[a]=findSet(parents[a]);
    }

    static boolean union(int a,int b){
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if(aRoot==bRoot) return false;

        parents[bRoot] = aRoot;

        return true;
    }

}
