package divide_conquer;

// [Gold V] Z - 1074
// https://www.acmicpc.net/problem/1074
// 분류: 분할 정복, 재귀
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1074 {
    static int N, rr, rc, cnt,result;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        rr = Integer.parseInt(st.nextToken());
        rc = Integer.parseInt(st.nextToken());
        N= (int) Math.pow(2,N);

        cnt = 0;
        result=0;
        order(0,0,N);
        System.out.println(result);
    }


    static void order(int r,int c,int size) {
        //r~r+size에 rr이 없거나 c+c+size에 cc가 없으면 이 집합에는 없다.
        if(!(r<=rr&&rr<r+size)||!(c<=rc&&rc<c+size)){
            cnt += size*size;
            return;
        }

        if(result!=0){
            return;
        }

        if(size==2){
            for(int i=r;i<r+2;++i){
                for(int j=c;j<c+2;++j){
                    if(rr==i&&rc==j){
                        result = cnt;
                    }
                    cnt++;
                }
            }
            return;
        }

        order(r,c,size/2);
        order(r,c+size/2,size/2);
        order(r+size/2,c,size/2);
        order(r+size/2,c+size/2,size/2);
    }
}
