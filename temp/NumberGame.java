import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class NumberGame {
    // [유지] 원래 사용하시던 전역 변수들
    static boolean[] isCut;
    static int N, maxTurn;
    static String num;

    // [추가] 시간 초과를 막기 위해 계산 결과를 저장하는 장부 (메모이제이션)
    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(in.readLine());

        for (int t = 1; t <= T; ++t) {
            String inputNum = in.readLine();
            map.clear(); // 테스트 케이스마다 초기화

            // [수정] solve 함수가 이 숫자로 갈 수 있는 최대 턴을 계산해옵니다.
            int result = solve(inputNum);

            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.print(sb.toString());
    }

    /**
     * 특정 숫자에서 게임을 끝낼 때까지 가능한 최대 턴수 반환
     */
    public static int solve(String s) {
        // 한 자릿수면 더 이상 쪼갤 수 없으므로 0턴 반환
        if (s.length() < 2) return 0;
        // 이미 계산해본 숫자라면 즉시 장부에서 꺼내줌 (시간 초과 해결)
        if (map.containsKey(s)) return map.get(s);

        // [유지] 기존 전역 변수들을 현재 숫자에 맞춰 세팅
        // (재귀에서 값이 섞이지 않게 임시 백업 후 세팅)
        String tempNum = num;
        int tempN = N;
        boolean[] tempIsCut = isCut;

        num = s;
        N = s.length();
        isCut = new boolean[N - 1];

        // [수정] dfs가 조합을 돌며 얻은 최대 턴수 중 가장 큰 값을 가져옴
        int currentMax = dfs(0);

        // 전역 변수 복구
        num = tempNum;
        N = tempN;
        isCut = tempIsCut;

        map.put(s, currentMax); // 계산 결과 장부에 기록
        return currentMax;
    }

    /**
     * 기존의 DFS: 모든 자르기 조합을 확인
     */
    public static int dfs(int depth) {
        if (depth == N - 1) {
            boolean flag = false;
            for (int i = 0; i < N - 1; ++i) {
                if (isCut[i]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) return 0;

            int mulNum = multipleNum();

            return solve(String.valueOf(mulNum)) + 1;
        }

        int res = 0;
        // 1. 자르는 경우
        isCut[depth] = true;
        res = Math.max(res, dfs(depth + 1));

        // 2. 안 자르는 경우
        isCut[depth] = false;
        res = Math.max(res, dfs(depth + 1));

        return res;
    }

    public static int multipleNum() {
        int preI = 0;
        int result = 1;
        for (int i = 0; i < N - 1; ++i) {
            if (isCut[i]) {
                result *= Integer.parseInt(num.substring(preI, i + 1));
                preI = i + 1;
            }
        }
        result *= Integer.parseInt(num.substring(preI, N));
        return result;
    }
}