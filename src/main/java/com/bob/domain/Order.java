package com.bob.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Order {
    private Map<Product, Long> items;
    private Double total;

    public Order(Map<Product, Long> items, Double total) {
        this.items = items;
        this.total = total;
    }

    public Order(String[] itemList) {
        this.items = buildItemsByProducts(itemList);
    }

    public Map<Product, Long> getItems() {
        return items;
    }

    void setItems(Map<Product, Long> items) {
        this.items = items;
    }

    public Double getTotal() {
        return total;
    }

    void setTotal(Double total) {
        this.total = total;
    }

    private Map<Product, Long> buildItemsByProducts(String[] itemList) {
        // we consider that itemList are existing in Product list
        return Arrays.stream(itemList)
                .collect(groupingBy(Product::valueOf, LinkedHashMap::new, Collectors.counting()));
    }

    public void calculateOrderTotal() {
        this.total = items.entrySet().stream()
                .mapToDouble(value -> value.getValue() * value.getKey().getPrice())
                .sum();
    }
}