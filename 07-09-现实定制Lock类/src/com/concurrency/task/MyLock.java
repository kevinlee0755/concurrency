package com.concurrency.task;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * �Զ�����ʵ����
 */
public class MyLock implements Lock {

    /**
     * ����ʵ������ͬ����
     */
    private AbstractQueuedSynchronizer sync;

    /**
     * ���캯������ʼ������
     */
    public MyLock() {
        sync = new MyAbstractQueuedSynchronizer();
    }

    /**
     * ��ȡ��
     */
    @Override
    public void lock() {
        sync.acquire(1);
    }

    /**
     * ��ȡ���������ȡ�����̻߳����������ͷţ��������߳̿��Ա��ж�
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    /**
     * ��ȡ���������ȡ���ͷ���true�������ȡ�����ͷ���false
     */
    @Override
    public boolean tryLock() {
        try {
            return sync.tryAcquireNanos(1, 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ��ָ����ʱ����ȡ���������ȡ���ͷ���true������ͷ���false
     *
     * @param time ʱ����
     * @param unit ʱ�䵥λ
     * @return �����ȡ���ͷ���true������ͷ���false
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, TimeUnit.NANOSECONDS.convert(time, unit));
    }

    /**
     * �ͷ���
     */
    @Override
    public void unlock() {
        sync.release(1);
    }

    /**
     * ����һ���µ���������
     */
    @Override
    public Condition newCondition() {
        return sync.new ConditionObject();
    }

}
