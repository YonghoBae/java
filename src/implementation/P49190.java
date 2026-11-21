package implementation;

// [level 5] 방의 개수 - 49190
// https://school.programmers.co.kr/learn/courses/30/lessons/49190
import java.util.*;

class P49190 {
    Map<Edge,Integer> edgeMap = new HashMap<>();
    Map<Node,Integer> nodeMap = new HashMap<>();
    
    public class Node {
        int r,c;
        Node(int r,int c){
            this.r = r;
            this.c = c;
        }
        
        public boolean equals(Object o){
            Node n = (Node) o;
            
            if(this.r==n.r&&this.c==n.c){
                return true;
            }
            
            return false;
        }
        
        public int hashCode() {
            return Objects.hash(r, c); // 한 줄로 해결
        }
    }
    
    public class Edge {
        int sr,sc;
        int er,ec;
        
        Edge(int sr,int sc,int er,int ec){
            this.sr = sr;
            this.sc = sc;
            this.er = er;
            this.ec = ec;
        }
        
        public boolean equals(Object o){
            Edge e = (Edge) o;
            
            if(this.sr==e.sr&&this.sc==e.sc&&this.er==e.er&&this.ec==e.ec){
                return true;
            }
            
            return false;
        }
        
        public int hashCode() {
            return Objects.hash(sr, sc, er, ec);
        }
    }
    
    public int solution(int[] arrows) {
        int answer = 0;
        
        int size = arrows.length;
        
        int[] dr= {-1,-1,0,1,1,1,0,-1};
        int[] dc= {0,1,1,1,0,-1,-1,-1};
        
        int r=0,c=0;
        nodeMap.put(new Node(0,0),1);
        int nr=r,nc=c;
        //size만큼 반복
        for(int d:arrows){
            //arrow 방향으로 2칸씩 이동
            for(int i=0;i<2;++i){
                nr += dr[d];
                nc += dc[d];
                
                Edge edge1 = new Edge(r,c,nr,nc);
                Edge edge2 = new Edge(nr,nc,r,c);
                
                //이미 지나갔던 간선을 지나가서 방문하는거라면? 카운팅 x
                if(!edgeMap.containsKey(edge1)&&!edgeMap.containsKey(edge2)) 
                {
                    edgeMap.put(edge1,1);
                    edgeMap.put(edge2,1);
                    
                    Node node = new Node(nr,nc);
                    //방문하는 방들이 이미 방문했다면? 카운팅
                    if(nodeMap.containsKey(node)) answer++;
                    else nodeMap.put(node,1);
                }
        
                r = nr;
                c = nc;
            }        
        }
         
        
        return answer;
    }
}