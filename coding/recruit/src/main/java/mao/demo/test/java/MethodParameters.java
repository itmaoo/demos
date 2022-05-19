package mao.demo.test.java;

import org.junit.Test;

/**
 * 当形参和实参不是指针类型时，在该函数运行时，形参和实参是不同的变量，他们在内存中位于不同的位置，形参将实参的内容复制一份，在该函数运行结束的时候形参被释放，而实参内容不会改变。
 *  如果函数的参数是指针类型变量,在调用该函数的过程中，传给函数的是实参的地址，在函数体内部使用的也是实参的地址，即使用的就是实参本身。所以在函数体内部可以改变实参的值。
 */
public class MethodParameters {
    @Test
    public void runCase() {
         solution();
    }
    volatile Integer min = -1;

    public void solution() {
        // write your code in Java SE 8
        // List<Integer> min = new ArrayList();

        //  calc(A, min, 0, 0, 0);
        Pojo p = new Pojo();
        calc(p);
    }

    public void calc(Pojo p) {
        p.setAge(1);
        p.setAge2(2);
        p.setName("dfg");
    }
}
