package chapter10;

import java.util.concurrent.*;

public class ConcurrentTask {
    private final ConcurrentMap<Object, Future<String>> taskCache = new ConcurrentHashMap<>();

    private String executionTask(final String taskName) {
        while (true) {
            Future<String> future = taskCache.get(taskName);
            if (future == null) {
                Callable<String> task = () -> taskName;

                // 1.2 创建任务
                FutureTask<String> futureTask = new FutureTask<>(task);
                future = taskCache.putIfAbsent(taskName, futureTask);
                if (future == null) {
                    future = futureTask;
                    futureTask.run(); // 1.4 执行任务
                }
            }

            try{
                return future.get();    // 1.5, 2.2线程在些等待任务执行完成
            } catch (Exception e) {
                taskCache.remove(taskName, future);
            }
        }
    }
}
