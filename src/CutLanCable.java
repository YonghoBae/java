import java.util.*;

public class CutLanCable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt(); // 기존 랜선 수
        int n = sc.nextInt(); // 필요한 랜선 수

        int[] lan = new int[k];
        for (int i = 0; i < k; i++) {
            lan[i] = sc.nextInt();
        }

        long left = 1;
        long right = Arrays.stream(lan).max().orElseThrow();
        long result = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;

            for (int length : lan) {
                count += length / mid;
            }

            if (count >= n) {
                result = mid;  // 조건을 만족했으니 일단 저장하고 더 긴 길이 시도
                left = mid + 1;
            } else {
                right = mid - 1;  // 조건을 만족 못하니 짧은 길이 시도
            }
        }

        System.out.println(result);
    }
}
