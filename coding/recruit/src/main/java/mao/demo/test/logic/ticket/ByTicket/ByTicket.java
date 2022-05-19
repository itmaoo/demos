package mao.demo.test.logic.ticket.ByTicket;

import org.junit.Test;

public class ByTicket {
    @Test
    public void runCase() {
        System.out.println(solution(new int[]{1, 2,3,4,29}));
    }
    volatile Integer min = -1;

    public int solution(int[] A) {
        // write your code in Java SE 8
       // List<Integer> min = new ArrayList();

      //  calc(A, min, 0, 0, 0);
        calc(1);
        return  min;
    }

    public void calc(Integer min) {
        min = 1;
    }
        public void calc(int[] A, int min, int currentSum, int index, int count) {
        if (count > A.length) {
            if(min== -1){
                min = currentSum;
                return;
            }
        }
        if (index == A.length) {
            if(min == -1||min > currentSum){
                min = currentSum;
            }
            System.out.println("min:"+min );
        } else if(index < A.length){
            count++;
            //cost for one day
            int currentSum1 = currentSum + 2;
            calc(A, min, currentSum1, index + 1, count);
            //cost for 7 days
            int currentSum2 = currentSum + 7;
            int nextIndex2 = calcNextIndex(A, index, 7);
            calc(A, min, currentSum2, nextIndex2, count);

            //cost for 30 days
            int currentSum3 = currentSum + 25;
            int nextIndex3 = calcNextIndex(A, index, 30);

            calc(A, min, currentSum3, nextIndex3, count);

        }
    }

    private int calcNextIndex(int[] A, int index, int step) {
        for (int i = index + 1; i < A.length; i++) {
            if (A[i] >= A[index] + step) {
                return i;
            }
        }
        return 0;
    }

}
