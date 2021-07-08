/**
 * 
 * Author Syed Thameem
 * 
 * File Name: OrderServiceImplTest
 * File Description: Junit class to test OrderServiceImpl.
 * 
 */
package com.abc.eshop.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.eshop.EshopApplication;
import com.abc.eshop.dto.CartDto;
import com.abc.eshop.dto.request.CartRequestDto;
import com.abc.eshop.dto.request.CheckoutDto;
import com.abc.eshop.dto.request.CheckoutRequestDto;
import com.abc.eshop.dto.response.CartResponseDto;
import com.abc.eshop.dto.response.CheckoutResponseDto;
import com.abc.eshop.dto.response.FetchCartItemsResponseDto;
import com.abc.eshop.exception.GlobalException;
import com.abc.eshop.service.OrderService;
import com.abc.eshop.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EshopApplication.class)
public class OrderServiceImplTest {

	@Autowired
	private OrderService orderService;
	
	private CartRequestDto request = new CartRequestDto();
	private Long orderId;
	private List<Long> cartItemList = null; 
	
	@Before
	public void prepareData() {
		CartDto cartDto = new CartDto();
		cartDto.setItemId(3L);
		cartDto.setQuantity(2);
		cartDto.setUserId("001");
		cartDto.setOrderId(0L);
		request.setServiceData(cartDto);
	}

	@Test
	public void testAddItem() throws ParseException {
		CartResponseDto response =  orderService.addItem(request);
		assertEquals(Constants.SUCCESS_RESPONSE_CODE, response.getResponseCode());
		assertEquals(Constants.success, response.getErrorCode());
	}
	
	@Test
	public void testGetItemsForCheckout() {
		orderId = 29L;
		FetchCartItemsResponseDto response = orderService.getItemsForCheckout(orderId);
		assertEquals(Constants.SUCCESS_RESPONSE_CODE, response.getResponseCode());
		assertEquals(Constants.success, response.getErrorCode());
	}
	
	@Test(expected = GlobalException.class)
	public void testGetItemsForInvalidOrderId() {
		orderId = 29L;
		orderService.getItemsForCheckout(orderId);
	}
	
	@Test
	public void testCheckOut() throws ParseException {
		CheckoutRequestDto request = new CheckoutRequestDto();
		
		CheckoutDto dto = new CheckoutDto();
		dto.setOrderId(29L);
		cartItemList = new ArrayList<>();
		cartItemList.add(30L);
		dto.setCartId(cartItemList);
		dto.setPaymentMode("CS");
		request.setServiceData(dto);
		
		CheckoutResponseDto response = orderService.checkout(request);
		assertEquals(Constants.SUCCESS_RESPONSE_CODE, response.getResponseCode());
		assertEquals(Constants.success, response.getErrorCode());
	}
	
	@Test(expected = GlobalException.class)
	public void testCheckOutInvalidOrderId() throws ParseException {
		CheckoutRequestDto request = new CheckoutRequestDto();
		
		CheckoutDto dto = new CheckoutDto();
		dto.setOrderId(29L);
		cartItemList = new ArrayList<>();
		cartItemList.add(30L);
		dto.setCartId(cartItemList);
		dto.setPaymentMode("CS");
		request.setServiceData(dto);
		
		orderService.checkout(request);
	}
	
	@Test
	public void receipt() {
		Long orderId = 29L;
		String response = orderService.generateReceipt(orderId);
		assertNotNull(response);
	}
	
	@Test(expected = GlobalException.class)
	public void receiptInvalidOrderId() {
		Long orderId = 22L;
		orderService.generateReceipt(orderId);
	}
}