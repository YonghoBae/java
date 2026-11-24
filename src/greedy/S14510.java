package greedy;

// [D2] 나무 높이 - 14510
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AYFofW8qpXYDFAR4
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S14510 {
    static int N, maxH;
    static int[] tree;

    public static int solve() {
        int result=0;

        int one=0,two=0;
        for(int i=0;i<N;++i){
            int sub = maxH - tree[i];
            two += sub/2;
            sub %= 2;

            if(sub==1){
                one++;
            }
        }

        //1보다 2가
        while(two>one+1){
            two--;
            one += 2;
        }

        //1 > 2
        if(one>two){
            result = 2*one-1;
        }
        //1 = 2
        else if(one==two){
            result = one+two;
        }
        //1 < 2 정확히는 1개 더 많은경우
        else if(one<two){
            result = 2*two;
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());
        for(int t=1;t<=T;++t){
            N = Integer.parseInt(in.readLine());
            tree = new int[N];
            StringTokenizer st= new StringTokenizer(in.readLine());
            maxH = Integer.MIN_VALUE;
            for(int i=0;i<N;++i){
                tree[i] = Integer.parseInt(st.nextToken());
                maxH = Math.max(maxH,tree[i]);
            }

            sb.append("#").append(t).append(" ").append(solve()).append("\n");
        }

        System.out.println(sb.toString());
    }
}
