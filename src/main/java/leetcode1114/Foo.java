package leetcode1114;

import java.util.concurrent.CountDownLatch;

class Foo {

    private CountDownLatch l1;
    private CountDownLatch l2;

    public Foo() {
        l1 = new CountDownLatch(1);
        l2 = new CountDownLatch(1);
    }

    public void one(Runnable printFirst) {
        printFirst.run();
        l1.countDown();
    }

    public void two(Runnable printSecond) throws InterruptedException {

        l1.await();
        printSecond.run();
        l2.countDown();
    }

    public void three(Runnable printThird) throws InterruptedException {

        l2.await();

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
