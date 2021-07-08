/**
 * 
 * Author Syed Thameem
 * 
 * File Name: SearchItemManager
 * File Description: Controller class for search api.
 * 
 */
package com.abc.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.eshop.dto.response.ItemResponseDto;
import com.abc.eshop.service.ItemService;

@RestController
@RequestMapping(value = "/api/search")
public class SearchItemManager {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping(value = "/itemId/{itemId}")
	public ItemResponseDto getItem(@PathVariable Long itemId) {
		return itemService.getItem(itemId);
	}
	@GetMapping(value = "/itemName/{itemName}")
	public ItemResponseDto getItemByProductName(@PathVariable String itemName) {
		return itemService.getItemByItemName(itemName);
	}
	
	@GetMapping(value = "/")
	public ItemResponseDto getItems() {
		return itemService.getAllItems();
	}
}