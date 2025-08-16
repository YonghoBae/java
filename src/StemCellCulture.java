import java.io.*;
import java.util.*;

public class StemCellCulture {
    static int N, M, K;
    static int[][] map;
    static List<Cell> cellList;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    // 세포 상태 상수
    static final int INACTIVE = 0;
    static final int ACTIVE = 1;
    static final int DEAD = 2;

    static class Cell implements Comparable<Cell> {
        int r, c, x, time, status;

        public Cell(int r, int c, int x) {
            this.r = r;
            this.c = c;
            this.x = x;      // 생명력 수치
            this.time = x;   // 상태가 변하기까지 남은 시간
            this.status = INACTIVE;
        }

        // 우선순위 큐: 생명력(x)이 높은 순서대로 정렬 (내림차순)
        @Override
        public int compareTo(Cell o) {
            return Integer.compare(o.x, this.x);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // 맵 크기: N + K, M + K로 설정하여 인덱스 에러 방지
            // 중앙 배치를 위해 offset을 K/2 정도로 설정
            int offset = 150;
            map = new int[N + 300][M + 300];
            cellList = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int x = Integer.parseInt(st.nextToken());
                    if (x > 0) {
                        map[i + offset][j + offset] = x;
                        cellList.add(new Cell(i + offset, j + offset, x));
                    }
                }
            }

            solve();

            // 결과 출력: 살아있는 세포(비활성 + 활성) 개수 계산
            int count = 0;
            for (Cell c : cellList) {
                if (c.status != DEAD) count++;
            }
            System.out.println("#" + t + " " + count);
        }
    }

    static void solve() {
        for (int k = 1; k <= K; k++) {
            PriorityQueue<Cell> pQ = new PriorityQueue<>();

            // 1. 기존 세포들 상태 업데이트
            for (Cell c : cellList) {
                if (c.status == DEAD) continue;

                c.time--; // 1시간 경과

                // 활성화된 첫 1시간 동안 번식 후보를 PQ에 담음
                if (c.status == ACTIVE && c.time == c.x - 1) {
                    for (int d = 0; d < 4; d++) {
                        int nr = c.r + dr[d];
                        int nc = c.c + dc[d];
                        if (map[nr][nc] == 0) {
                            pQ.add(new Cell(nr, nc, c.x));
                        }
                    }
                }

                // 상태 전환 로직
                if (c.time == -1) { // 해당 상태 시간이 다 됨
                    if (c.status == INACTIVE) {
                        c.status = ACTIVE;
                        c.time = c.x - 1; // 활성 상태 유지 시간 설정
                    } else if (c.status == ACTIVE) {
                        c.status = DEAD; // 죽음 상태로 변경
                    }
                }
            }

            // 2. PQ를 이용해 새로운 세포 번식 (경합 해결)
            while (!pQ.isEmpty()) {
                Cell nc = pQ.poll();
                if (map[nc.r][nc.c] == 0) {
                    map[nc.r][nc.c] = nc.x;
                    cellList.add(nc);
                }
            }
        }
    }
}