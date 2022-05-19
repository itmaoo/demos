package mao.demo.calc.binary;

import org.junit.Test;

public class BinaryCase {
    @Test
    public void run(){
        //3 >> 1 = 1
        System.out.println(3>>1);
        //-3>>1 = -2
        // 11111111111111111111111111111101 11111111111111111111111111111110
        System.out.println(Integer.toBinaryString(-1&5));
        System.out.println(Integer.toBinaryString(-2));

        System.out.println(Integer.toBinaryString(-3));
        System.out.println(-3>>1);
        System.out.println(Integer.toBinaryString(-3>>1));

        System.out.println(-1|5);
        System.out.println(Integer.toBinaryString(5));

        System.out.println(Integer.toBinaryString(-1&5));


    }
}
