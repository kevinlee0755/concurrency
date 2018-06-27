package com.concurrency.task;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * �Զ���������ͬ����
 */
public class MyAbstractQueuedSynchronizer extends AbstractQueuedSynchronizer {

    private static final long serialVersionUID = 1L;

    // ԭ�ӱ������洢����״̬��0�У�1æ
    private AtomicInteger state;

    /**
     * ���캯��
     */
    public MyAbstractQueuedSynchronizer() {
        state = new AtomicInteger(0);
    }

    /**
     * ��ȡ��
     *
     * @param arg ���ڱ������в�ʹ�ã�
     * @return true��ȡ��falseδ��ȡ
     */
    @Override
    protected boolean tryAcquire(int arg) {
        return state.compareAndSet(0, 1);
    }

    /**
     * �ͷ���
     *
     * @param arg �ͷ������������в�ʹ�ã�
     * @return true�ɹ���falseʧ��
     */
    @Override
    protected boolean tryRelease(int arg) {
        return state.compareAndSet(1, 0);
    }
}
