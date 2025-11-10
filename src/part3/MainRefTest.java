package part3;

import part3.discount.DiscountPoilcy;
import part3.dto.CheckOutInDto;
import part3.dto.CheckOutOutDto;
import part3.exception.ValidationException;
import part3.inventory.InventoryRepository;
import part3.price.PricePolicy;

public class MainRefTest {
	public static void main(String[] args) {
		InventoryRepository inventory = new InventoryRepository();
		PricePolicy pricePolicy = new PricePolicy();
		DiscountPoilcy discountPoilcy = new DiscountPoilcy();
		
		RefactoredOrderService svc
			= new RefactoredOrderService(inventory, pricePolicy, discountPoilcy);
		
		// 정상
		CheckOutInDto successinDto = new CheckOutInDto("vip001", "A-100" ,2 ,true, "COUPON10");
		CheckOutOutDto outDto = svc.checkout(successinDto);
		System.out.println(outDto.getReceipt());
		
		// 검증실패
		try {
			CheckOutInDto failIndto = new CheckOutInDto("", "A-100", -1, false, "");
			svc.checkout(failIndto);
		} catch (ValidationException e) {
			System.out.println("검증실패:"+e.getMessage());
		}
		
		// 재고부족
        try {
        	CheckOutInDto lackIndto = new CheckOutInDto("u1","B-100", 99, false, "");
        	svc.checkout(lackIndto);
        } catch (ValidationException e) {
        	System.out.println("재고부족:"+e.getMessage());
        }
    }
}
