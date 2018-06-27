package com.concurrency.core;

import com.concurrency.task.MyTask;
import com.concurrency.task.MyThreadFactory;

public class Main {

    public static void main(String[] args) throws Exception {
        // ����һ���̹߳���
        MyThreadFactory myFactory = new MyThreadFactory("MyThreadFactory");

        // ����һ������
        MyTask task = new MyTask();

        // ʹ���Զ�����̹߳�������һ���µ��߳�
        Thread thread = myFactory.newThread(task);

        // ��ʼִ���߳�
        thread.start();

        // �ȴ��߳�ִ�н���
        thread.join();

        // ����߳���Ϣ
        System.out.printf("Main: Thread information.\n");
        System.out.printf("%s\n", thread);
        System.out.printf("Main: End of the example.\n");
    }
}
