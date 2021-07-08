/**
 * 
 * Author Syed Thameem
 * 
 * File Name: OrderService
 * File Description: Order Service to order items for shopping.
 * 
 */
package com.abc.eshop.service;

import java.text.ParseException;

import com.abc.eshop.dto.request.CartRequestDto;
import com.abc.eshop.dto.request.CheckoutRequestDto;
import com.abc.eshop.dto.response.CartResponseDto;
import com.abc.eshop.dto.response.CheckoutResponseDto;
import com.abc.eshop.dto.response.FetchCartItemsResponseDto;
import com.abc.eshop.dto.response.GlobalResponse;
import com.abc.eshop.exception.GlobalException;

public interface OrderService {

	/**
	 * Add items to the cart.
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	public CartResponseDto addItem(CartRequestDto request) throws ParseException;
	
	/**
	 * Fetch Items waiting for checkout.
	 * @param orderId
	 * @return
	 */
	public FetchCartItemsResponseDto getItemsForCheckout(Long orderId);

	/**
	 * Remove item from the cart
	 * @param orderId
	 * @param cartId
	 * @return
	 */
	public GlobalResponse removeItem(Long orderId, Long cartId);
	
	/**
	 * Completes the purchase of items added to cart.
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	public CheckoutResponseDto checkout(CheckoutRequestDto request) throws GlobalException, ParseException;
	
	/**
	 * Generate Print Receipt for orders.
	 * @param orderId
	 * @return
	 */
	public String generateReceipt(Long orderId);
}
