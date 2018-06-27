package com.concurrency.task;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  �Զ����߳����ȼ���
 *
 * @param <E> ���Ͳ���
 */
public class MyPriorityTransferQueue<E> extends PriorityBlockingQueue<E> implements TransferQueue<E> {

    private static final long serialVersionUID = 1L;

    // �ȴ�����������Ŀ
    private AtomicInteger counter;

    // �洢����Ԫ�صĶ���
    private LinkedBlockingQueue<E> transfered;

    // ������
    private ReentrantLock lock;

    /**
     * ���캯��
     */
    public MyPriorityTransferQueue() {
        counter = new AtomicInteger(0);
        lock = new ReentrantLock();
        transfered = new LinkedBlockingQueue<>();
    }

    /**
     * ��Ԫ�ط��͵�һ�����ڵȴ��������ߣ����û�еȴ��е������ߣ��ͷ���false
     */
    @Override
    public boolean tryTransfer(E e) {
        lock.lock();
        boolean value;
        if (counter.get() == 0) {
            value = false;
        } else {
            put(e);
            value = true;
        }
        lock.unlock();
        return value;
    }

    /**
     * ��Ԫ�ط��͵�һ�����ڵȴ��������ߣ����û�еȴ��е������ߣ���Ԫ�ش洢��transfer�����У�
     * ���ҵȴ�������ͼ��ȡԪ�صĵ�һ�������ߣ�������ǰ�̱߳�����
     */
    @Override
    public void transfer(E e) throws InterruptedException {
        lock.lock();
        if (counter.get() != 0) {
            put(e);
            lock.unlock();
        } else {
            transfered.add(e);
            lock.unlock();
            synchronized (e) {
                e.wait();
            }
        }
    }

    /**
     * ��һ���������Ա�ʾ���������ѵ�Ԫ��, �ڶ���������ʾ���û����������ȴ�һ�������ߵ�ʱ�䣬
     * ������������ʾ�ȴ�ʱ��ĵ� λ��������������ڵȴ���������������Ԫ�ء����򣬽�����ָ����
     * ʱ��ת��Ϊ���벢ʹ ��wait()�������߳����ߡ���������ȡԪ��ʱ������߳�����wait()������
     * ���ߣ���ʹ��notify()����ȥ������
     */
    @Override
    public boolean tryTransfer(E e, long timeout, TimeUnit unit)
            throws InterruptedException {
        lock.lock();
        if (counter.get() != 0) {
            put(e);
            lock.unlock();
            return true;
        } else {
            transfered.add(e);
            long newTimeout = TimeUnit.MILLISECONDS.convert(timeout, unit);
            lock.unlock();
            e.wait(newTimeout);
            lock.lock();
            if (transfered.contains(e)) {
                transfered.remove(e);
                lock.unlock();
                return false;
            } else {
                lock.unlock();
                return true;
            }
        }
    }


    /**
     * ʹ��counter���Ե�ֵ������÷����ķ���ֵ����Ϊ0����true�����򷵻�false
     */
    @Override
    public boolean hasWaitingConsumer() {
        return (counter.get() != 0);
    }

    /**
     * ���� counter ���Ե�ֵ
     */
    @Override
    public int getWaitingConsumerCount() {
        return counter.get();
    }

    /**
     * �����transfered������û��Ԫ�أ����ͷ���������ʹ��toke()�����Ӷ�����ȡ
     * ��һ��Ԫ�ز��ٴλ�ȡ��.���������û��Ԫ�أ��÷��������߳�����ֱ����Ԫ�ؿɱ�����
     */
    @Override
    public E take() throws InterruptedException {
        lock.lock();
        counter.incrementAndGet();
        E value = transfered.poll();
        if (value == null) {
            lock.unlock();
            value = super.take();
            lock.lock();
        } else {
            synchronized (value) {
                value.notify();
            }
        }
        counter.decrementAndGet();
        lock.unlock();
        return value;
    }
}
