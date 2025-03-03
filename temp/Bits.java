import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bits {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= testCase; ++t) {
			st = new StringTokenizer(br.readLine());
			String[] bitsTexts = st.nextToken().split("");
			int[] bits = Arrays.stream(bitsTexts).mapToInt(Integer::parseInt).toArray();
			int length = bits.length;
			int[] originBits = new int[length];

			int cnt=0;
			for (int i=0; i < length; ++i) {
				if(checkBits(originBits,bits,length)) {
					break;
				}
				
				if (bits[i] != originBits[i]) {
					changeBits(originBits, i, length, bits[i]);
					cnt++;
				}
			
			}
			
			System.out.println("#"+t+" "+cnt);
		}
	}

	private static void changeBits(int[] originBits, int pos,int length,int num) {
		for(int i=pos;i<length;++i) {
			originBits[i] = num;
		}
	}
	
	private static boolean checkBits(int[] originBits,int[] bits, int length) {
		for(int i=0;i<length;++i) {
			if(originBits[i] != bits[i]) {
				return false;
			}
		}
		
		return true;
	}
}
