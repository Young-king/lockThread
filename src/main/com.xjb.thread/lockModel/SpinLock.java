package main.com.xjb.thread.lockModel;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xujinbing
 * @date 2018/2/24/02421:35
 * 自旋锁的普通版本
 */
public class SpinLock {


    /**
     * use thread itself as  synchronization state
     * 使用线程本身去做同步的状态
     */
    private AtomicReference<Thread> owner = new AtomicReference<Thread>();

    private int count = 0;

    public void lock() {
        Thread currentThread = Thread.currentThread();
        if (currentThread == owner.get()) {
            ++count;
            return;
        }

        //自旋等待到是null才会跳出自旋
        while (!owner.compareAndSet(null, currentThread)) {

        }

    }


    /**
     * 使用了CAS原子操作，lock函数将owner设置为当前线程，并且预测原来的值为空。unlock函数将owner设置为null，并且预测值为当前线程
     * 当有第二个线程调用lock操作时由于owner值不为空，导致循环一直被执行，直至第一个线程调用unlock函数将owner设置为null，
     * 第二个线程才能进入临界区。
     */
    public void unLock() {

        Thread currentThread = Thread.currentThread();
        /**
         * 不是自己的线程，不可以解锁成功
         */
        if (owner.get() != currentThread) {
            return;
        }

        if (count > 0) {
            --count;
        }
        owner.compareAndSet(currentThread, null);
    }


}
