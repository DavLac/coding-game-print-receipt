package com.bob.domain;

import java.util.ArrayList;
import java.util.List;

public class RawReceipt {

    private List<String> orderItemRows = new ArrayList<>();
    private String orderTotalRow;

    public List<String> getOrderItemRows() {
        return orderItemRows;
    }

    public void setOrderItemRows(List<String> orderItemRows) {
        this.orderItemRows = orderItemRows;
    }

    public String getOrderTotalRow() {
        return orderTotalRow;
    }

    public void setOrderTotalRow(String orderTotalRow) {
        this.orderTotalRow = orderTotalRow;
    }

    public void print() {
        this.orderItemRows.forEach(System.out::println);
        System.out.println(this.orderTotalRow);
    }
}