package mao.demo.test.recuit.binaryGap;

import org.junit.Test;

/**
 * A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones at both ends in the binary representation of N.
 * For example, number 9 has binary representation 1001 and contains a binary gap of length 2. The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3. The number 20 has binary representation 10100 and contains one binary gap of length 1. The number 15 has binary representation 1111 and has no binary gaps. The number 32 has binary representation 100000 and has no binary gaps.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int N); }
 * <p>
 * that, given a positive integer N, returns the length of its longest binary gap. The function should return 0 if N doesn't contain a binary gap.
 * <p>
 * For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5. Given N = 32 the function should return 0, because N has binary representation '100000' and thus no binary gaps.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [1..2,147,483,647].
 */
public class BinaryGap4 {

    @Test
    public void runCase() {
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE)+"="+Integer.MIN_VALUE);
        System.out.println(Integer.toBinaryString(0));
        System.out.println(Integer.toBinaryString(-0));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-2));
        System.out.println(Integer.toBinaryString(-4));
        System.out.println(Integer.toBinaryString(-5));
        System.out.println(Integer.toBinaryString(74901729));
        toString(74901729, new StringBuffer());
        System.out.println(solution(74901729));

    }

    public int solution(int N) {
        // write your code in Java SE 8
        return calc(N, 0, 0, false);
    }

    public static int calc(int n, int current0, int max, boolean startCount) {
        if (n == 1) {
            if (current0 > 0) {
                if (max < current0) {
                    max = current0;
                }
            }
            return max;
        } else if (n == 0) {
            return max;
        }

        int v1 = n & 1;
        if (v1 == 1) {
            if (current0 > max) {
                max = current0;
            }
            startCount = true;
            current0 = 0;
        } else if (startCount) {
            current0++;
        }
        int v2 = n >>>= 1;
        return calc(v2, current0, max, startCount);
    }

    public String toString(int N, StringBuffer result) {
        if (N == 1 || N == 0) {
            result.insert(0, N);
            System.out.println(result.toString());
            return result.toString();
        }
        int v1 = N % 2;
        result.insert(0, v1);
        int V2 = N / 2;
        return toString(V2, result);

    }
}
