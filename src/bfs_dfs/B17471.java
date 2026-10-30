package bfs_dfs;

// [Gold III] 게리맨더링 - 17471
// https://www.acmicpc.net/problem/17471
// 분류: 수학, 그래프 이론, 브루트포스 알고리즘, 그래프 탐색, 조합론, 너비 우선 탐색, 비트마스킹, 깊이 우선 탐색
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B17471 {
	static boolean[] isSelected;
	static List<List<Integer>> edges;
	static int[] people;
	static int N, total, minGab;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());

		people = new int[N];
		isSelected = new boolean[N];
		total = 0; // 초기화
		minGab = Integer.MAX_VALUE;

		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int n = 0; n < N; ++n) {
			people[n] = Integer.parseInt(st.nextToken());
			total += people[n]; // 전체 인구 합계를 반드시 구해야 합니다!
		}

		edges = new ArrayList<>();

		for (int n = 0; n < N; ++n) {
			st = new StringTokenizer(in.readLine());
			int eNum = Integer.parseInt(st.nextToken());
			List<Integer> edge = new ArrayList<>();
			for (int i = 0; i < eNum; ++i) {
				edge.add(Integer.parseInt(st.nextToken()) - 1);
			}

			edges.add(edge);
		}

		dfs(0, 0, 0);

		System.out.println(minGab == Integer.MAX_VALUE ? -1 : minGab);
	}



	private static void dfs(int depth, int selectCnt, int selectSum) {
	    if (depth == N) {
	        // selectCnt(A팀 개수)를 활용하면 selectedCnt() 함수를 호출할 필요도 없습니다.
	        if (selectCnt == 0 || selectCnt == N) return;

	        if (checkConnect()) {
	            minGab = Math.min(minGab, Math.abs(selectSum - (total - selectSum)));
	        }
	        return;
	    }

	    isSelected[depth] = true;
	    dfs(depth + 1, selectCnt + 1, selectSum + people[depth]);
	    isSelected[depth] = false;
	    dfs(depth + 1, selectCnt, selectSum);
	}

	private static boolean checkConnect() {
	    int startA = -1, startB = -1;
	    int countA = 0, countB = 0;

	    for (int i = 0; i < N; i++) {
	        if (isSelected[i]) {
	            startA = i;
	            countA++;
	        } else {
	            startB = i;
	            countB++;
	        }
	    }

	    // 2. A팀 연결 확인
	    boolean[] vA = new boolean[N]; // A팀 전용 방문 배열
	    int visitCountA = 1;
	    Queue<Integer> q = new LinkedList<>();
	    q.add(startA);
	    vA[startA] = true;
	    while (!q.isEmpty()) {
	        int curr = q.poll();
	        for (int next : edges.get(curr)) {
	            if (!vA[next] && isSelected[next]) {
	                vA[next] = true;
	                q.add(next);
	                visitCountA++;
	            }
	        }
	    }

	    // 3. B팀 연결 확인
	    boolean[] vB = new boolean[N]; // B팀 전용 방문 배열 (새로 생성!)
	    int visitCountB = 1;
	    q.add(startB);
	    vB[startB] = true;
	    while (!q.isEmpty()) {
	        int curr = q.poll();
	        for (int next : edges.get(curr)) {
	            if (!vB[next] && !isSelected[next]) {
	                vB[next] = true;
	                q.add(next);
	                visitCountB++;
	            }
	        }
	    }

	    return (visitCountA == countA) && (visitCountB == countB);
	}
}