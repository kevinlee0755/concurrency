package com.concurrency.task;

import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * �Զ��������̳߳ص�����
 */
public class MyScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {

    /**
     * ���캯��
     *
     * @param corePoolSize �̳߳������ٱ�����������
     */
    public MyScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize);
    }


    /**
     * װ�η�������һ��RunnableScheduledFuture����ת����MyScheduledTask����
     */
    @Override
    protected <V> RunnableScheduledFuture<V> decorateTask(Runnable runnable,
                                                          RunnableScheduledFuture<V> task) {
        MyScheduledTask<V> myTask = new MyScheduledTask<V>(runnable, null, task, this);
        return myTask;
    }


    /**
     * ִ���������ڵ��ȵķ���
     */
    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command,
                                                  long initialDelay,
                                                  long period,
                                                  TimeUnit unit) {
        // ʹ�ó���ķ���ȥ�������
        ScheduledFuture<?> task = super.scheduleAtFixedRate(command, initialDelay, period, unit);
        MyScheduledTask<?> myTask = (MyScheduledTask<?>) task;
        myTask.setPeriod(TimeUnit.MILLISECONDS.convert(period, unit));
        return task;
    }

}
