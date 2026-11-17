package simulation;

// [Unrated] [모의 SW 역량테스트] 점심 식사시간 - 2383
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5-BEE6AK0DFAVl
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S2383 {
	static int N,peopleNum,minTime;
	static int[][] grid;
	static List<int[]> people = new ArrayList<>();
	static int[][] stair;
	static boolean[] isSelect;
	
	static class Node {
		int i,stair,time;

        public Node(int i, int stair, int time) {
            this.i = i;
            this.stair = stair;
            this.time = time;
        }
    }
	
	private static void dfs(int depth) {
		if(depth==peopleNum) {
			//이동 완료 시간 계산 로직
			int time = calcTime();
			//min 시간 갱신
            minTime = Math.min(time,minTime);
			return;
		}
		
		isSelect[depth] = true;
		dfs(depth+1);		
		isSelect[depth] = false;
		dfs(depth+1);
	}
	

	
	private static int calcTime() {
		int time=0;
		//1. PQ에 각각 거리 짧은 순으로 넣기
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->Integer.compare(a.time,b.time));
		for(int i=0;i<peopleNum;++i) {
			//계단 위치
			int[] stairPos = isSelect[i] ? stair[1]:stair[0];
			int stairR = stairPos[0];
			int stairC = stairPos[1];
			
			//사람 위치
			int[] pos = people.get(i);
			int r = pos[0];
			int c = pos[1];


			int timeToStair = Math.abs(r-stairR)+Math.abs(c-stairC);
			
			pq.offer(new Node(i,isSelect[i]?1:0,timeToStair));
		}

        PriorityQueue<Node>[] stairPqs = new PriorityQueue[2];

        stairPqs[0] = new PriorityQueue<Node>((a,b)->Integer.compare(a.time,b.time));
        stairPqs[1] = new PriorityQueue<Node>((a,b)->Integer.compare(a.time,b.time));

        //2. 먼저 도착한 순으로 꺼내서 종료시간을 같이 넣을까?
		while(!pq.isEmpty()) {
            Node curr = pq.poll();

            int index = curr.i;
            int stairIndex = curr.stair;
            int currTime = curr.time + 1;
            int K = grid[stair[stairIndex][0]][stair[stairIndex][1]];

            //시간 지난 사람은 제거
            while(!stairPqs[stairIndex].isEmpty()&&stairPqs[stairIndex].peek().time<=currTime) {
                stairPqs[stairIndex].poll();
            }

			//3. 계단 할당량이 남았다면 시간에 +K하고 넣기
			if(stairPqs[stairIndex].size()<3){
                stairPqs[stairIndex].offer(new Node(index,stairIndex,currTime+K));
            }else{
                // 안됐다면 가장 빠른 만료시간 + K
                int recentTime = stairPqs[stairIndex].peek().time;
                stairPqs[stairIndex].offer(new Node(index,stairIndex,recentTime+K));
            }
            time = currTime;
		}

        for(int i=0;i<2;++i){
            while(!stairPqs[i].isEmpty()){
                Node curr =stairPqs[i].poll();
                time = Math.max(time, curr.time);
            }
        }
		
		return time;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());

        StringBuilder sb = new StringBuilder();
		for(int t=1;t<=T;++t) {
			N = Integer.parseInt(in.readLine());
			grid = new int[N][N];
			stair = new int[2][2];
			
			
			StringTokenizer st;
			int stairCnt=0;
			for(int i=0;i<N;++i) {
				st = new StringTokenizer(in.readLine());
				for(int j=0;j<N;++j) {
					grid[i][j] = Integer.parseInt(st.nextToken());
					if(grid[i][j]==1) {
						people.add(new int[] {i,j});
					}else if(grid[i][j]!=0) {
						stair[stairCnt][0] = i;
						stair[stairCnt][1] = j;
						stairCnt++;
					}
				}
			}
			
			peopleNum = people.size();
			isSelect = new boolean[peopleNum];
			minTime = Integer.MAX_VALUE;
			dfs(0);

            sb.append("#").append(t).append(" ").append(minTime).append("\n");

			people.clear();
		}
        System.out.println(sb.toString());
	}
}
