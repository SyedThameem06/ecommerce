/**
 * 
 * Author Syed Thameem
 * 
 * File Name: CheckoutRequestDto
 * File Description: Checkout request object.
 * 
 */
package com.abc.eshop.dto.request;

public class CheckoutRequestDto {

	private CheckoutDto serviceData;

	public CheckoutDto getServiceData() {
		return serviceData;
	}

	public void setServiceData(CheckoutDto serviceData) {
		this.serviceData = serviceData;
	}

	@Override
	public String toString() {
		return "CheckoutRequestDto [serviceData=" + serviceData + "]";
	}
}