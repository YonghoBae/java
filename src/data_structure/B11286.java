package data_structure;

// 11286번: 절댓값 힙
// https://www.acmicpc.net/problem/11286
import java.io.*;
import java.util.*;

public class B11286 {
    static class Number {
        int value;
        int abs;

        Number(int value,int abs) {
            this.value = value;
            this.abs = abs;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Number> pq = new PriorityQueue<>((a, b)->{
            if(a.abs==b.abs) {
                return a.value-b.value;
            }else{
                return a.abs-b.abs;
            }
        });

        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++) {
            int n = Integer.parseInt(br.readLine());
            if(n!=0){
                pq.offer(new Number(n,Math.abs(n)));
            }else{
                if(!pq.isEmpty()){
                    Number num = pq.poll();
                    sb.append(num.value).append("\n");
                }else {
                    sb.append(0).append("\n");
                }
            }
        }

        System.out.print(sb);
    }
}