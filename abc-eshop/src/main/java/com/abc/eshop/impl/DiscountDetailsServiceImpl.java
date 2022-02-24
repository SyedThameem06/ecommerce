package com.abc.eshop.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.abc.eshop.model.DiscountDetails;
import com.abc.eshop.service.DiscountService;

public class DiscountDetailsServiceImpl implements DiscountService {

	private List<DiscountDetails> discountDetails;
	
	private void prepareDiscountDetails() {
		DiscountDetails discountDetail = null;
		
		discountDetails = new ArrayList<>();
		discountDetail = new DiscountDetails();
		discountDetail.discountId =1L;
		discountDetail.binNo = 520000;
		discountDetail.discountPercentage = new BigDecimal(20);
		discountDetails.add(discountDetail);
		discountDetail = new DiscountDetails();
		discountDetail.discountId =2L;
		discountDetail.binNo = 530000;
		discountDetail.discountPercentage = new BigDecimal(10);
		discountDetails.add(discountDetail);
	}
	
	@Override
	public List<DiscountDetails> getDiscountDetails(int binNo) {
		prepareDiscountDetails();
		return discountDetails.stream().filter(list -> list.binNo == binNo).collect(Collectors.toList()); 
	}

}
