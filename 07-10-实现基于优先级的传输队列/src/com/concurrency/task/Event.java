package com.concurrency.task;

/**
 * �Զ����¼�����
 */
public class Event implements Comparable<Event> {

    // �����¼����߳���
    private String thread;

    // �߳����ȼ�
    private int priority;

    /**
     * ���캯��
     *
     * @param thread   �����¼����߳���
     * @param priority �߳����ȼ�
     */
    public Event(String thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    public String getThread() {
        return thread;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Event e) {
        if (this.priority > e.getPriority()) {
            return -1;
        } else if (this.priority < e.getPriority()) {
            return 1;
        } else {
            return 0;
        }
    }
}
