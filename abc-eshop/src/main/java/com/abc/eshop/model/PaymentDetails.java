/**
 * 
 * Author Syed Thameem
 * 
 * File Name: PaymentDetails
 * File Description: Entity to maintain Payment Details
 * 
 */

package com.abc.eshop.model;

import java.math.BigDecimal;

public class PaymentDetails {

	public Long paymentId;
	public Long orderId;
	public String userId;
	public String paymentMode;
	public BigDecimal paymentAmount;
	public GlobalEntity globalEntity;
}