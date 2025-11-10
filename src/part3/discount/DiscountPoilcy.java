package part3.discount;

import java.math.BigDecimal;

import part3.code.CouponCode;

public class DiscountPoilcy {

	private final static double VIP_DISCOUNT = 0.9;
	
	
	public BigDecimal discountApply(BigDecimal base, boolean vip, String coupon) {
		BigDecimal result = base;
		
		if( vip ) {
			result = result.multiply(BigDecimal.valueOf(VIP_DISCOUNT));
		}
		
		if(CouponCode.COUPON10.equals(coupon)) {
			result = result.subtract(BigDecimal.TEN);
		}
		
		return result;
	}
}
