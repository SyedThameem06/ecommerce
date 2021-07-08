/**
 * 
 * Author Syed Thameem
 * 
 * File Name: CartDto
 * File Description: Cart data transfer object.
 * 
 */
package com.abc.eshop.dto;

public class CartDto {

	private Long itemId;
	private int quantity;
	private String userId;
	private Long orderId;
	
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "OrderDto [itemId=" + itemId + ", quantity=" + quantity + ", userId=" + userId + ", orderId=" + orderId
				+ "]";
	}
}