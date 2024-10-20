package org.dhltest.framework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.runners.Parameterized;
import org.junit.runners.model.RunnerScheduler;

/**
 * Allows running junit tests in parallel Creates parallel threads
 */
public class Parallelized extends Parameterized {

    public Parallelized(Class<?> klass) throws Throwable {
        super(klass);
        setScheduler(new ThreadPoolScheduler());
    }

    private static class ThreadPoolScheduler implements RunnerScheduler {
        private final ExecutorService executor;

        ThreadPoolScheduler() {
            executor = Executors.newFixedThreadPool(TestSettings.getInstance().getParallelThreads());
        }

        @Override
        public void finished() {
            executor.shutdown();
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException exc) {
                Thread.currentThread().interrupt();
                throw new ActionWasNotDoneException("Parallelized error", exc);
            }
        }

        @Override
        public void schedule(Runnable childStatement) {
            executor.submit(childStatement);
        }
    }
}
