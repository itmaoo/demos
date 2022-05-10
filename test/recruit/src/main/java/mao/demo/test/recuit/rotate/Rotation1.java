package mao.demo.test.recuit.rotate;

import org.junit.Test;

public class Rotation1 {
    @Test
    public void runCase(){
        solution(new int[]{1,2,3},1);
    }
    public int[] solution(int[] A, int K) {
        // write your code in Java SE 8
        for(int i = 0; i < K; i++){
            rotate(A);
        }
        return A;
    }
    public int[] rotate(int[] A){
        int last = A[A.length-1];
        for(int i = A.length -1; i >0; i --){
            A[i] = A[i-1];
        }
        A[0] = last;
        return A;
    }
}
