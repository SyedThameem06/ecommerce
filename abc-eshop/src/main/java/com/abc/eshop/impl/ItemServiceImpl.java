package com.abc.eshop.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.eshop.dto.ItemDto;
import com.abc.eshop.dto.response.ItemResponseDto;
import com.abc.eshop.service.ItemService;
import com.abc.eshop.service.MessageByLocaleService;
import com.abc.eshop.util.Constants;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private MessageByLocaleService localResolver;
	private List<ItemDto> items;
	
	private void prepareItemDetails() {
		items = new ArrayList<>();
		ItemDto item = null;
		item = new ItemDto();
		item.itemId = 1L;
		item.itemName = "Refregirator";
		item.price = new BigDecimal(18000);
		items.add(item);
		
		item = new ItemDto();
		item.itemId = 2L;
		item.itemName = "Oven";
		item.price = new BigDecimal(12000);
		items.add(item);
		
		item = new ItemDto();
		item.itemId = 3L;
		item.itemName = "LED TV";
		item.price = new BigDecimal(25000);
		items.add(item);
	}
	
	@Override
	public ItemResponseDto getItem(Long itemId) {
		prepareItemDetails();
		List<ItemDto> itemLst = items.stream().filter(item -> item.itemId.equals(itemId)).collect(Collectors.toList());
		return new ItemResponseDto(itemLst, Constants.SUCCESS_RESPONSE_CODE, Constants.success, localResolver.getErrorMessage(Constants.success));
	}

	@Override
	public ItemResponseDto getItemByItemName(String itemName) {
		prepareItemDetails();
		List<ItemDto> itemLst = items.stream().filter(item -> item.itemName.contains(itemName)).collect(Collectors.toList());
		return new ItemResponseDto(itemLst, Constants.SUCCESS_RESPONSE_CODE, Constants.success, localResolver.getErrorMessage(Constants.success));
	}

	@Override
	public ItemResponseDto getAllItems() {
		prepareItemDetails();
		return new ItemResponseDto(items, Constants.SUCCESS_RESPONSE_CODE, Constants.success, localResolver.getErrorMessage(Constants.success));
	}
}