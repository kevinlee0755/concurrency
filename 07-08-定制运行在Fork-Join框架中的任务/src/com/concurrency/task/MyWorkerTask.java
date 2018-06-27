package com.concurrency.task;

import java.util.Date;
import java.util.concurrent.ForkJoinTask;

/**
 * �Զ��幤�������޷���ֵ
 */
public abstract class MyWorkerTask extends ForkJoinTask<Void> {

    private static final long serialVersionUID = 1L;

    // ��������
    private String name;

    /**
     * ���캯������ʼ������
     *
     * @param name ��������
     */
    public MyWorkerTask(String name) {
        this.name = name;
    }

    /**
     * ��ȡִ�н������������ִ���޽�����أ�
     */
    @Override
    public Void getRawResult() {
        return null;
    }

    /**
     * ���ý��
     */
    @Override
    protected void setRawResult(Void value) {

    }

    /**
     * ��������ִ������
     */
    @Override
    protected boolean exec() {
        Date startDate = new Date();
        compute();
        Date finishDate = new Date();
        long diff = finishDate.getTime() - startDate.getTime();
        System.out.printf("MyWorkerTask: %s : %d Milliseconds to complete.\n", name, diff);
        return true;
    }

    /**
     * ��ȡ���������
     *
     * @return ���������
     */
    public String getName() {
        return name;
    }

    /**
     * ģ�巽�����ȴ�����ʵ��
     */
    protected abstract void compute();
}
