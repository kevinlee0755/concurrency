package com.concurrency.task;

public class Calculator3 implements Runnable{
    private int number;

    public Calculator3(int number) {
        this.number = number;
    }

    @Override
    public void run() {
    	System.out.println(Thread.currentThread().getName()+ "task run");
        // 指定的数字进行乘法表运算。
//        for (int i = 1; i <= 10; i++) {
//            System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(), number, i, number * i);
//        }
    }
}
