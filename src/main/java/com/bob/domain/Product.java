package com.bob.domain;

public enum Product {

	SERVICING            (100.00),
	VALET                ( 25.00),
	OIL_CHANGE_NORMAL    ( 15.00),
	OIL_CHANGE_SYNTHETIC ( 25.00),
	CAR_WASH_NORMAL      (  5.00),
	CAR_WASH_WITH_WAX    ( 18.00);

	private double price;

	Product(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}
}
