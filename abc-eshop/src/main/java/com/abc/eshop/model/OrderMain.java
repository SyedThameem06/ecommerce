/**
 * 
 * Author Syed Thameem
 * 
 * File Name: OrderMain
 * File Description: Entity to maintain Order Main Details
 * 
 */
package com.abc.eshop.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class OrderMain {

	public Long orderId;
	public String userId;
	public Long paymentId;
	public BigDecimal totalItemAmount;
	public BigDecimal totalAmount;
	public double totalRoundOffAmount;
	public BigDecimal roundOffDiffAmount;
	public BigDecimal discountAmount;
	public String status;
	public List<OrderDetails> orderDetails;
	public List<OrderHistory> orderHistory;
	public GlobalEntity globalEntity;
}
