import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Statistics {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        int[] nums = new int[N];
        int sum=0;
        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<N;++i){
            nums[i] = Integer.parseInt(in.readLine());
            sum+=nums[i];
            if(map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
            }else{
                map.put(nums[i],1);
            }
        }

        int maxCnt=Integer.MIN_VALUE;
        int maxCntNum=0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(maxCnt<entry.getValue()){
                maxCnt = entry.getValue();
                maxCntNum = entry.getKey();
            }
        }

        Arrays.sort(nums);

        System.out.println((int)Math.round((double)sum / N));

        System.out.println(nums[N/2]);

        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxCnt) {
                list.add(entry.getKey());
            }
        }
        Collections.sort(list);

        if (list.size() > 1) {
            System.out.println(list.get(1));
        } else {
            System.out.println(list.get(0));
        }

        System.out.println(nums[N-1] - nums[0]);
    }
}
