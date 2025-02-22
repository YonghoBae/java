import java.util.Scanner;

public class Ring {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        int n = sc.nextInt();
        sc.nextLine();

        int slen  = s.length();
        String word= "", temp = "";

        int cnt =0, wsize=0;


        for(int i=0;i<n;i++){
            word = sc.nextLine();
            wsize = word.length();

            for(int j=0;j<wsize;j++){
                if(j+slen<=wsize){
                    temp = word.substring(j,j+slen);

                }else{
                    temp = word.substring(j);
                    temp += word.substring(0,(j+slen)%wsize);
                }

                if (s.equals(temp)) {
                    cnt++;
                    break;
                }

            }
        }

        System.out.println(cnt);

    }
}
