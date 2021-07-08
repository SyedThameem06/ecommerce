/**
 * 
 * Author Syed Thameem
 * 
 * File Name: CartResponseDto
 * File Description: Cart response object.
 * 
 */
package com.abc.eshop.dto.response;

import com.abc.eshop.dto.CartDto;

public class CartResponseDto extends GlobalResponse {

	private CartDto serviceData;

	public CartDto getServiceData() {
		return serviceData;
	}

	public void setServiceData(CartDto serviceData) {
		this.serviceData = serviceData;
	}


	public CartResponseDto() {
		super();
	}

	public CartResponseDto(CartDto serviceData, int responseCode, String errorCode, String errorDesc) {
		super(responseCode, errorCode, errorDesc);
		this.serviceData = serviceData;
	}

	@Override
	public String toString() {
		return "OrderResponseDto [serviceData=" + serviceData + "]";
	}
}