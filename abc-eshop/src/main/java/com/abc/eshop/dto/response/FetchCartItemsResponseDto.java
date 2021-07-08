/**
 * 
 * Author Syed Thameem
 * 
 * File Name: FetchCartItemsResponseDto
 * File Description: Fetch Items response object.
 * 
 */
package com.abc.eshop.dto.response;

import com.abc.eshop.dto.FetchOrderMainDto;

public class FetchCartItemsResponseDto extends GlobalResponse{

	private FetchOrderMainDto serviceData;
	
	public FetchCartItemsResponseDto() {
		super();
	}

	public FetchCartItemsResponseDto(FetchOrderMainDto serviceData, int responseCode, String errorCode, String errorDesc) {
		super(responseCode, errorCode, errorDesc);
		this.serviceData = serviceData;
	}

	public FetchOrderMainDto getServiceData() {
		return serviceData;
	}

	public void setServiceData(FetchOrderMainDto serviceData) {
		this.serviceData = serviceData;
	}

	@Override
	public String toString() {
		return "FetchCartItemsResponseDto [serviceData=" + serviceData + "]";
	}
}