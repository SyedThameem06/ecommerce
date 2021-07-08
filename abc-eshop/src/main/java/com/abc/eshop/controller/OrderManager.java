package com.abc.eshop.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.eshop.dto.request.CartRequestDto;
import com.abc.eshop.dto.request.CheckoutRequestDto;
import com.abc.eshop.dto.response.CartResponseDto;
import com.abc.eshop.dto.response.CheckoutResponseDto;
import com.abc.eshop.dto.response.FetchCartItemsResponseDto;
import com.abc.eshop.dto.response.GlobalResponse;
import com.abc.eshop.service.OrderService;

@RestController
@RequestMapping(value = "/api/order")
public class OrderManager {

	@Autowired
	private OrderService orderService;
	
	@PostMapping(value = "/addItem")
	public CartResponseDto addItem(@RequestBody CartRequestDto request) throws ParseException {
		return orderService.addItem(request);
	}
	
	@GetMapping(value = "/viewCartItems/orderId/{orderId}")
	public FetchCartItemsResponseDto fetchCartItems(@PathVariable Long orderId) {
		return orderService.getItemsForCheckout(orderId);
	}
	
	@GetMapping(value = "/receipt/orderId/{orderId}")
	public String generateReceipt(@PathVariable Long orderId) {
		return orderService.generateReceipt(orderId);
	}
	
	@DeleteMapping(value = "/remove/{orderId}/{cartId}")
	public GlobalResponse removeItem(@PathVariable Long orderId, @PathVariable Long cartId) {
		return orderService.removeItem(orderId, cartId);
	}
	
	@PostMapping(value = "/checkout")
	public CheckoutResponseDto checkout(@RequestBody CheckoutRequestDto request) throws ParseException {
		return orderService.checkout(request);
	}
}
