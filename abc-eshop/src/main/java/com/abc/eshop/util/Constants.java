/**
 * 
 * Author Syed Thameem
 * 
 * File Name: Constants
 * File Description: Final Constant variable are declared here for the entire application.
 * 
 */
package com.abc.eshop.util;

import java.util.Arrays;
import java.util.List;

public class Constants {

	private Constants() {
		
	}
	
	public static final String defaultDate = "1900/01/01";
	public static final String system = "System";
	public static final String success = "000";
	public static final int SUCCESS_RESPONSE_CODE = 0;
	public static final int SYSTEM_ERROR_RESPONSE_CODE = -1;
	public static final int VALIDATION_ERROR_RESPONSE_CODE = -2;
	public static final String internalErrorCode = "CMNRES";
	public static final String EMPTY = "";
	public static final String NO_REC_FOUND = "ER001";
	public static final String ITEM_NAME = "itemName";
	public static final String SALE_TAX_TYPE = "SALE_TAX";
	public static final String IMPORT_TAX_TYPE = "IMPORT_TAX";
	public static final String ORDER_INITIATED = "INITIATED";
	public static final String ORDER_COMPLETED = "COMPLETED";
	public static final String STOCK_NOT_AVAILABLE = "OSI01";
	public static final String INVALID_ORDER_ID = "OSI02";
	public static final String ORDER_DETAILS_INTIAL_STATUS = "ADDED";
	public static final String ORDER_DETAILS_COMLETED_STATUS = "PURCHASED";
	public static final List<String> CATEGORY_NOT_ALLOWED_FOR_DISCOUNT = Arrays.asList("Management");
	public static final String ITEM_NOT_EXISTS = "ITNTEX";
}