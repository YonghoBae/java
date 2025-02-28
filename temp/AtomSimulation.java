import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AtomSimulation {
    static class Atom {
        int r, c, dir, k;
        Atom(int r, int c, int dir, int k) {
            this.r = r; this.c = c; this.dir = dir; this.k = k;
        }
    }

    static int[][] map = new int[4001][4001];
    static int[] dr = {1, -1, 0, 0}; // 상, 하, 좌, 우 (Y축)
    static int[] dc = {0, 0, -1, 1}; // 상, 하, 좌, 우 (X축)

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; ++t) {
            int N = Integer.parseInt(in.readLine().trim());
            ArrayList<Atom> aliveAtoms = new ArrayList<>();
            int totalEnergy = 0;

            for (int n = 0; n < N; ++n) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int c = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int r = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                aliveAtoms.add(new Atom(r, c, dir, k));
            }

            // 시뮬레이션 (최대 4000번의 0.5초 단위 이동)
            for (int step = 0; step <= 4000; step++) {
                if (aliveAtoms.size() < 2) break;

                // 1. 모든 원자 이동 및 맵에 에너지 누적
                for (Atom a : aliveAtoms) {
                    a.r += dr[a.dir];
                    a.c += dc[a.dir];

                    if (a.r >= 0 && a.r <= 4000 && a.c >= 0 && a.c <= 4000) {
                        map[a.r][a.c] += a.k;
                    }
                }

                // 2. 충돌 여부 판단 및 리스트 재구성
                ArrayList<Atom> nextAtoms = new ArrayList<>();
                for (Atom a : aliveAtoms) {
                    // 경계 밖으로 나간 원자 제외
                    if (a.r < 0 || a.r > 4000 || a.c < 0 || a.c > 4000) continue;

                    // 해당 좌표의 에너지가 내 에너지보다 크면 충돌 발생
                    if (map[a.r][a.c] > a.k) {
                        totalEnergy += a.k;
                        // map[a.r][a.c]는 마지막에 0으로 초기화해야 함
                    } else {
                        // 충돌하지 않은 원자만 다음 턴으로
                        nextAtoms.add(a);
                    }
                }

                // 3. 사용한 맵 좌표 초기화 (이동했던 모든 좌표를 0으로)
                for (Atom a : aliveAtoms) {
                    if (a.r >= 0 && a.r <= 4000 && a.c >= 0 && a.c <= 4000) {
                        map[a.r][a.c] = 0;
                    }
                }

                // 살아남은 원자들로 교체
                aliveAtoms = nextAtoms;
            }
            sb.append("#").append(t).append(" ").append(totalEnergy).append("\n");
        }
        System.out.print(sb.toString());
    }
}