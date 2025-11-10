package part3.dto;



public class CheckOutInDto {
	
	private String userId;
	private String itemId;
	private int qty;
	private String coupon;
	private boolean vip;
	
	public CheckOutInDto(String userId, String itemId, int qty, boolean vip, String coupon) {
        this.userId = userId;
        this.itemId = itemId;
        this.qty = qty;
        this.vip = vip;
        this.coupon = coupon;
    }
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public boolean isVip() {
		return vip;
	}
	public void setVip(boolean vip) {
		this.vip = vip;
	}
}
