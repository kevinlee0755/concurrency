package com.concurrency.task;

import java.util.concurrent.TimeUnit;

/**
 * �Զ���������
 */
public class Task implements Runnable {

    // ʹ���Զ���������
    private MyLock lock;

    // ��������
    private String name;

    /**
     * ���캯��
     *
     * @param name ��������
     * @param lock ʹ�õ���
     */
    public Task(String name, MyLock lock) {
        this.lock = lock;
        this.name = name;
    }

    /**
     * �����������������֣���ʵ�������ߣ�
     */
    @Override
    public void run() {
        lock.lock();
        System.out.printf("Task: %s: Take the lock\n", name);
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.printf("Task: %s: Free the lock\n", name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
