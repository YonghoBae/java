import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

// 클래스 이름을 Main으로 변경
public class Stack {
    public static void main(String[] args) {
        // try-with-resources 구문으로 Scanner 자동 관리
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            Deque<Integer> deque = new ArrayDeque<>();

            for (int i = 0; i < n; i++) {
                String command = sc.next(); // 명령어만 읽기

                switch (command) {
                    case "push":
                        // push일 경우에만 추가로 숫자 읽기
                        int num = sc.nextInt();
                        deque.addLast(num);
                        break;
                    case "pop":
                        if (deque.isEmpty()) {
                            System.out.println(-1);
                        } else {
                            // pop은 값을 꺼내기만 하므로 peekLast 대신 pollLast 사용
                            System.out.println(deque.pollLast());
                        }
                        break;
                    case "size":
                        System.out.println(deque.size());
                        break;
                    case "empty":
                        System.out.println(deque.isEmpty() ? 1 : 0);
                        break;
                    case "top":
                        // 비어있는 경우 예외 처리 추가
                        if (deque.isEmpty()) {
                            System.out.println(-1);
                        } else {
                            System.out.println(deque.peekLast());
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }
}