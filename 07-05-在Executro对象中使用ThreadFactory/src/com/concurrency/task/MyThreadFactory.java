package com.concurrency.task;

import java.util.concurrent.ThreadFactory;

/**
 * �Զ����̹߳�����
 */
public class MyThreadFactory implements ThreadFactory {

	// ��¼�����е��߳���Ŀ
	private int counter;

	// �������̵߳�����ǰ׺
	private String prefix;

	/**
	 * ���캯��
	 *
	 * @param prefix �������߳�����ǰ׺
	 */
	public MyThreadFactory(String prefix) {
		this.prefix = prefix;
		counter = 1;
	}

	/**
	 * �����̵߳Ĺ���
	 */
	@Override
	public Thread newThread(Runnable r) {
		MyThread myThread = new MyThread(r, prefix + "-" + counter);
		counter++;
		return myThread;
	}
}
