package divide_conquer;

// [Silver II] 색종이 만들기 - 2630
// https://www.acmicpc.net/problem/2630
// 분류: 분할 정복, 재귀
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2630 {
    static int N,zCnt,oCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        StringTokenizer st;
        int[][] paper = new int[N][N];
        for(int i=0;i<N;++i){
            st = new StringTokenizer(in.readLine());
            for(int j=0;j<N;++j){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        zCnt=0;
        oCnt=0;
        sameColor(paper,N);
        System.out.println(zCnt);
        System.out.println(oCnt);

    }

    static void sameColor(int[][] paper,int size) {
        int color = paper[0][0];
        boolean flag = false;
        for(int i=0;i<size;++i){
            for(int j=0;j<size;++j){
                if(paper[i][j]!=color){
                    flag = true;
                }
            }
        }

        if(flag){
            int[][] paper1 = new int[size/2][size/2];
            int[][] paper2 = new int[size/2][size/2];
            int[][] paper3 = new int[size/2][size/2];
            int[][] paper4 = new int[size/2][size/2];

            for(int i=0;i<size/2;++i){
                for(int j=0;j<size/2;++j){
                    paper1[i][j] = paper[i][j];
                    paper2[i][j] = paper[i+size/2][j];
                    paper3[i][j] = paper[i][j+size/2];
                    paper4[i][j] = paper[i+size/2][j+size/2];
                }
            }
            sameColor(paper1,size/2);
            sameColor(paper2,size/2);
            sameColor(paper3,size/2);
            sameColor(paper4,size/2);

        }else{
            if(color==0){
                zCnt++;
            }else{
                oCnt++;
            }
        }
    }
}
