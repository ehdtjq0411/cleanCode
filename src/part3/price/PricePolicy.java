package part3.price;

import java.math.BigDecimal;

public class PricePolicy {

	private final static int WEIGHT_PER_UNIT = 2;
	
	public BigDecimal basePrice(String itemId, int qty) {
		int unit = itemId.startsWith("A") ? 100 : 150;
		return BigDecimal.valueOf(unit).multiply(BigDecimal.valueOf(qty));
	}
	
	public BigDecimal shippingPrice(String itemId, int qty) {
		int shipping = itemId.startsWith("A") ? 500 : 1200;
		int weight = qty * WEIGHT_PER_UNIT;
		int extra = (weight > 10) ? 800 : 0;
		return BigDecimal.valueOf(shipping + extra);
	}
}
