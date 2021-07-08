package com.abc.eshop.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.abc.eshop.service.MessageByLocaleService;

@Component
public class MessageByLocaleServiceImpl implements MessageByLocaleService {

	@Autowired
    private MessageSource errorMessage;
	
	@Override
	public String getErrorMessage(String id) {
		return errorMessage.getMessage(id, null, LocaleContextHolder.getLocale());
	}
}