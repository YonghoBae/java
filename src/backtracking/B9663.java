package backtracking;

// [Gold IV] N-Queen - 9663
// https://www.acmicpc.net/problem/9663
// 분류: 브루트포스 알고리즘, 백트래킹
import java.util.*;

public class B9663 {
    static int n;
    static int cnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[][] board = new int[n][n];
        backtrack(0, board);
        System.out.println(cnt);
    }

    static void backtrack(int depth, int[][] board) {
        if (depth == n) {
            cnt++;
            return;
        }

        for (int col = 0; col < n; ++col) {
            if (isSafe(board, depth, col)) {
                board[depth][col] = 1;
                backtrack(depth + 1, board);
                board[depth][col] = 0;
            }
        }
    }

    static boolean isSafe(int[][] board, int row, int col) {
        // 위쪽 열 검사
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) return false;
        }

        // 왼쪽 대각선
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }

        // 오른쪽 대각선
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1) return false;
        }

        return true;
    }
}
