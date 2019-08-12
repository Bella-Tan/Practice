class Proxy {

    volatile boolean terminated = false;   //valatile 修饰被多个线程访问的变量，保证可见行
    boolean started = false;  //start变量只在同一个线程的synchronized方法中被读写，不需要volatile修饰
    Thread rptThread;

    synchronized void start() {
        if (started) {
            return;
        }
        started = true;
        terminated = false;
        rptThread = new Thread(() -> {
            while (!terminated) {
                // do something
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            started = false; //在rptThread也被修改
        });
        rptThread.start();
    }

    synchronized void stop() {
        terminated = true;
        rptThread.interrupt();
    }
}
