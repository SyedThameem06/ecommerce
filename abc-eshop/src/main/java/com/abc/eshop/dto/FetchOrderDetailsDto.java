/**
 * 
 * Author Syed Thameem
 * 
 * File Name: FetchOrderDetailsDto
 * File Description: Checkout Items data transfer object.
 * 
 */
package com.abc.eshop.dto;

import java.math.BigDecimal;

public class FetchOrderDetailsDto {

	private Long cartId;
	private String itemName;
	private int quantity;
	private BigDecimal itemAmount;
	
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getItemAmount() {
		return itemAmount;
	}
	public void setItemAmount(BigDecimal itemAmount) {
		this.itemAmount = itemAmount;
	}
}