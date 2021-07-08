/**
 * 
 * Author Syed Thameem
 * 
 * File Name: ItemServiceImplTest
 * File Description: Junit class to test ItemServiceImpl.
 * 
 */
package com.abc.eshop.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.eshop.EshopApplication;
import com.abc.eshop.service.ItemService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApplication.class)
public class ItemServiceImplTest {

	@Autowired
	private ItemService itemService;
	
	@Test
	public void getProdut() {
		Long itemId = Long.valueOf(1);
		assertEquals(0, itemService.getItem(itemId).getResponseCode());
	}
	
	@Test
	public void getProdutNotFound() {
		Long itemId = Long.valueOf(5);
		assertEquals(-2, itemService.getItem(itemId).getResponseCode());
	}
	
	@Test
	public void getItemByItemName() {
		String itemName = "A Promised Land Hardcover";
		assertEquals(0, itemService.getItemByItemName(itemName).getResponseCode());
	}
	
	@Test
	public void getItemByItemNameNotFound() {
		String itemName = "Desiya Geedam";
		assertEquals(-2, itemService.getItemByItemName(itemName).getResponseCode());
	}
	
}
