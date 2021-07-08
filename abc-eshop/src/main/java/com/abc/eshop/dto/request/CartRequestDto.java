/**
 * 
 * Author Syed Thameem
 * 
 * File Name: CartRequestDto
 * File Description: Cart request object.
 * 
 */
package com.abc.eshop.dto.request;

import com.abc.eshop.dto.CartDto;

public class CartRequestDto {
	private CartDto serviceData;

	public CartDto getServiceData() {
		return serviceData;
	}

	public void setServiceData(CartDto serviceData) {
		this.serviceData = serviceData;
	}

	@Override
	public String toString() {
		return "OrderRequestDto [serviceData=" + serviceData + "]";
	}
}