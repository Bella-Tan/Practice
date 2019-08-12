import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTest {

    static class Task1 implements Callable<String> {
        @Override
        public String call() throws InterruptedException {
            System.out.println("T1: 洗茶壶...");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T1: 洗茶杯...");
            TimeUnit.SECONDS.sleep(2);

            System.out.println("T1: 拿茶叶...");
            TimeUnit.SECONDS.sleep(1);
            return " 龙井 ";
        }
    }

    static class Task2 implements Callable<String> {
        FutureTask<String> futureTask;
        Task2(FutureTask<String> futureTask){
            this.futureTask = futureTask;
        }

        @Override
        public String call() throws ExecutionException, InterruptedException {
            System.out.println("T2: 烧开水...");
            TimeUnit.SECONDS.sleep(1);
            String result = futureTask.get();
            System.out.println("T2: 取到茶叶：" + result);
            System.out.println("T2: 泡茶...");
            TimeUnit.SECONDS.sleep(1);
            return "上茶 - " + result;
        }
    }
    static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        }catch(InterruptedException e){}
    }

    public static void main(String[] args){
        CompletableFuture<Void> f1 =
            CompletableFuture.runAsync(()->{
                System.out.println("T1: 洗水壶...");
                sleep(1, TimeUnit.SECONDS);

                System.out.println("T1: 烧开水...");
                sleep(15, TimeUnit.SECONDS);
            });
        CompletableFuture<String> f2 =
            CompletableFuture.supplyAsync(()->{
                System.out.println("T2: 洗茶壶...");
                sleep(1, TimeUnit.SECONDS);

                System.out.println("T2: 洗茶杯...");
                sleep(2, TimeUnit.SECONDS);

                System.out.println("T2: 拿茶叶...");
                sleep(1, TimeUnit.SECONDS);
                return " 龙井 ";
            });
        CompletableFuture<String> f3 =
            f1.thenCombine(f2, (__, tf)->{
                System.out.println("T3: 拿到茶叶:" + tf);
                System.out.println("T3: 泡茶...");
                return " 上茶:" + tf;
            });
        System.out.println(f3.join());

    }

}