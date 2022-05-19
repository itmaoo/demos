package recuit;

public class BinaryGap {

    public  int solution(int N) {
        // write your code in Java SE 8
        int max = 0;
        max = calc(5,0,max);
        System.out.println(max);
        return 0;
    }

    public  int calc(int n,int current0,int max) {
        if (n==1){
            if(current0>0){
                if(max<current0){
                    max = current0;
                }
            }
            return max;
        }else if (n==0){
            return max;
        }

        int v1 = n%2;
        if(v1==1){
            if(current0>0){
                max = current0;
            }
            current0 = 0;
        }else {
            current0 ++;
        }
        int v2 = n/2;
        return   calc(v2,current0,max);
    }
}
