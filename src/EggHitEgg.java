//16987번
//예외처리 부분 놓쳐서 다시 풀어봐야할 것 같음
import java.util.Scanner;

public class EggHitEgg {
    static int[][] egg;
    static int n, max;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        max = 0;
        egg = new int[n][2];

        for(int i=0;i<n;++i){
            egg[i][0] = sc.nextInt();
            egg[i][1] = sc.nextInt();
        }

        backtrack(0);

        System.out.println(max);
    }


    public static void backtrack(int at){
        if(at == n){
            int cnt=0;
            for(int i=0;i<n;++i){
                if(egg[i][0]<=0){
                    cnt++;
                }
            }
            if(max<cnt){
                max = cnt;
            }
            return;
        }

        // 현재 계란이 이미 깨졌다면 다음 계란으로 진행
        if(egg[at][0] <= 0){
            backtrack(at + 1);
            return;
        }

        boolean hit = false;
        for(int i=0;i<n;++i){
            if(i == at || egg[i][0] <= 0) continue;

            // 계란 치기 시도
            egg[at][0] -= egg[i][1];
            egg[i][0] -= egg[at][1];
            hit = true;

            backtrack(at + 1);

            // 복구
            egg[at][0] += egg[i][1];
            egg[i][0] += egg[at][1];
        }

        // 칠 수 있는 계란이 없었던 경우 → 그냥 다음 계란으로 넘어가야 함
        if(!hit){
            backtrack(at + 1);
        }
    }
}
//내구도는 상대 무게만큼 깍임
//게란1 내구도 7 무게 5
//계란2 내구도 3 무게 4
//계란1 vs 계란2하면
//계란1 내구도 3 무게 5
//계란2 내구도 -2 무게 4 (깨짐)
//1.가장 왼쪽 계란 들기
//2.안깨진 계란 중 한개 치기
//3.가장 최근에 든 계란 오른쪽 계란 들고 2번 진행(가장 오른쪽 계란일 경우 종료