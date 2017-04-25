package org.sodergren.restapi.response;

import org.sodergren.model.BookOrderStatus;
import org.sodergren.restapi.json.JSON;
import org.sodergren.restapi.json.JSONArrayBuilder;

public class OrderStatusResponse {
    private final int[] bookOrderStatus;

    public OrderStatusResponse(int[] statusCodes) {
        this.bookOrderStatus = statusCodes;
    }

    public String toJson() {
        JSONArrayBuilder array = JSON.makeArray();

        for (int statusCode : bookOrderStatus) {
            array.add(BookOrderStatus.get(statusCode).toString());
        }

        return array.build().toJson();
    }
}
