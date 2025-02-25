import java.util.*;

class MemoriesScore {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        
        Map<String,Integer> score = new HashMap<>();
        
        for(int i=0;i<name.length;++i){
            score.put(name[i],yearning[i]);
        }
        
        int sum = 0;
        for(int i=0;i<photo.length;++i){
            sum = 0;
            for(int j=0;j<photo[i].length;++j){
                if(score.containsKey(photo[i][j])){
                  sum += score.get(photo[i][j]); 
                }
            }
            answer[i] = sum;
        }
        
        
        return answer;
    }
}

//추억 점수
//사진 -> 각 인물 각 그리움 점수 -> 각 인물에 대한 그리움 점수의 총합 = 추억 점수
//1. map으로 name에 대한 yearning 매핑
//2. photo 순회 후 각 추억 점수 계산
//3. 계산한 추억 점수 answer에 추가
