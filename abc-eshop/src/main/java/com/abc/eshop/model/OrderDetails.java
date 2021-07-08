/**
 * 
 * Author Syed Thameem
 * 
 * File Name: OrderDetails
 * File Description: Entity to maintain Order Details
 * 
 */
package com.abc.eshop.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderDetails {
	public Long cartId;
	public Long itemId;
	public String itemName;
	public int quantity;
	public BigDecimal itemPerUnitPrice;
	public BigDecimal itemAmount;
	public String status; 
	public GlobalEntity globalEntity;
}
