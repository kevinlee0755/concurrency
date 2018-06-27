package com.concurrency.core;

import com.concurrency.task.MyScheduledThreadPoolExecutor;
import com.concurrency.task.Task;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {

        // ����һ���Զ���ĵ����̳߳�ִ�������
        MyScheduledThreadPoolExecutor executor = new MyScheduledThreadPoolExecutor(2);

        // ����һ������
        Task task = new Task();

        // �����ʼִ�е�ʱ��
        System.out.printf("Main: %s\n", new Date());

        // ��ִ�����з���һ����������һ���ִ��
        executor.schedule(task, 1, TimeUnit.SECONDS);

        // ���߳�����3��
        TimeUnit.SECONDS.sleep(3);

        // ����һ������
        task = new Task();

        // �����ʼִ�е�ʱ��
        System.out.printf("Main: %s\n", new Date());

        // ��һ������ִ����������һ���ִ�У����ҷ�ÿ������ִ��һ��
        executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);

        // ���߳�����10��
        TimeUnit.SECONDS.sleep(10);

        // �ر�ִ����
        executor.shutdown();

        // ����ִ�������н���
        executor.awaitTermination(1, TimeUnit.DAYS);

        // �����Ϣ��֪ͨ������н���
        System.out.printf("Main: End of the program.\n");
    }

}
