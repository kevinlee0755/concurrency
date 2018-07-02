package com.concurrency.core;

import com.concurrency.task.Job3;
import com.concurrency.task.PrintQueue3;

public class Main302 {
    public static void main(String[] args) {
        // 创建一个打印队列对象
        PrintQueue3 printQueue = new PrintQueue3();

        // 创建10个线程
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job3(printQueue), "Thread " + i);
        }

        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }
}
