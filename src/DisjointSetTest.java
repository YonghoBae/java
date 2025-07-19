import java.util.Arrays;

public class DisjointSetTest {

	static int N;
	static int[] parents;
	
	
	public static void makeSets() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = -1; // 집합의 크기를 표현하며 루트노트로 인식되게하여 단위집합 생성
		}
	}
	
	public static int findSet(int a) {
		if ( parents[a]<0 ) return a; // 자신이 자신의 부모라면 즉, 루트노트라면 집합의 대표자이므로 자신을 리턴
		return parents[a] = findSet(parents[a]); // path compression
	}
	
	public static boolean union(int a,int b) {
		
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false;
		
		if(parents[aRoot]<=parents[bRoot]) {
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
		}else {
			parents[bRoot] += parents[aRoot];
			parents[aRoot] = bRoot;
		}
		
		return true;
	}
	
	public static void main(String[] args) {

		N = 5;
		
		makeSets();
		System.out.println(Arrays.toString(parents));
		System.out.println(union(0,1));
		System.out.println(Arrays.toString(parents));
		System.out.println(union(2,1));
		System.out.println(Arrays.toString(parents));
		System.out.println(union(3, 2));
		System.out.println(Arrays.toString(parents));
		System.out.println(union(4, 3));
		System.out.println(Arrays.toString(parents));
		
		System.out.println(findSet(1));

		System.out.println(Arrays.toString(parents));
		
		
	}

}


/*
[0, 1, 2, 3, 4]
true
[0, 0, 2, 3, 4]
true
[2, 0, 2, 3, 4]
true
[2, 0, 3, 3, 4]
true
[2, 0, 3, 4, 4]
 */






