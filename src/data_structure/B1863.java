package data_structure;

// [Gold IV] 스카이라인 쉬운거 - 1863
// https://www.acmicpc.net/problem/1863
// 분류: 자료 구조, 스택
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack; // 스택 추가
import java.util.StringTokenizer;

public class B1863 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int count = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // x는 사실 로직에 필요 없음
            int y = Integer.parseInt(st.nextToken());

            // 핵심 로직: 현재 높이보다 높은 건물이 스택에 있다면 pop (건물이 끝남)
            while (!stack.isEmpty() && stack.peek() > y) {
                stack.pop();
            }

            // 새로운 높이의 건물이 나타나면 count 증가 및 push
            if (y > 0) {
                if (stack.isEmpty() || stack.peek() < y) {
                    stack.push(y);
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}