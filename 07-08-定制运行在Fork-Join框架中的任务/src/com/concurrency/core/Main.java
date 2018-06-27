package com.concurrency.core;

import com.concurrency.task.Task;

import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) throws Exception {

        int array[] = new int[10000];

        // ����һ��ִ�г�
        ForkJoinPool pool = new ForkJoinPool();

        // ����һ���������
        Task task = new Task("Task", array, 0, array.length);

        // �������ύ��ִ�г�
        pool.invoke(task);

        // �ر�ִ�г�
        pool.shutdown();

        // �����Ϣ����������ִ�н���
        System.out.printf("Main: End of the program.\n");

    }

}
