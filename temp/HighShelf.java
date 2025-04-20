import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HighShelf {
    static int N,B, minH;
    static int[] H;
    static boolean[] V;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int t=1;t<=T;++t) {
            st= new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            H = new int[N];
            V = new boolean[N];
            st = new StringTokenizer(in.readLine());
            for(int i=0;i<N;++i) {
                H[i] = Integer.parseInt(st.nextToken());
            }

            minH = Integer.MAX_VALUE;
            dfs(0,0);

            sb.append("#").append(t).append(" ").append(minH-B).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int depth,int h) {
        if(depth==N) {
            if(h>=B) {
                minH = Math.min(h, minH);
            }
            return;
        }

        dfs(depth+1,h+H[depth]);
        dfs(depth+1,h);
    }
}
//
//#1 1
//#2 4
//#3 27
//#4 11
//#5 42
//#6 32
//#7 2
//#8 3
//#9 25
//#10 0