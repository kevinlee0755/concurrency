package com.concurrency.core;

import com.concurrency.task.MyRecursiveTask;
import com.concurrency.task.MyWorkerThreadFactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {

		// ����һ�������̹߳���
        MyWorkerThreadFactory factory = new MyWorkerThreadFactory();

		// ����һ��Fork/Join��
        ForkJoinPool pool = new ForkJoinPool(4, factory, null, false);

		// ��ʼ��һ�������������
        int array[] = new int[100000];

        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }

		// ����һ��ִ�м�����������
        MyRecursiveTask task = new MyRecursiveTask(array, 0, array.length);

		// �����ύ��fork/join����
        pool.execute(task);

        // ��������ִ����
        task.join();

		// �ر�Fork/Join��
        pool.shutdown();

		// �������е������������н���
        pool.awaitTermination(1, TimeUnit.DAYS);

		// �������ִ�еĽ��
        System.out.printf("Main: Result: %d\n", task.get());

		// �����Ϣ��ʾ�������н���
        System.out.printf("Main: End of the program\n");
    }

}
