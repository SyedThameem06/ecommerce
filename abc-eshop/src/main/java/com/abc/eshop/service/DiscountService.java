package com.abc.eshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abc.eshop.model.DiscountDetails;

@Service
public interface DiscountService {
	public List<DiscountDetails> getDiscountDetails(int binNo);
}