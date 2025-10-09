package juloo.keyboard2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class KeyboardExecutors {

    private static final int CORE_POOL_SIZE = 3; // For custom, common, wordlist, and autocorrect dictionaries

    public static final ExecutorService HIGH_PRIORITY_EXECUTOR =
            Executors.newFixedThreadPool(CORE_POOL_SIZE, new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setPriority(Thread.MAX_PRIORITY);
                    return t;
                }
            });
}