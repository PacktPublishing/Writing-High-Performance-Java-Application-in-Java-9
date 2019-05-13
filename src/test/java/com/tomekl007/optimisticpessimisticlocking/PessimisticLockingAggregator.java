package com.tomekl007.optimisticpessimisticlocking;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PessimisticLockingAggregator {
    int count = 0;

    public void increment() {
        synchronized (this) {
            count++;
        }
    }

    Lock lock = new ReentrantLock();

    public void increment2() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

}
