package com.concurrency.task;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory;

/**
 * �Զ���Fork/Join�����̹߳���
 */
public class MyWorkerThreadFactory implements ForkJoinWorkerThreadFactory {

    /**
     * ΪFork/Join��ܴ���һ�������߳�
     *
     * @param pool �߳̽�Ҫ��ִ�е��̳߳�
     * @return һ���Զ���Ĺ����̶߳���
     */
    @Override
    public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
        return new MyWorkerThread(pool);
    }

}
