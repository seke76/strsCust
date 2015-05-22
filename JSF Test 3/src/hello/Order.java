package hello;

import java.math.BigDecimal;

public class Order {

	String orderNo;
	String productName;
	BigDecimal price;
	int qty;

	public Order(String orderNo, String productName, 
                            BigDecimal price, int qty) {

		this.orderNo = orderNo;
		this.productName = productName;
		this.price = price;
		this.qty = qty;
	}

	//getter and setter methods
}
