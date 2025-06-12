import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class SkyLine {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Pos[] pos = new Pos[n];
        for(int i=0; i<n; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine());
            pos[i] = new Pos();
            pos[i].x = Integer.parseInt(st.nextToken());
            pos[i].y = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(pos, (p1, p2) -> p1.x - p2.x);

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int count = 0;

        for(int i=0; i<n; ++i){
            while(stack.peek() > pos[i].y) {
                stack.pop();
                count++;
            }

            if(stack.peek() < pos[i].y) {
                stack.push(pos[i].y);
            }
        }

        while(stack.peek() > 0) {
            stack.pop();
            count++;
        }

        System.out.println(count);
    }

    static class Pos {
        public int x;
        public int y;
    }
}