/**
 * 
 * Author Syed Thameem
 * 
 * File Name: ItemService
 * File Description: Service to Fetch Item from system.
 * 
 */
package com.abc.eshop.service;

import com.abc.eshop.dto.response.ItemResponseDto;

public interface ItemService {

	/**
	 * Fetch available Item for itemId.
	 * @param itemId
	 * @return
	 */
	public ItemResponseDto getItem(Long itemId);
	/**
	 * Fetch available Item for itemName.
	 * @param itemName
	 * @return
	 */
	public ItemResponseDto getItemByItemName(String itemName);
	/**
	 * Fetch all available items in the system.
	 * @return
	 */
	public ItemResponseDto getAllItems();
}
