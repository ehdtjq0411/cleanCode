package part3.inventory;

import java.util.Map;

public class InventoryRepository {

	private final Map<String, Integer> stock = Map.of("A-100", 20, "B-100", 10);
	
	public int findStock(String itemId) {
		return stock.getOrDefault(itemId, 0);
	}
}
