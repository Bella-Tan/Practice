import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EagerThreadPoolTesgt {
    public static void main(String[] args){
        ExecutorService pool = Executors
            .newSingleThreadExecutor();
        pool.submit(() -> {
            try {
                String qq=pool.submit(()->"QQ").get();
                System.out.println(qq);
            } catch (Exception e) {
            }
        });

    }
}
