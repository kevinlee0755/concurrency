package com.concurrency.core;

import com.concurrency.task.Consumer8;
import com.concurrency.task.Producer8;
import com.concurrency.utils.Buffer;
import com.concurrency.utils.FileMock;

public class Main208 {
    public static void main(String[] args) {
        // 创建一个文件模拟对象，它有101行
        FileMock mock = new FileMock(101, 10);

        // 创建一个缓冲对象，最多可以缓存20行数据
        Buffer buffer = new Buffer(20);

        // 创建一个生产者对象，并且让它在一个单独的线程中运行
        Producer8 producer = new Producer8(mock, buffer);
        Thread threadProducer = new Thread(producer, "Producer");

        // 创建三个消费者对象，并且个他们分别在不同的线程中运行
        Consumer8 consumers[] = new Consumer8[3];
        Thread threadConsumers[] = new Thread[3];

        for (int i = 0; i < 3; i++) {
            consumers[i] = new Consumer8(buffer);
            threadConsumers[i] = new Thread(consumers[i], "Consumer " + i);
        }

        // 启动生产者和消费者线程
        threadProducer.start();
        for (int i = 0; i < 3; i++) {
            threadConsumers[i].start();
        }
    }
}
