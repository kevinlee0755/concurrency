package com.concurrency.task;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * �Զ��幤���߳�
 */
public class MyWorkerThread extends ForkJoinWorkerThread {

    // ÿ���߳���ִ�е�������
    private static ThreadLocal<Integer> taskCounter = new ThreadLocal<>();

    /**
     * ���캯��
     *
     * @param pool �ֺϳض���
     */
    protected MyWorkerThread(ForkJoinPool pool) {
        super(pool);
    }

    /**
     * ��һ��Fork/Join��ܵ��߳̿�ʼִ�������ʱ����е��ã�����ʼ�����������
     */
    @Override
    protected void onStart() {
        super.onStart();
        System.out.printf("MyWorkerThread %d: Initializing task counter.\n", getId());
        taskCounter.set(0);
    }

    /**
     * ��һ��Fork/Join��ܵ��߳̽���ִ�������ʱ����е��ã�����߳�ִ�е�������
     */
    @Override
    protected void onTermination(Throwable exception) {
        System.out.printf("MyWorkerThread %d: %d\n", getId(), taskCounter.get());
        super.onTermination(exception);
    }

    /**
     * �����������
     */
    public void addTask() {
        int counter = taskCounter.get().intValue();
        counter++;
        taskCounter.set(counter);
    }
}
