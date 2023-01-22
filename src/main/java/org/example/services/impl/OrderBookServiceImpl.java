package org.example.services.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.example.dao.impl.OrderBookBinanceImpl;
import org.example.model.OrderType;
import org.example.services.OrderBookService;
import org.json.JSONArray;
import org.json.JSONObject;

public class OrderBookServiceImpl implements OrderBookService {
    private static final String URL = "https://api.binance.com/api/v3/depth?symbol=LINKUSDT&limit=";
    private final int limit;
    private final int timeOut;
    private final int steps;

    public OrderBookServiceImpl(Integer limit, int timeOut, int steps) {
        this.limit = limit;
        this.timeOut = timeOut;
        this.steps = steps;
    }

    @Override
    public String call() throws Exception {
        handelOrderBook();
        return "Done";
    }

    public void handelOrderBook() {
        double[][][] lastOrderBook = new double[2][0][2];
        double[][] newOrderBook;
        String[] ordersType = new String[]{OrderType.BIDS.geOrderType(),
                OrderType.ASKS.geOrderType()};
        boolean isHandled;
        int counter = 0;

        while (steps == 0 || counter++ < steps) {
            JSONObject jsonObj = new OrderBookBinanceImpl().getOrderBook(URL + limit);
            System.out.println(LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("=== MM.dd.yyy hh.mm.ss ===")));
            for (int k = 0; k < ordersType.length; k++) {
                JSONArray jsonArry1 = jsonObj.getJSONArray(ordersType[k]);
                newOrderBook = new double[jsonArry1.length()][2];
                int posLastOB = 0;

                for (int i = 0; i < jsonArry1.length(); i++) {
                    JSONArray jsa1;
                    jsa1 = jsonArry1.getJSONArray((k == 0 ? jsonArry1.length() - i - 1 : i));

                    for (int j = 0; j < jsa1.length(); j++) {
                        newOrderBook[i][j] = jsa1.getDouble(j);
                    }

                    isHandled = false;
                    for (int n = posLastOB; n < lastOrderBook[k].length; n++) {

                        if (newOrderBook[i][0] == lastOrderBook[k][n][0]) {
                            System.out.println("update [" + ordersType[k]
                                    + "] (" + newOrderBook[i][0] + ", " + newOrderBook[i][1] + ")");
                            posLastOB++;
                            isHandled = true;
                            break;
                        } else if (newOrderBook[i][0] > lastOrderBook[k][n][0]) {
                            System.out.println("delete [" + ordersType[k] + "] ("
                                    + lastOrderBook[k][n][0] + ", " + lastOrderBook[k][n][1] + ")");
                            posLastOB++;
                        }
                    }

                    if (!isHandled) {
                        System.out.println("new [" + ordersType[k]
                                + "] (" + newOrderBook[i][0] + ", " + newOrderBook[i][1] + ")");
                    }
                }
                for (int n = posLastOB; n < lastOrderBook[k].length; n++) {
                    System.out.println("delete [" + ordersType[k]
                            + "] (" + lastOrderBook[k][n][0] + ", " + lastOrderBook[k][n][1] + ")");
                    posLastOB++;
                }
                lastOrderBook[k] = newOrderBook;
                newOrderBook = null;
            }

            try {
                Thread.sleep(timeOut);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
