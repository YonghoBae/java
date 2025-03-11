import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Chef {
    static int N,minGab;
    static int[][] S;
    static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(in.readLine());
        for (int t = 1; t <= T; ++t) {
            N = Integer.parseInt(in.readLine());

            S = new int[N][N];
            isSelected = new boolean[N];
            minGab=Integer.MAX_VALUE;

            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < N; ++j) {
                    S[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0,0);
            sb.append("#").append(t).append(" ").append(minGab).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void dfs(int depth,int selectCnt) {
        if(selectCnt>N/2) return;

        if(depth==N){
            if(selectCnt!=N/2) return;

            int sum1=0,sum2=0;
            for(int i=0;i<N;++i){
                for(int j=0;j<N;++j){
                    if(isSelected[i]&&isSelected[j]){
                        sum1+=S[i][j];
                    }else if(!isSelected[i]&&!isSelected[j]){
                        sum2+=S[i][j];
                    }
                }
            }

            minGab = Math.min(minGab,Math.abs(sum1-sum2));
            return;
        }

        isSelected[depth]=true;
        dfs(depth+1,selectCnt+1);
        isSelected[depth] = false;
        dfs(depth+1,selectCnt);
    }
}