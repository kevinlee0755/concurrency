package com.concurrency.core;

import com.concurrency.task.Job1;
import com.concurrency.task.PrintQueue1;

public class Main207 {
    public static void main(String[] args) {
        // 创建一个打印队列
        PrintQueue1 printQueue = new PrintQueue1();

        // 创建10个打印任务并且将其放入到不同的线程中运行
        Thread thread[] = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new Job1(printQueue), "Thread " + i);
        }

        // 每隔0.1s运行一个线程，一个10个线程
        for (int i = 0; i < 10; i++) {
            thread[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
