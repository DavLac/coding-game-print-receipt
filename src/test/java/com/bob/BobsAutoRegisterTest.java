package com.bob;

import com.bob.domain.Order;
import com.bob.domain.Product;
import com.bob.domain.RawReceipt;
import com.bob.mapper.OrderToReceiptMapper;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.IntStream;

@RunWith(Parameterized.class)
public class BobsAutoRegisterTest {

    private static final String REGEX_OUTPUT_ORDER_ITEMS = "^[0-9]{1,2} [a-zA-Z_]{1,50} @ £[0-9]{1,20}.[0-9]{2} : £[0-9]{1,20}.[0-9]{2}$";
    private static final String REGEX_OUTPUT_ORDER_TOTAL = "^GRAND TOTAL : £[0-9]{1,20}.[0-9]{2}$";

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new String[]{Product.CAR_WASH_WITH_WAX.name()},
                        new Order(new HashMap<Product, Long>() {{
                            put(Product.CAR_WASH_WITH_WAX, 1L);
                        }}, Product.CAR_WASH_WITH_WAX.getPrice())},
                {new String[]{Product.CAR_WASH_WITH_WAX.name(), Product.VALET.name()},
                        new Order(new HashMap<Product, Long>() {{
                            put(Product.CAR_WASH_WITH_WAX, 1L);
                            put(Product.VALET, 1L);
                        }}, Product.CAR_WASH_WITH_WAX.getPrice() + Product.VALET.getPrice())},
                {new String[]{Product.CAR_WASH_NORMAL.name(), Product.SERVICING.name()},
                        new Order(new HashMap<Product, Long>() {{
                            put(Product.CAR_WASH_NORMAL, 1L);
                            put(Product.SERVICING, 1L);
                        }}, Product.CAR_WASH_NORMAL.getPrice() + Product.SERVICING.getPrice())},
                {new String[]{Product.SERVICING.name(), Product.VALET.name(), Product.VALET.name()},
                        new Order(new HashMap<Product, Long>() {{
                            put(Product.SERVICING, 1L);
                            put(Product.VALET, 2L);
                        }}, Product.SERVICING.getPrice() + Product.VALET.getPrice() * 2)},
                {new String[]{Product.OIL_CHANGE_NORMAL.name(), Product.OIL_CHANGE_NORMAL.name(), Product.OIL_CHANGE_SYNTHETIC.name()},
                        new Order(new HashMap<Product, Long>() {{
                            put(Product.OIL_CHANGE_NORMAL, 2L);
                            put(Product.OIL_CHANGE_SYNTHETIC, 1L);
                        }}, Product.OIL_CHANGE_NORMAL.getPrice() * 2 + Product.OIL_CHANGE_SYNTHETIC.getPrice())},
                {new String[]{}, new Order(new HashMap<>(), 0d)}
        });
    }

    private final String[] orderItems;
    private final Order order;

    public BobsAutoRegisterTest(String[] orderItems, Order order) {
        this.orderItems = orderItems;
        this.order = order;
    }

    @Test
    public void createOrder_withExistingItems_shouldGroupAndCountProducts() {
        Order actualOrder = new Order(orderItems);
        actualOrder.calculateOrderTotal();
        Assert.assertTrue(EqualsBuilder.reflectionEquals(actualOrder, order));
    }

    @Test
    public void mapOrderToRawReceipt_withOrder_shouldCreateRawReceipt() {
        RawReceipt rawReceipt = OrderToReceiptMapper.mapOrderToRawReceipt(order);

        // check items size
        Assert.assertEquals(rawReceipt.getOrderItemRows().size(), order.getItems().size());

        // check Receipt items
        if (rawReceipt.getOrderItemRows().size() > 1) { // if order have items
            IntStream.range(0, rawReceipt.getOrderItemRows().size())
                    .forEach(index -> Assert.assertTrue(rawReceipt.getOrderItemRows().get(index).matches(REGEX_OUTPUT_ORDER_ITEMS)));
        }

        // check total row
        Assert.assertTrue(rawReceipt.getOrderTotalRow().matches(REGEX_OUTPUT_ORDER_TOTAL));
    }
}
