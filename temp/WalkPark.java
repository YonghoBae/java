import java.util.*;

class WalkPark {
    public int[] solution(String[] park, String[] routes) {
        int H = park.length;
        int W = park[0].length();
        
        int curY = 0; // Y좌표 (세로, 행)
        int curX = 0; // X좌표 (가로, 열)

        // 시작 지점 'S' 찾기
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (park[i].charAt(j) == 'S') {
                    curY = i;
                    curX = j;
                    break;
                }
            }
        }
        
        for (String r : routes) {
            String[] order = r.split(" ");
            String direction = order[0];
            int distance = Integer.parseInt(order[1]);
            
            int nextY = curY;
            int nextX = curX;
            boolean isMoveValid = true; // 이동이 유효한지 확인하는 플래그

            // 1. 한 칸씩 이동하며 경로 전체를 미리 검사
            for (int i = 0; i < distance; i++) {
                switch (direction) {
                    case "E": nextX++; break;
                    case "W": nextX--; break;
                    case "S": nextY++; break;
                    case "N": nextY--; break;
                }
                
                // 1-1. 공원을 벗어나는지 확인
                if (nextY < 0 || nextY >= H || nextX < 0 || nextX >= W) {
                    isMoveValid = false;
                    break;
                }
                // 1-2. 장애물을 만나는지 확인 (올바른 좌표계 사용)
                if (park[nextY].charAt(nextX) == 'X') {
                    isMoveValid = false;
                    break;
                }
            }
            
            // 2. 경로 검사를 통과했을 때만, 최종 위치로 업데이트
            if (isMoveValid) {
                curY = nextY;
                curX = nextX;
            }
        }

        // 3. 최종 위치를 {Y, X} 순서로 반환
        return new int[]{curY, curX};
    }
}