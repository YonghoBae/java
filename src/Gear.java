import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Gear {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deque<Integer>[] gear = new Deque[4];

        

        for(int i=0;i<4;++i){
            gear[i] = new ArrayDeque<>();
            String gearStatus = sc.next();
            for(int j=0;j<gearStatus.length();++j){
                gear[i].addLast(gearStatus.charAt(j) - '0');
            }
        }

        
    }
}
//1~4 톱니바퀴
//각 톱니바퀴의 톱니는 N or S
//톱니바퀴 총 k번 회전(시계 or 반시계)
//맞닿은 톱니의 극이 다르면 서로 반대로 회전
//극이 같으면 회전x
//옆이 회전 안하면 회전x

//자료구조 deque
//오른족 index = 2
//왼쪽 index = 6
//N극 = 0
//S극 = 1
//시계반향 = 1
//반시계반향 = -1