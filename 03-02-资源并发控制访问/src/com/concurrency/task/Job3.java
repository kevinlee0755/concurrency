package com.concurrency.task;

public class Job3 implements Runnable {
    /**
     * 打印队列对象
     */
    private PrintQueue3 printQueue;

    /**
     * 构造函数，初始化打印队列对象
     *
     * @param printQueue 打印队列对象
     */
    public Job3(PrintQueue3 printQueue) {
        this.printQueue = printQueue;
    }

    /**
     * 核心方法，向打印队列中发送打印任务，并且等待它完成
     */
    @Override
    public void run() {
        System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
    }
}
