//15553번 난로
import java.util.Arrays;
import java.util.Scanner;

public class Hitter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] t = new int[n];
        for(int i=0;i<n;++i){
            t[i] = sc.nextInt();
        }

        //k==n인 경우
        //친구 올때마다 난로 켜면됨
        if(k==1){
            System.out.println(t[n-1]-t[0]+1);
            return;
        }

        if(k==n) {
            System.out.println(n);
            return;
        }

        //k<n인 경우
        //방문시간이 짧은 간격을 찾아야함
        //n-k개만큼 순서대로
        //방문간격 다 구하고
        //정렬해서 개수만큼 가져오면될듯
        if(k<n){
            int[] td = new int[n-1];
            for(int i=0;i<n-1;++i){
                td[i] = t[i+1]-t[i]+1;
            }
            Arrays.sort(td);

            int time=n-2*(n-k);
            //n개에서 2*(n-k)
            for(int i=0;i<n-k;++i) {
                time += td[i];
            }
            System.out.println(time);
        }

    }
}

//방에 혼자 -> 난로 x
//방에 나+친구 -> 난로 o
//친구 1-N
//i번째 친구 ti에 도착, ti+1에 나감
//한번에 한명 방문(방안은 2명 이하)
//난로 킬려면 성냥 이용 총 k개(k번 난로 켤 수 있음)
//처음에는 꺼져있음
//난로가 켜져 있는 시간 최솟값
//1 3 1 2 3 2 2 1 4
// 1 1 1 2 2
//12