package lc1115;

import java.util.concurrent.Semaphore;

class FooBar {

    private int n;
    private final Semaphore semaphoreFoo = new Semaphore(0);
    private final Semaphore semaphoreBar = new Semaphore(0);
    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            semaphoreBar.release();
            semaphoreFoo.acquire();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            semaphoreBar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            semaphoreFoo.release();
        }
    }
}
