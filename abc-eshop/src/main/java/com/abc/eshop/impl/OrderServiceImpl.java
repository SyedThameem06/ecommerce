package com.abc.eshop.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.eshop.dto.FetchOrderDetailsDto;
import com.abc.eshop.dto.FetchOrderMainDto;
import com.abc.eshop.dto.ItemDto;
import com.abc.eshop.dto.request.CartRequestDto;
import com.abc.eshop.dto.request.CheckoutRequestDto;
import com.abc.eshop.dto.response.CartResponseDto;
import com.abc.eshop.dto.response.CheckoutResponseDto;
import com.abc.eshop.dto.response.FetchCartItemsResponseDto;
import com.abc.eshop.dto.response.GlobalResponse;
import com.abc.eshop.dto.response.ItemResponseDto;
import com.abc.eshop.exception.GlobalException;
import com.abc.eshop.model.DiscountDetails;
import com.abc.eshop.model.GlobalEntity;
import com.abc.eshop.model.OrderDetails;
import com.abc.eshop.model.OrderHistory;
import com.abc.eshop.model.OrderMain;
import com.abc.eshop.model.PaymentDetails;
import com.abc.eshop.service.DiscountService;
import com.abc.eshop.service.ItemService;
import com.abc.eshop.service.OrderService;
import com.abc.eshop.util.Constants;

public class OrderServiceImpl implements OrderService{

	private static Map<Long, OrderMain> data = new ConcurrentHashMap<>();
	private static Map<Long, PaymentDetails> paymentData = new ConcurrentHashMap<>();

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private MessageByLocaleServiceImpl localResolver;

	@Autowired
	private DiscountService discountDetailsService;
	
	private Date currentDate = new Date();

	@Override
	public CartResponseDto addItem(CartRequestDto request) throws ParseException {
		
		ItemResponseDto itemResponseDto = itemService.getItem(request.getServiceData().getItemId());
		if(itemResponseDto.getResponseCode() == Constants.SUCCESS_RESPONSE_CODE && !itemResponseDto.getServiceData().isEmpty()) {
			ItemDto itemDetails = itemResponseDto.getServiceData().get(0);
			OrderMain orderMain = null;
			
			if(data != null && !data.isEmpty() && request.getServiceData().getOrderId() != null && request.getServiceData().getOrderId() != 0 
					&& data.containsKey(request.getServiceData().getOrderId())) {
				orderMain = data.get(request.getServiceData().getOrderId());
				orderMain.globalEntity.modifiedBy = request.getServiceData().getUserId();
				orderMain.globalEntity.modifiedOn = currentDate;
				prepareOrderDetails(request, itemDetails, orderMain, true);
				request.getServiceData().setOrderId(orderMain.orderId);
				data.put(orderMain.orderId, orderMain);
				return new CartResponseDto(request.getServiceData(), Constants.SUCCESS_RESPONSE_CODE, Constants.success, 
						localResolver.getErrorMessage(Constants.success));
			}
			orderMain = new OrderMain();
			orderMain.globalEntity = getGlobalEntity();
			orderMain.status = Constants.ORDER_INITIATED;
			orderMain.userId = request.getServiceData().getUserId();
			orderMain.orderId = System.nanoTime();
			prepareOrderHistory(orderMain, true);
			prepareOrderDetails(request, itemDetails, orderMain, false);
			data.put(orderMain.orderId, orderMain);
			request.getServiceData().setOrderId(orderMain.orderId);
		}else {
			throw new GlobalException(Constants.VALIDATION_ERROR_RESPONSE_CODE, Constants.ITEM_NOT_EXISTS, localResolver.getErrorMessage(Constants.ITEM_NOT_EXISTS));
		}
		
		return new CartResponseDto(request.getServiceData(), Constants.SUCCESS_RESPONSE_CODE, Constants.success, 
				localResolver.getErrorMessage(Constants.success));
	}

	@Override
	public FetchCartItemsResponseDto getItemsForCheckout(Long orderId) {
		FetchOrderMainDto fetchOrderMainDto = new FetchOrderMainDto();
		OrderMain orderMain = null;
		if(data != null && !data.isEmpty() && data.containsKey(orderId)) {
			orderMain = data.get(orderId);
			FetchOrderDetailsDto fetchOrderDetailsDto = null;
			List<FetchOrderDetailsDto> orderDetaisList = new ArrayList<>();
			for(OrderDetails details : orderMain.orderDetails) {
				fetchOrderDetailsDto = new FetchOrderDetailsDto();
				fetchOrderDetailsDto.setCartId(details.cartId);
				fetchOrderDetailsDto.setItemAmount(details.itemAmount);
				fetchOrderDetailsDto.setItemName(details.itemName);
				fetchOrderDetailsDto.setQuantity(details.quantity);
				orderDetaisList.add(fetchOrderDetailsDto);
			}
			fetchOrderMainDto.setOrderDetails(orderDetaisList);
			fetchOrderMainDto.setOrderId(orderId);
		}else {
			throw new GlobalException(Constants.VALIDATION_ERROR_RESPONSE_CODE, Constants.INVALID_ORDER_ID, 
					localResolver.getErrorMessage(Constants.INVALID_ORDER_ID));
		}
		return new FetchCartItemsResponseDto(fetchOrderMainDto, Constants.SUCCESS_RESPONSE_CODE, Constants.success, 
				localResolver.getErrorMessage(Constants.success));
	}
	
	
	/**
	 * Validates and prepare order details for both creating and updating existing orders.   
	 * @param request
	 * @param itemDetails
	 * @param orderMain
	 * @param isOrderExists
	 * @throws ParseException
	 */
	private void prepareOrderDetails(CartRequestDto request, ItemDto itemDto, OrderMain orderMain, boolean isOrderExists)
			throws ParseException {
		OrderDetails orderDetails = null;
		if(isOrderExists ) {
			boolean isItemExistsInCart = false;		
			for(OrderDetails details:orderMain.orderDetails) { // If Cart already exists then will increase quantity and update pricing accordingly.
				if(details.itemId.equals(itemDto.itemId)) {
					int quantity = 0;
					quantity = details.quantity + request.getServiceData().getQuantity();
					details.quantity =  quantity;
					details.itemAmount = details.itemPerUnitPrice.multiply(new BigDecimal(details.quantity));
					details.globalEntity.modifiedBy = orderMain.userId;
					details.globalEntity.modifiedOn = currentDate;
					details.globalEntity.modifiedBy = request.getServiceData().getUserId();
					details.globalEntity.modifiedOn = currentDate;
					isItemExistsInCart = true;
					break;
				}
			}
			if(!isItemExistsInCart) { // If Cart not exists create a new cart entry in order details.
				orderDetails = setOrderDetails(request, itemDto);
				orderMain.orderDetails.add(orderDetails);
			}
		}else { // If order not exists creates the order.
			orderDetails = setOrderDetails(request, itemDto);
			List<OrderDetails> orderDetailsList = new ArrayList<>();
			orderDetailsList.add(orderDetails);
			orderMain.orderDetails = orderDetailsList;
		}
	}

	/**
	 * Adds the cart items in order details. 
	 * @param request
	 * @param itemDetails
	 * @return
	 * @throws ParseException
	 */
	private OrderDetails setOrderDetails(CartRequestDto request, ItemDto itemDetails)
			throws ParseException {
		OrderDetails orderDetails;
		orderDetails = new OrderDetails();
		orderDetails.cartId = System.nanoTime();
		orderDetails.globalEntity = getGlobalEntity();
		orderDetails.itemName = itemDetails.itemName;
		orderDetails.itemId = itemDetails.itemId;
		orderDetails.quantity = request.getServiceData().getQuantity();
		orderDetails.itemPerUnitPrice = itemDetails.price;
		orderDetails.itemAmount = orderDetails.itemPerUnitPrice.multiply(new BigDecimal(orderDetails.quantity));
		orderDetails.status = Constants.ORDER_DETAILS_INTIAL_STATUS;
		return orderDetails;
	}
	/**
	 * Prepare the order status history to maintain change in order status. 
	 * @param orderMain
	 * @param isInitial
	 */
	private void prepareOrderHistory(OrderMain orderMain, boolean isInitial) {
		OrderHistory orderHistory = new OrderHistory();
		List<OrderHistory> orderHistoryList = new ArrayList<>();
		orderHistory.status = orderMain.status;
		orderHistory.statusUpdatedOn = currentDate;
		if(isInitial) {
			orderHistoryList.add(orderHistory);
			orderMain.orderHistory = orderHistoryList;
		}else {
			orderMain.orderHistory.add(orderHistory);
		}
	}
	
	/**
	 * Prepares Global Entity object to update across the entity where ever required.
	 * @return
	 * @throws ParseException
	 */
	private GlobalEntity getGlobalEntity() throws ParseException {
		Date defaultDate = new SimpleDateFormat("yyyy/MM/dd").parse(Constants.defaultDate);
		GlobalEntity globalEntity = new GlobalEntity();
		globalEntity.createdBy = "System";
		globalEntity.createdOn = currentDate;
		globalEntity.modifiedBy = "";
		globalEntity.modifiedOn = defaultDate;
		return globalEntity;
	}

	@Override
	public String generateReceipt(Long orderId) {
		StringBuilder responseStr = new StringBuilder();
		if(data.containsKey(orderId)) {
			OrderMain orderMain = data.get(orderId);
			responseStr.append("Order# : "+orderMain.orderId+"\n");
			responseStr.append("Item Name" + "\t" + "PricePerUnit" + "\t" + "Quantity" + "\t" + "ItemTotalPrice" +"\n");
			for(OrderDetails orderDetails : orderMain.orderDetails) {
				if(orderDetails.status.equals(Constants.ORDER_DETAILS_COMLETED_STATUS)) {
					responseStr.append(orderDetails.itemName +"\t" + orderDetails.itemPerUnitPrice + "\t\t" + orderDetails.quantity 
							+ "\t" + orderDetails.itemAmount +"\n");
				}
			}
			responseStr.append("Total Before Tax \t\t" + orderMain.totalItemAmount + "\n");
			responseStr.append("Total Discount Amount \t\t "+ orderMain.discountAmount + "\n");
			responseStr.append("Order Total \t\t\t" + orderMain.totalAmount + "\n");
		}else {
			throw new GlobalException(Constants.VALIDATION_ERROR_RESPONSE_CODE, Constants.INVALID_ORDER_ID, 
					localResolver.getErrorMessage(Constants.INVALID_ORDER_ID));	
		}
		return responseStr.toString();
	}

	@Override
	public GlobalResponse removeItem(Long orderId, Long cartId) {
		if(data != null && !data.isEmpty() && data.containsKey(orderId)) {
			OrderMain orderMain = data.get(orderId);
			orderMain.orderDetails.removeIf(list -> list.cartId.equals(cartId));
		}else {
			throw new GlobalException(Constants.VALIDATION_ERROR_RESPONSE_CODE, Constants.INVALID_ORDER_ID, localResolver.getErrorMessage(Constants.INVALID_ORDER_ID));
		}
		return new GlobalResponse(Constants.SUCCESS_RESPONSE_CODE, Constants.success, localResolver.getErrorMessage(Constants.success));
	}
	
	/**
	 * Fetches Order Details if cart id matches else return null.
	 * @param orderMain
	 * @param cartId
	 * @return
	 */
	private OrderDetails getCartItem(OrderMain orderMain, Long cartId) {
		return orderMain.orderDetails.stream().filter(details -> details.cartId.equals(cartId))
				.findAny().orElse(null);
	}
	
	/**
	 * Applies discount based on Discount Master.
	 * @param totalAmount
	 * @return
	 */
	private BigDecimal applyDiscount(int binNo, BigDecimal totalAmount) {
		List<DiscountDetails> discountDetails = discountDetailsService.getDiscountDetails(binNo);
		return !discountDetails.isEmpty() 
				? totalAmount.subtract((totalAmount.multiply(discountDetails.get(0).discountPercentage)).divide(new BigDecimal(100))) 
						: totalAmount;
	}
	
	/**
	 * 
	 * @param orderMain
	 * @param request
	 * @throws ParseException
	 */
	private void completePayment(OrderMain orderMain, CheckoutRequestDto request) throws ParseException {
		PaymentDetails paymentDetails = new PaymentDetails();
		paymentDetails.orderId = orderMain.orderId;
		paymentDetails.paymentAmount = orderMain.totalAmount;
		paymentDetails.paymentMode = request.getServiceData().getPaymentMode();
		paymentDetails.userId = orderMain.userId;
		paymentDetails.globalEntity = getGlobalEntity();
		paymentDetails.paymentId = System.nanoTime();
		paymentData.put(paymentDetails.paymentId, paymentDetails);
		orderMain.paymentId = paymentDetails.paymentId;
	}
	@Override
	public CheckoutResponseDto checkout(CheckoutRequestDto request) throws ParseException {
		if(data != null && !data.isEmpty() && request.getServiceData().getOrderId() != null && request.getServiceData().getOrderId() != 0 
				&& data.containsKey(request.getServiceData().getOrderId())) {
			OrderMain orderMain = data.get(request.getServiceData().getOrderId());
			BigDecimal totalItemAmount = new BigDecimal(0);
			BigDecimal remainingAmountAfterDiscount;
			BigDecimal totalAmount = new BigDecimal(0);
			for(Long cartId : request.getServiceData().getCartId()) {
				OrderDetails orderDetails = getCartItem(orderMain, cartId);
				if(orderDetails != null) {
					totalItemAmount = totalItemAmount.add(orderDetails.itemAmount);
					orderDetails.status = Constants.ORDER_DETAILS_COMLETED_STATUS;
					orderDetails.globalEntity.modifiedBy = orderMain.userId;
					orderDetails.globalEntity.modifiedOn = currentDate;
					orderMain.orderDetails.add(orderDetails); 
				}
			}
			int binNo = Integer.parseInt(request.getServiceData().getCardNo().substring(0,6));
			remainingAmountAfterDiscount = applyDiscount(binNo,totalItemAmount);
			totalAmount = totalAmount.add(remainingAmountAfterDiscount);
			
			orderMain.totalAmount = totalAmount;
			orderMain.totalItemAmount = totalItemAmount;
			orderMain.globalEntity.modifiedBy = orderMain.userId;
			orderMain.globalEntity.modifiedOn = currentDate;
			orderMain.discountAmount = totalItemAmount.subtract(totalAmount);
			orderMain.totalRoundOffAmount = Math.round(totalAmount.doubleValue());
			orderMain.roundOffDiffAmount = BigDecimal.valueOf((totalAmount.doubleValue() - orderMain.totalRoundOffAmount)); 
			orderMain.status = Constants.ORDER_COMPLETED;
			orderMain.globalEntity.modifiedBy = orderMain.userId;
			orderMain.globalEntity.modifiedOn = currentDate;
			completePayment(orderMain,request);
			prepareOrderHistory(orderMain, false);
			data.put(request.getServiceData().getOrderId(), orderMain);
		}else {
			throw new GlobalException(Constants.VALIDATION_ERROR_RESPONSE_CODE, Constants.INVALID_ORDER_ID, 
					localResolver.getErrorMessage(Constants.INVALID_ORDER_ID));
		}
		return new CheckoutResponseDto(request.getServiceData(), Constants.SUCCESS_RESPONSE_CODE, Constants.success, 
				localResolver.getErrorMessage(Constants.success));
	}
}