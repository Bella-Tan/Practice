import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {
    public static void main(String[] args){
        ForkJoinPool fjp = new ForkJoinPool(4);
        Fibonacci fib = new Fibonacci(30);
        Integer result = fjp.invoke(fib);
        System.out.println(result);
    }

    static class Fibonacci extends RecursiveTask<Integer> {
        final int n;
        Fibonacci(int n){this.n = n;}
        protected Integer compute(){
            if (n <= 1)
                return n;
            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            int result = f2.compute() + f1.join();
            System.out.println(result);
            return result;
        }
    }
}
