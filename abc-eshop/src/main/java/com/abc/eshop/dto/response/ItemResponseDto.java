package com.abc.eshop.dto.response;

import java.util.List;

import com.abc.eshop.dto.ItemDto;

public class ItemResponseDto extends GlobalResponse {
	private List<ItemDto> serviceData;

	public ItemResponseDto(List<ItemDto> serviceData, int responseCode, String errorCode, String errorDesc) {
		super(responseCode, errorCode, errorDesc);
		this.serviceData = serviceData;
	}

	public List<ItemDto> getServiceData() {
		return serviceData;
	}

	public void setServiceData(List<ItemDto> serviceData) {
		this.serviceData = serviceData;
	}
}