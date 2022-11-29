public class AddByTwoThreadTest {
    private static long count = 0;
    private void add10K() {
        int idx = 0;
        while(idx++ < 10000) {
            count += 1;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        final AddByTwoThreadTest test = new AddByTwoThreadTest();
        Thread th1 = new Thread(()->test.add10K());
        Thread th2 = new Thread(()-> test.add10K());
        Thread th3 = new Thread(()-> test.add10K());
        th1.start();
        th2.start();
        th3.start();

        th1.join();
        th2.join();
        th3.join();
        System.out.println("result of add 30k times by three threads: " + count);
    }
}
