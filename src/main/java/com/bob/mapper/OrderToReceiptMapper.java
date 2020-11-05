package com.bob.mapper;

import com.bob.domain.Order;
import com.bob.domain.RawReceipt;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class OrderToReceiptMapper {

    private static final String STRING_FORMAT_PATTERN_ORDER_ITEMS = "%d %s @ £%.2f : £%.2f";
    private static final String STRING_FORMAT_PATTERN_ORDER_TOTAL = "GRAND TOTAL : £%.2f";

    private OrderToReceiptMapper() {
        // private constructor to avoid instantiation
    }

    public static RawReceipt mapOrderToRawReceipt(Order order) {
        RawReceipt rawReceipt = new RawReceipt();
        // ITEMS
        List<String> rawItemList = order.getItems().entrySet().stream()
                .map(item -> String.format(Locale.UK, STRING_FORMAT_PATTERN_ORDER_ITEMS,
                        item.getValue(),
                        item.getKey().name(),
                        item.getKey().getPrice(),
                        item.getKey().getPrice() * item.getValue()))
                .collect(Collectors.toList());

        rawReceipt.getOrderItemRows().addAll(rawItemList);

        // TOTAL
        rawReceipt.setOrderTotalRow(String.format(Locale.UK, STRING_FORMAT_PATTERN_ORDER_TOTAL, order.getTotal()));

        return rawReceipt;
    }
}
