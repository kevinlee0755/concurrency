package com.concurrency.task;

/**
 * ��������
 */
public class Producer implements Runnable {

    // // �����洢����������ɵ��¼�
    private MyPriorityTransferQueue<Event> buffer;

    public Producer(MyPriorityTransferQueue<Event> buffer) {
        this.buffer = buffer;
    }

    /**
     * ����100��Event����ʹ�ô��������Ϊ���ȼ����¼��� ����Խ�����ȼ�Խ��)��
     * ��ʹ��put()���������ǲ��뵽�����С�
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Event event = new Event(Thread.currentThread().getName(), i);
            buffer.put(event);
        }
    }
}
