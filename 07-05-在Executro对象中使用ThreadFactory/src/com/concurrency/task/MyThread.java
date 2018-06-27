package com.concurrency.task;

import java.util.Date;

/**
 * �Զ����߳���
 */
public class MyThread extends Thread {


	// �̴߳�����ʱ��
	private Date creationDate;

	// �߿�ʼִ�е�ʱ��
	private Date startDate;

	// �߳����ִ�е�ʱ��
	private Date finishDate;

	/**
	 * ���캯��
	 *
	 * @param target ִ�е�����
	 * @param name   �̵߳�����
	 */
	public MyThread(Runnable target, String name) {
		super(target, name);
		setCreationDate();
	}

	/**
	 * ����������¼�߳����еĿ�ʼ�ͽ���ʱ��
	 */
	@Override
	public void run() {
		setStartDate();
		super.run();
		setFinishDate();
	}

	/**
	 * �����̴߳�����ʱ��
	 */
	public void setCreationDate() {
		creationDate = new Date();
	}

	/**
	 * �����߳̿�ʼִ�е�ʱ��
	 */
	public void setStartDate() {
		startDate = new Date();
	}

	/**
	 * �����߳̽���ִ�е�ʱ��
	 */
	public void setFinishDate() {
		finishDate = new Date();
	}

	/**
	 * �����߳�ִ�е�ʱ��
	 *
	 * @return �߳�ִ�е�ʱ��
	 */
	public long getExecutionTime() {
		return finishDate.getTime() - startDate.getTime();
	}

	/**
	 * ����߳������Ϣ
	 */
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(getName());
		buffer.append(": ");
		buffer.append(" Creation Date: ");
		buffer.append(creationDate);
		buffer.append(" : Running time: ");
		buffer.append(getExecutionTime());
		buffer.append(" Milliseconds.");
		return buffer.toString();
	}
}
