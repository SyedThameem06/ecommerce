/**
 * 
 * Author Syed Thameem
 * 
 * File Name: ItemDto
 * File Description: Item Data transfer object for request and response.
 * 
 */
package com.abc.eshop.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ItemDto {
	public Long itemId;
	public String itemName;
	public BigDecimal price;
}