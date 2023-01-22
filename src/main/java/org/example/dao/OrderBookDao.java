package org.example.dao;

import org.json.JSONObject;

public interface OrderBookDao {
    JSONObject getOrderBook(String url);
}
