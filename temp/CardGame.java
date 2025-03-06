import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CardGame {
	static int[] cards = new int[10];
	static boolean[] isSelected = new boolean[19];
	static int[] enemyCards = new int[10];
	static int winCnt=0;
	static int looseCnt=0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; ++t) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(in.readLine()," ");
			Arrays.fill(isSelected, false);
			for(int i=1;i<=9;++i) {
				enemyCards[i] = Integer.parseInt(st.nextToken());
				isSelected[enemyCards[i]] = true;
			}
			winCnt=0;
			looseCnt=0;
			perm(0);
			sb.append(winCnt).append(" ").append(looseCnt).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void perm(int cnt) {
		if(cnt==9) {
			if(isWin()) {
				winCnt++;
			}else {
				looseCnt++;
			}
		}else { 
			for(int i=1;i<=18;++i) {
				if (isSelected[i]) continue;
				cards[cnt+1] = i;
				isSelected[i] = true;
				perm(cnt+1);
				isSelected[i] = false;
			}
		}
	}
	
	private static boolean isWin() {
		int score=0;
		int score2=0;
		for(int i=1;i<=9;++i) {
			if(enemyCards[i]<cards[i]) {
				score+=enemyCards[i]+cards[i];
			}else if(enemyCards[i]>cards[i]) {
				score2+=enemyCards[i]+cards[i];
			}
		}
		return score<score2;
	}
}
//1-18 카드
//카드를 섞고 9장씩 나눔
//9라운드 게임 진행
//높은 수가 적힌 카드를 낸 사람 -> 적힌 수 만큼 점수 얻기
//총점 높은 사람이 승
//한사람의 카드 순서는 고정
//이기는 경우와 지는 경우의 수
