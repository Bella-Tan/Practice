package leetcode1115;

public class Demo {
    public static void main(String[] args) {
        FooBar f = new FooBar(5);

        Thread t1 = new Thread(() -> {
            try {
                f.foo(() -> System.out.println("Foo"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                f.bar(() ->System.out.println("Bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t2.start();
        t1.start();
    }
}
