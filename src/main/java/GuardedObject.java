import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

class GuardedObject<T> {

    T obj;
    final Lock lock = new ReentrantLock();
    final Condition done = lock.newCondition();
    final int timeout = 2;
    final static Map<Object, GuardedObject> gos = new ConcurrentHashMap<>();

    static <K> GuardedObject create(K key) {
        GuardedObject go = new GuardedObject();
        gos.put(key, go);
        return go;
    }

    static <K, T> void fireEvent(K key, T obj) {
        GuardedObject go = gos.remove(key);
        if (go != null) {
            go.onChanged(obj);
        }
    }

    T get(Predicate<T> p) {
        lock.lock();
        try {
            //MESA 管程推荐写法
            while (!p.test(obj)) {
                done.await(timeout,
                    TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        return obj;
    }

    void onChanged(T obj) {
        lock.lock();
        try {
            this.obj = obj;
            done.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
