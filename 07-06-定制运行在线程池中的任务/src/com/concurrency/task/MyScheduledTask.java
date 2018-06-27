package com.concurrency.task;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * �Զ�������߶��࣬����һ������������V
 *
 * @param <V>
 */
public class MyScheduledTask<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {

    // ���ڴ洢�ɵ��ȵ��������
    private RunnableScheduledFuture<V> task;

    // �ɵ��ȵ��̳߳�ִ����
    private ScheduledThreadPoolExecutor executor;

    // ��������ִ�е�ʱ����
    private long period;

    // ����ʼִ�е�ʱ��
    private long startDate;

    /**
     * ���캯��
     *
     * @param runnable �����ύ�Ŀ�ִ�е��������
     * @param result   ���񷵻صĽ��
     * @param task     ִ��runnable���������
     * @param executor ִ��task�����ִ����
     */
    public MyScheduledTask(Runnable runnable, V result, RunnableScheduledFuture<V> task, ScheduledThreadPoolExecutor executor) {
        super(runnable, result);
        this.task = task;
        this.executor = executor;
    }

    /**
     * ������һ��Ҫִ�е�ʣ��ʱ�䣬�����ӳ�����ͷ������������ӳ�ʱ�䣬
     * ������������񣬷��ؿ�ʼʱ��͵�ǰʱ��Ĳ�ֵ
     *
     * @param unit �ӳٵ�ʱ�䵥λ
     */
    @Override
    public long getDelay(TimeUnit unit) {
        if (!isPeriodic()) {
            return task.getDelay(unit);
        } else {
            if (startDate == 0) {
                return task.getDelay(unit);
            } else {
                Date now = new Date();
                long delay = startDate - now.getTime();
                return unit.convert(delay, TimeUnit.MILLISECONDS);
            }
        }
    }

    /**
     * �ȽϷ���
     */
    @Override
    public int compareTo(Delayed o) {
        return task.compareTo(o);
    }

    /**
     * �ж��Ƿ�����������
     */
    @Override
    public boolean isPeriodic() {
        return task.isPeriodic();
    }


    /**
     * ������
     */
    @Override
    public void run() {
        // ������������񣬲���ִ����û�йر�
        if (isPeriodic() && (!executor.isShutdown())) {
            // ���¿�ʼʱ�䣬ͬʱ���������ٴ����
            Date now = new Date();
            startDate = now.getTime() + period;
            executor.getQueue().add(this);
        }

        // ������������Ϣ
        System.out.printf("Pre-MyScheduledTask: %s\n", new Date());
        System.out.printf("MyScheduledTask: Is Periodic: %s\n", isPeriodic());
        super.runAndReset();
        System.out.printf("Post-MyScheduledTask: %s\n", new Date());
    }

    /**
     * �������������ʱ����
     *
     * @param period ʱ����
     */
    public void setPeriod(long period) {
        this.period = period;
    }
}
