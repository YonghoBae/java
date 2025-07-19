import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DSLR {
	static int end;
	static boolean[] v;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());
		for (int t = 1; t <= T; ++t) {
			StringTokenizer st = new StringTokenizer(in.readLine());

			int start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());

			v = new boolean[10000];

			System.out.println(bfs(start));
		}
	}

	private static String bfs(int start) {
		Deque<Node> q = new ArrayDeque<>();
		q.offer(new Node(start, ""));
		v[start] = true;

		while (!q.isEmpty()) {
			Node curr = q.poll();

			if (curr.isEqual()) {
				return curr.commands;
			}

			
			int n = curr.D();
			if (!v[n])
				q.offer(new Node(n, curr.commands + "D"));
			v[n]= true;
			n = curr.S();
			if (!v[n])
				q.offer(new Node(n, curr.commands + "S"));
			v[n]= true;
			n = curr.L();
			if (!v[n])
				q.offer(new Node(n, curr.commands + "L"));
			v[n]= true;
			n = curr.R();
			if (!v[n])
				q.offer(new Node(n, curr.commands + "R"));
			v[n]= true;
		}

		return "";
	}

	private static class Node {
		int num;
		String commands;

		public Node(int num, String commands) {
			super();
			this.num = num;
			this.commands = commands;
		}

		public int D() {
			return num * 2 % 10000;
		}

		public int S() {
			if (num == 0) {
				return 9999;
			}
			return num - 1;
		}

		public int L() {
			int d1 = num / 1000;
			return (num * 10 + d1) % 10000;
		}

		public int R() {
			int d4 = num - num / 10 * 10;
			return (num + d4 * 10000) / 10;

		}

		public boolean isEqual() {
			if (num == end) {
				return true;
			} else {
				return false;
			}
		}
	}
}
