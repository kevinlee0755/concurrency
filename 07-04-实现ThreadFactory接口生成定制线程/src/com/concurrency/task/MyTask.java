package com.concurrency.task;

import java.util.concurrent.TimeUnit;


/**
 * �Զ����������
 */
public class MyTask implements Runnable {

	/**
	 * ������������������
	 */
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
