package shortest_path;

// [Gold V] 최소비용 구하기 - 1916
// https://www.acmicpc.net/problem/1916
// 분류: 그래프 이론, 최단 경로, 데이크스트라
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B1916 {

    static class Node {
        int v, cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N,M;

        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());

        List<List<Node>> graph = new ArrayList<>();
        for(int i=0;i<N;++i){
            graph.add(new ArrayList<>());
        }


        StringTokenizer st;
        for(int i=0;i<M;++i){
            st= new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end,cost));
        }

        st = new StringTokenizer(in.readLine());
        int start = Integer.parseInt(st.nextToken())-1;
        int end = Integer.parseInt(st.nextToken())-1;

        int[] dist = new int[N];
        for(int i=0;i<N;++i){
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->Integer.compare(a.cost,b.cost));
        pq.offer(new Node(start,0));
        dist[start]=0;

        while(!pq.isEmpty()) {
            Node curr = pq.poll();

            if(dist[curr.v]<curr.cost) continue;

            for(Node next:graph.get(curr.v)){
                int cost = dist[curr.v] + next.cost;

                if(cost<dist[next.v]){
                    dist[next.v] = cost;
                    pq.offer(new Node(next.v,next.cost));
                }
            }
        }

        System.out.println(dist[end]);
    }
}
