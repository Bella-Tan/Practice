import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class CompletionServiceTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
        completionService.submit(()->3);
        completionService.submit(()->2);
        completionService.submit(()->1);
        AtomicReference<Integer> atomicReference = new AtomicReference<>(Integer.MAX_VALUE);
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for(int i=0;i<3; ++i){
            executorService.execute(()->{
                Integer r = null;
                try {
                    r = completionService.take().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println(r);
                atomicReference.set(Integer.min(atomicReference.get(),r));
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(atomicReference);
    }
}
