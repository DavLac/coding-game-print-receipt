package com.bob;

import com.bob.domain.Order;
import com.bob.domain.RawReceipt;
import com.bob.mapper.OrderToReceiptMapper;

public class BobsAutoRegister {

    /**
     * The cash register prints on the receipt every item purchased group by products
     *
     * @param args item list
     */
    public static void main(String[] args) {
        Order order = new Order(args);
        order.calculateOrderTotal();
        RawReceipt rawReceipt = OrderToReceiptMapper.mapOrderToRawReceipt(order);
        rawReceipt.print();
    }
}
