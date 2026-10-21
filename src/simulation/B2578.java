package simulation;

// [Silver IV] 빙고 - 2578
// https://www.acmicpc.net/problem/2578
// 분류: 구현, 시뮬레이션
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2578 {
	public static void main(String[] args) throws Exception {
		int[][] bingo = new int[5][5];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 5; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; ++j) {
				bingo[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 0, num, bingoNum;
		for (int i = 0; i < 5; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; ++j) {
				cnt++;
				num = Integer.parseInt(st.nextToken());
				bingo = findBingoNum(bingo,num);
				bingoNum = checkBingo(bingo);
				
				if(bingoNum>=3) {
					System.out.println(cnt);
					return;
				}
			}
		}

	}

	private static int[][] findBingoNum(int[][] bingo, int num) {
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				if (bingo[i][j] == num) {
					bingo[i][j] = 0;
					return bingo;
				}
			}
		}
		return bingo;
	}

	private static int checkBingo(int[][] bingo) {
		int cnt = 0;
		for (int i = 0; i < 5; ++i) {
			boolean isBingo = true;
			for (int j = 0; j < 5; ++j) {
				if (bingo[i][j] != 0) {
					isBingo = false;
				}
			}
			if (isBingo) {
				cnt++;
			}
		}

		for (int i = 0; i < 5; ++i) {
			boolean isBingo = true;
			for (int j = 0; j < 5; ++j) {
				if (bingo[j][i] != 0) {
					isBingo = false;
				}
			}
			if (isBingo) {
				cnt++;
			}
		}

		boolean isBingo = true;
		for (int i = 0; i < 5; ++i) {
			if (bingo[i][i] != 0) {
				isBingo = false;
			}
		}
		if (isBingo) {
			cnt++;
		}

		isBingo = true;
		for (int i = 0; i < 5; ++i) {
			if (bingo[i][4 - i] != 0) {
				isBingo = false;
			}
		}

		if (isBingo) {
			cnt++;
		}

		return cnt;
	}
}
