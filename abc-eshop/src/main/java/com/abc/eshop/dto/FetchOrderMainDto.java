package com.abc.eshop.dto;

import java.util.List;

public class FetchOrderMainDto {

	private Long orderId;
	private List<FetchOrderDetailsDto> orderDetails;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public List<FetchOrderDetailsDto> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<FetchOrderDetailsDto> orderDetails) {
		this.orderDetails = orderDetails;
	}
}