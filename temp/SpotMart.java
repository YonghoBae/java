import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SpotMart {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(in.readLine());

		StringTokenizer st;
		for (int t = 1; t <= tc; ++t) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(in.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] a = new int[N];
			st = new StringTokenizer(in.readLine());
			for (int n = 0; n < N; ++n) {
				a[n] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(a);

			int s = 0, e = N - 1, max = -1, sum;
			while (s < e) {
				sum = a[s] + a[e];
				if (sum == M) {
					max = M;
					break;
				} else if (sum < M) {
					max = Math.max(max, sum);
					s++;
				} else {
					e--;
				}
			}

			sb.append(max).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
//과자 봉지 N
//각 무게 ai
//M그램 이하인 무거운 과자 봉지
//2봉지 구매 가능