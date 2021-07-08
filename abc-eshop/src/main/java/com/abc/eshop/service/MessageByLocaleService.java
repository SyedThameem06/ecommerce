/**
 * 
 * Author Syed Thameem
 * 
 * File Name: MessageByLocaleService
 * File Description: Service to Fetch error message from properties.
 * 
 */
package com.abc.eshop.service;

public interface MessageByLocaleService {
	/**
	 * Retrieves error message from properties.
	 * @param id
	 * @return
	 */
	public String getErrorMessage(String id);
}
