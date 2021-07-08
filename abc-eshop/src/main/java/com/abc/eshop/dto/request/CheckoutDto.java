/**
 * 
 * Author Syed Thameem
 * 
 * File Name: CheckoutDto
 * File Description: Checkout data transfer object.
 * 
 */
package com.abc.eshop.dto.request;

import java.util.List;

public class CheckoutDto {

	private Long orderId;
	private List<Long> cartId;
	private String paymentMode;
	private String cardNo;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public List<Long> getCartId() {
		return cartId;
	}
	public void setCartId(List<Long> cartId) {
		this.cartId = cartId;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	@Override
	public String toString() {
		return "CheckoutDto [orderId=" + orderId + ", cartId=" + cartId + ", paymentMode=" + paymentMode + ", cardNo="
				+ cardNo + "]";
	}
}