package com.concurrency.task;

/**
* �����࣬ģ��ͣ������
 */
public class Sensor2 implements Runnable {

	// ͣ����������
	private ParkingCounter counter;

	/**
	 * ���캯��
	 *
	 * @param counter ͣ����������
	 */
	public Sensor2(ParkingCounter counter) {
		this.counter=counter;
	}
	
	/**
	 * ������������ͣ��������ģ��
	 */
	@Override
	public void run() {
		counter.carIn();
		counter.carOut();
		counter.carOut();
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carIn();
	}

}
