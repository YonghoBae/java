import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HandmadeBurger {
    static boolean[] isSelected;
    static int N,M,result;
    static int[] a,b;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());
        StringTokenizer st;
        StringBuilder sb =new StringBuilder();
        for(int t=1;t<=T;++t){
            st = new StringTokenizer(in.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            a = new int[M];
            b = new int[M];
            for(int i=0;i<M;++i){
                st = new StringTokenizer(in.readLine());
                a[i] = Integer.parseInt(st.nextToken())-1;
                b[i] = Integer.parseInt(st.nextToken())-1;
            }
            result=0;
            isSelected = new boolean[N];
            findSubset(0);
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void findSubset(int cnt){
        if(cnt==N){
            for(int i=0;i<M;++i){
                if(isSelected[a[i]]&&isSelected[b[i]]){
                   return;
                }
            }
            result++;

            return;
        }

        isSelected[cnt] = true;
        findSubset(cnt+1);
        isSelected[cnt] = false;
        findSubset(cnt+1);
    }
}
