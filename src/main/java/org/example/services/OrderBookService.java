package org.example.services;

import java.util.concurrent.Callable;

public interface OrderBookService extends Callable<String> {
    void handelOrderBook();
}
