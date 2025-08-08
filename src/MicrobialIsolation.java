import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

public class MicrobialIsolation {
	static class Micro {
		int r, c, size, dir, maxSize;

		public Micro(int r, int c, int size, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
			this.dir = dir;
			maxSize = size;
		}
		
		public void move() {
			this.r += dr[this.dir];
			this.c += dc[this.dir];

			if (this.r == 0) {
				this.dir = 1;
				this.size /= 2;
			} else if (this.r == N - 1) {
				this.dir = 0;
				this.size /= 2;
			} else if (this.c == 0) {
				this.dir = 3;
				this.size /= 2;
			} else if (this.c == N - 1) {
				this.dir = 2;
				this.size /= 2;
			}
		}
	}

	static int N, M, K;
	static List<Micro> micros;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	private static void solve() {
		Map<String, Micro> map = new HashMap<>();

		for (int k = 0; k < K; ++k) {
			Micro micro = micros.get(k);
			micro.move();

			String key = micro.r + " " + micro.c;
			if (!map.containsKey(key)) {
				map.put(key, micro);
			} else {
				Micro originMicro = map.get(key);
				if (micro.size > originMicro.maxSize) {
					originMicro.maxSize = micro.size;
					originMicro.dir = micro.dir;
				}
				originMicro.size += micro.size;
			}
		}

		List<Micro> nextList = new ArrayList<>();
		for (Micro m : map.values()) {
			if (m.size != 0) {
				m.maxSize = m.size;
				nextList.add(m);
			}
		}

		micros = nextList;
		K = micros.size();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; ++t) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			micros = new ArrayList<>();
			for (int k = 0; k < K; ++k) {
				st = new StringTokenizer(in.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;

				micros.add(new Micro(r, c, size, dir));
			}

			for (int m = 0; m < M; ++m) {
				solve();
			}
			
			int sum=0;
			for(int i=0;i<K;++i) {
				sum+=micros.get(i).size;
			}
			sb.append("#").append(t).append(" ").append(sum).append("\n");
			
		}
		System.out.println(sb.toString());
	}
}
