package com.concurrency.task;

/**
 * �Զ���������
 */
public class Task extends MyWorkerTask {
    private static final long serialVersionUID = 1L;

    // ����Ҫ���������
    private int array[];

    // ��ʼ�������λ��
    private int start;

    // ���������λ�ã���������
    private int end;

    /**
     * ���캯������ʼ������
     *
     * @param name  ���������
     * @param array ���������
     * @param start ��ʼ�����λ��
     * @param end   ���������λ��
     */
    public Task(String name, int array[], int start, int end) {
        super(name);
        this.array = array;
        this.start = start;
        this.end = end;
    }

    /**
     * ���÷��������Ҫ�����Ԫ�ظ�¦����100���ͷֳ�������������д���
     */
    @Override
    protected void compute() {
        if (end - start > 100) {
            int mid = (end + start) / 2;
            Task task1 = new Task(this.getName() + "1", array, start, mid);
            Task task2 = new Task(this.getName() + "2", array, mid, end);
            invokeAll(task1, task2);
        } else {
            for (int i = start; i < end; i++) {
                array[i]++;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
