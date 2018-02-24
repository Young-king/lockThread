package main.com.xjb.thread;


import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xujinbing
 * @date 2018/2/21/02111:01
 */
public class WorkQueue<T> {
    private final T[] iteam;

    private final Lock lock = new ReentrantLock();

    private final Condition unEmpty = lock.newCondition();

    private final Condition isFull = lock.newCondition();


    private int head;

    private int tail;

    private int count;

    public WorkQueue(int maxCount) {
        iteam = (T[]) new Object[maxCount];
    }


    private void put(T model){
        lock.lock();
     //   if ()


    }




    /**
     * 逐步拿出数据
     * @return
     * @throws InterruptedException
     */
    private T take() throws InterruptedException {
        lock.lock();
        if (count == 0) {
            unEmpty.await();
        }

        T model = iteam[head];
        iteam[head] = null;

        if (++head == iteam.length) {
            head = 0;
        }

        --count;
        isFull.signalAll();
        return model;
    }


}
