/**
 * 
 * Author Syed Thameem
 * 
 * File Name: CheckoutResponseDto
 * File Description: Checkout response object.
 * 
 */
package com.abc.eshop.dto.response;

import com.abc.eshop.dto.request.CheckoutDto;

public class CheckoutResponseDto extends GlobalResponse{

	private CheckoutDto serviceData;
	
	public CheckoutResponseDto() {
		super();
	}

	public CheckoutResponseDto(CheckoutDto serviceData, int responseCode, String errorCode, String errorDesc) {
		super(responseCode, errorCode, errorDesc);
		this.serviceData = serviceData;
	}

	public CheckoutDto getServiceData() {
		return serviceData;
	}

	public void setServiceData(CheckoutDto serviceData) {
		this.serviceData = serviceData;
	}

	@Override
	public String toString() {
		return "CheckoutResponseDto [serviceData=" + serviceData + "]";
	}
}