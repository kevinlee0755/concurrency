package com.concurrency.task;

import java.util.concurrent.TimeUnit;

/**
 * ����ָ�����ȼ�������
 */
public class MyPriorityTask implements Runnable, Comparable<MyPriorityTask> {

    /**
     * �������ȼ�
     */
    private int priority;

    /**
     * ��������
     */
    private String name;

    /**
     * ���캯��
     *
     * @param name     ��������
     * @param priority �������ȼ�
     */
    public MyPriorityTask(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    /**
     * ��ȡ�������ȼ�
     *
     * @return �������ȼ�
     */
    public int getPriority() {
        return priority;
    }

    /**
     * �ȽϷ���������һ�����������бȽ�
     */
    @Override
    public int compareTo(MyPriorityTask o) {
        if (this.getPriority() < o.getPriority()) {
            return 1;
        }

        if (this.getPriority() > o.getPriority()) {
            return -1;
        }

        return 0;
    }

    /**
     * �����������������ӣ���Ϣ�����ӣ�
     */
    @Override
    public void run() {
        System.out.printf("MyPriorityTask: %s Priority : %d\n", name, priority);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
