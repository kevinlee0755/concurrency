package com.concurrency.task;

import java.util.concurrent.Callable;

public class Task implements Callable<String> {
    /**
     * 核心方法，一个无限循环的任务，每100毫秒向控制台写一个消息
     * @return
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        while (true){
            System.out.printf("Task: Test\n");
            Thread.sleep(100);
        }
    }
}
