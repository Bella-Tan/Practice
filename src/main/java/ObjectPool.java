import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

class ObjectPool<T, R> {
    final List<T> pool;
    final Semaphore sem;
    ObjectPool(int size, T t){
        pool = new Vector<T>(){};
        for(int i=0; i<size; i++){
            pool.add(t);
        }
        sem = new Semaphore(size);
    }

    R exec(Function<T,R> func) throws InterruptedException {
        T t = null;
        sem.acquire();
        try {
            t = pool.remove(0);
            return func.apply(t);
        } finally {
            pool.add(t);
            sem.release();
        }
    }

    public void main(String[] args) throws InterruptedException {
//        ObjectPool<Integer, String> pool = new ObjectPool<Integer, String>(10,"234");
//        pool.exec(t -> {
//            System.out.println(t);
//            return t.toString();
//        });
    }
}

