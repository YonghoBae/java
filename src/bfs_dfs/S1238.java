package bfs_dfs;

// [D4] [S/W 문제해결 기본] 10일차 - Contact - 1238
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15B1cKAKwCFAYD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S1238 {
    static int maxNum, lastOrder;
    static List<Integer>[] graph = new List[100];
    static Deque<Node> q = new ArrayDeque<>();
    static boolean[] v;

    static class Node {
        int num,order;

        public Node(int num, int order) {
            this.num = num;
            this.order = order;
        }
    }

    static void solve(int start) {
        q.offer(new Node(start,0));
        v[start]=true;
        lastOrder = 0;
        maxNum = start;

        while(!q.isEmpty()){
            Node curr = q.poll();
            int num = curr.num;
            int order = curr.order;

            int nextOrder= order+1;
            for(Integer next:graph[num]) {
                if(!v[next]){
                    v[next]=true;
                    q.offer(new Node(next,nextOrder));

                    if(lastOrder==nextOrder){
                        maxNum = Math.max(maxNum,next);
                    }else if(lastOrder<nextOrder){
                        lastOrder = nextOrder;
                        maxNum = next;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int t=1;t<=10;++t){
            for(int i=0;i<100;i++) {
                graph[i]= new ArrayList<Integer>();
            }
            v = new boolean[100];

            st = new StringTokenizer(in.readLine());
            int len = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken())-1;

            st = new StringTokenizer(in.readLine());
            for(int i=0;i<len/2;++i){
                int from = Integer.parseInt(st.nextToken())-1;
                int to = Integer.parseInt(st.nextToken())-1;

                graph[from].add(to);
            }

            solve(start);

            sb.append("#").append(t).append(" ").append(maxNum+1).append("\n");
        }
        System.out.println(sb.toString());
    }
}