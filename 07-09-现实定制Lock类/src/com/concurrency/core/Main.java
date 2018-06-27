package com.concurrency.core;

import com.concurrency.task.MyLock;
import com.concurrency.task.Task;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        // ����һ���Զ����������
        MyLock lock = new MyLock();

        // ����10�������������
        for (int i = 0; i < 10; i++) {
            Task task = new Task("Task-" + i, lock);
            Thread thread = new Thread(task);
            thread.start();
        }

        // ���߳���ͼ��ȡ��
        boolean value;
        do {
            try {
                value = lock.tryLock(1, TimeUnit.SECONDS);
                if (!value) {
                    System.out.printf("Main: Trying to get the Lock\n");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                value = false;
            }
        } while (!value);

        // ���߳��ͷ���
        System.out.printf("Main: Got the lock\n");
        lock.unlock();

        // �����Ϣ�������������н���
        System.out.printf("Main: End of the program\n");
    }

}
