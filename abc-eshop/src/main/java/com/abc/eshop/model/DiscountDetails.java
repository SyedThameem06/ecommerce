/**
 * 
 * Author Syed Thameem
 * 
 * File Name: DiscountDetails
 * File Description: Entity to maintain Discount Details
 * 
 */
package com.abc.eshop.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDetails {

	public Long discountId;
	public int binNo;
	public BigDecimal discountPercentage;
	public GlobalEntity globalEntity;
}