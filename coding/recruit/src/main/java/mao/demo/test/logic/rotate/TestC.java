package mao.demo.test.logic.rotate;

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");
/**
 * 找出剔除一个元素后，所有元素是一高一低的序列
 */

import org.junit.Test;

public class TestC {

    @Test
    public void runCase(){
        System.out.println(solution(new int[]{3, 4, 5, 3, 7}));
    }
    public int solution(int[] A) {
        // write your code in Java SE 8
        if(isAesth(A)){
            return 0;
        }
        int count = 0;
        int[] B = new int[A.length-1];
        for(int i= 0; i< A.length; i++){
            for(int j= 0; j< i; j++){
                B[j]=A[j];
            }
            for(int h= i+1; h < A.length; h++){
                B[h-1]=A[h];
            }
            if(isAesth(B)){
                count ++;
            }
        }
        if(count==0){
            return -1;
        }
        return count;
    }
    public boolean isAesth(int[] A){
        if(A[0] == A[1]){
            return false;
        }
        boolean previous2FirstOneBigger = A[0]>A[1];
        for(int i= 1; i< A.length-1; i++){
            if(A[i] == A[i+1]){
                return false;
            }
            boolean current2FirstOneBigger = A[i] > A[i+1];
            if(current2FirstOneBigger == previous2FirstOneBigger){
                return false;
            }
            previous2FirstOneBigger = current2FirstOneBigger;
        }
        return true;
    }
}

