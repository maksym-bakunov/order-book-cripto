package org.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.example.services.impl.OrderBookServiceImpl;

public class Main {
    private static final int limit = 5000;
    private static final int timeOut = 10000;
    private static final int steps = 0;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> task = new OrderBookServiceImpl(limit, timeOut, steps);
        executorService.submit(task);
        executorService.shutdown();
    }
}
