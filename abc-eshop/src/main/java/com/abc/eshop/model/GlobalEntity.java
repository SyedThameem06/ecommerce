/**
 * 
 * Author Syed Thameem
 * 
 * File Name: GlobalEntity
 * File Description: Entity to maintain record state
 * 
 */
package com.abc.eshop.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalEntity {
	public String createdBy;
	public Date createdOn;
	public String modifiedBy;
	public Date modifiedOn;
}