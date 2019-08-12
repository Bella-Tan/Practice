import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MapReduceTest {

    public static void main(String[] args) {
        String[] fc = {"hello world",
            "hello me",
            "hello fork",
            "hello join",
            "fork join in world"};

        ForkJoinPool fjp = new ForkJoinPool(3);
        MR mr = new MR(fc, 0, fc.length);
        Map<String, Long> result = fjp.invoke(mr);
        result.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    static class MR extends RecursiveTask<Map<String, Long>> {

        final private String[] fc;
        final private int start, end;

        MR(String[] fc, int start, int to) {
            this.fc = fc;
            this.start = start;
            this.end = to;
        }

        @Override
        protected Map<String, Long> compute() {
            System.out.println(end);
            if (end - start == 1) {
                return calc(fc[start]);
            } else {
                int mid = (start + end) / 2;
                MR mr1 = new MR(fc, start, mid);
                mr1.fork();
                MR mr2 = new MR(fc, mid, end);
                return merge(mr2.compute(),
                    mr1.join());
            }
        }

        private Map<String, Long> merge(
            Map<String, Long> r1,
            Map<String, Long> r2) {
            Map<String, Long> result = new HashMap<>();
            result.putAll(r1);
            r2.forEach((k, v) -> {
                Long c = result.get(k);
                if (c != null) {
                    result.put(k, c + v);
                } else {
                    result.put(k, v);
                }
            });
            return result;
        }

        private Map<String, Long> calc(String line) {
            Map<String, Long> result = new HashMap<>();
            String[] words = line.split("\\s+");
            for (String w : words) {
                Long v = result.get(w);
                if (v != null) {
                    result.put(w, v + 1);
                } else {
                    result.put(w, 1L);
                }
            }
            return result;
        }
    }

}
