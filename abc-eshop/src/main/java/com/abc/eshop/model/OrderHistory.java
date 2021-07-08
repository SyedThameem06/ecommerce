/**
 * 
 * Author Syed Thameem
 * 
 * File Name: OrderHistory
 * File Description: Entity to maintain Order History
 * 
 */
package com.abc.eshop.model;

import java.util.Date;
import lombok.Data;

@Data
public class OrderHistory {
	public Long statusId;
	public String status;
	public Date statusUpdatedOn;
}