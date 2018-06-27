package com.concurrency.core;

import com.concurrency.task.MyTask;
import com.concurrency.task.MyThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws Exception {
		
		// ����һ���Զ�����̹߳�����
		MyThreadFactory threadFactory=new MyThreadFactory("MyThreadFactory");
		
		// ����һ���̻߳����ִ�����������Ĳ������̹߳�����
		ExecutorService executor=Executors.newCachedThreadPool(threadFactory);
		
		// ����һ���Զ�������
		MyTask task=new MyTask();
		
		// �����ύ��ִ����
		executor.submit(task);
		
		// �ر�ִ��������
		executor.shutdown();
		
		// �ȴ�ִ�����е��������н���
		executor.awaitTermination(1, TimeUnit.DAYS);
		
		// �����Ϣ���������Ѿ�����
		System.out.printf("Main: End of the program.\n");
	}
}
