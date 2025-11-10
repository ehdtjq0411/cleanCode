package part3;

import java.math.BigDecimal;
import java.util.UUID;

import part3.code.ErrorCode;
import part3.discount.DiscountPoilcy;
import part3.dto.CheckOutInDto;
import part3.dto.CheckOutOutDto;
import part3.exception.ValidationException;
import part3.inventory.InventoryRepository;
import part3.price.PricePolicy;


class RefactoredOrderService {

    private final InventoryRepository inventoryRepository;
    private final PricePolicy pricePolicy;
    private final DiscountPoilcy discountPoilcy;
    
    public RefactoredOrderService(
    		InventoryRepository inventoryRepository,
    		PricePolicy pricePolicy,
    		DiscountPoilcy discountPoilcy ) {
    	this.inventoryRepository = inventoryRepository;
    	this.pricePolicy = pricePolicy;
    	this.discountPoilcy = discountPoilcy;
    }
    
    public CheckOutOutDto checkout(CheckOutInDto inDto) {
    	// 검증
    	validate( inDto );
    	
    	// 재고확인
    	checkStock( inDto );
    	
    	// 할인정책 적용 후 합계
    	BigDecimal sumPrice = getSumPrice( inDto );
    	
    	CheckOutOutDto outDto = new CheckOutOutDto();
    	outDto.setReceipt(getReceipt(inDto, sumPrice));
    	
    	return outDto;
    }
    
    
    private void validate( CheckOutInDto inDto ) {
    	if( inDto.getUserId() == null || inDto.getUserId().isEmpty() )
    		throw new ValidationException(ErrorCode.USER_REQUIRED);
    	
    	if( inDto.getItemId() == null || inDto.getItemId().isEmpty() )
    		throw new ValidationException(ErrorCode.ITEM_REQUIRED);
    	
    	if( inDto.getQty() <=0 )
    		throw new ValidationException(ErrorCode.QTY_POSITIVE);
    }
    
    private void checkStock( CheckOutInDto inDto ) {
    	int stock = inventoryRepository.findStock(inDto.getItemId());
    	
    	if( inDto.getQty() > stock )
    		throw new ValidationException(ErrorCode.OUT_OF_STOCK); 
    }
    
    private BigDecimal getSumPrice( CheckOutInDto inDto ) {
    	BigDecimal base = pricePolicy.basePrice(inDto.getItemId(), inDto.getQty());
    	BigDecimal shipping = pricePolicy.shippingPrice(inDto.getItemId(), inDto.getQty());
    	BigDecimal sum = base.add(shipping);
    	
    	return discountPoilcy.discountApply(sum, inDto.isVip(), inDto.getCoupon());
    }
    
    private String getReceipt( CheckOutInDto inDto, BigDecimal sum) {
    	String orderId = UUID.randomUUID().toString();
    	String receipt = "OK:" + orderId + ":" + inDto.getUserId() + ":" + inDto.getItemId() + ":" + inDto.getQty() + ":" + sum;
    	
    	return receipt;
    }
}