/**
 * 
 * Author Syed Thameem
 * 
 * File Name: ItemDetails
 * File Description: Entity to maintain Item Details
 * 
 */
package com.abc.eshop.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ItemDetails {
	public Long itemId;
	public String itemName;
	public BigDecimal price;
}