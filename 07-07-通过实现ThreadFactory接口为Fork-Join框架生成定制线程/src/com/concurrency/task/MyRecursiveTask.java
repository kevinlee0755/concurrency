package com.concurrency.task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * �Զ���ݹ�������
 */
public class MyRecursiveTask extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 1L;

    // �����������
    private int array[];

    // �������ʼ�ͽ���λ��
    private int start, end;

    /**
     * ���캯������ʼ����������
     *
     * @param array ����������
     * @param start �������ʼλ��
     * @param end   ����Ľ���λ��
     */
    public MyRecursiveTask(int array[], int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    /**
     * ��������������м������Ŀ����100���ͻ���������������ִ��
     */
    @Override
    protected Integer compute() {
        Integer ret;
        MyWorkerThread thread = (MyWorkerThread) Thread.currentThread();
        thread.addTask();
        if (end - start > 100) {
            int mid = (start + end) / 2;
            MyRecursiveTask task1 = new MyRecursiveTask(array, start, mid);
            MyRecursiveTask task2 = new MyRecursiveTask(array, mid, end);
            invokeAll(task1, task2);
            ret = addResults(task1, task2);
        } else {
            int add = 0;
            for (int i = start; i < end; i++) {
                add += array[i];
            }
            ret = new Integer(add);
        }
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * �鲢����������Ľ��
     *
     * @param task1 First task
     * @param task2 Second task
     * @return The sum of the results of the two tasks
     */
    private Integer addResults(MyRecursiveTask task1, MyRecursiveTask task2) {
        int value;
        try {
            value = task1.get().intValue() + task2.get().intValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
            value = 0;
        } catch (ExecutionException e) {
            e.printStackTrace();
            value = 0;
        }
        return new Integer(value);
    }

}
