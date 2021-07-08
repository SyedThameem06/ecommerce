package com.abc.eshop.service;

import java.util.List;

import com.abc.eshop.model.DiscountDetails;

public interface DiscountService {
	public List<DiscountDetails> getDiscountDetails(int binNo);
}