package lc1114;

public class Demo {
    public static void main(String[] args) {
        Foo f = new Foo();

        Thread t1 = new Thread(() -> {
            try {
                f.one(() -> System.out.println("One"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                f.two(() ->System.out.println("Two"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                f.two(() -> System.out.println("Three"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
        t1.start();
        t3.start();
    }
}
