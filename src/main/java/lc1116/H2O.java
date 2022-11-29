package lc1116;

import java.util.concurrent.Semaphore;

class H2O {

    private Semaphore s1; /**/
    private Semaphore s2;
    private Semaphore s3;
    private Semaphore s4;

    /*关键是互相等待互相通知*/
    H2O() {
        s1 = new Semaphore(2);
        s2 = new Semaphore(1);
        s3 = new Semaphore(0);
        s4 = new Semaphore(0);
    }

     void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        s1.acquire();
        s3.release(); /*每次release H时，H的等待信号量release一次*/
        s4.acquire();
        releaseHydrogen.run();
        s1.release();
    }

     void oxygen(Runnable releaseOxygen) throws InterruptedException {
        s2.acquire();
        s4.release(2);
        s3.acquire(2);
        releaseOxygen.run(); /*release O的条件是，有两个H release*/
//         System.out.println();
        s2.release();
    }
}
