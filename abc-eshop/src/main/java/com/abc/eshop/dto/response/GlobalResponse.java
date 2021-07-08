/**
 * 
 * Author Syed Thameem
 * 
 * File Name: GlobalResponse
 * File Description: Global Response for e-shopping application.
 * 
 */
package com.abc.eshop.dto.response;

public class GlobalResponse{
	
	private int responseCode;
	private String errorCode;
	private String errorDesc;

	public GlobalResponse() {
		
	}
	
	public GlobalResponse(int responseCode,String errorCode,String errorDesc) {
		this.responseCode = responseCode;
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}
	
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	@Override
	public String toString() {
		return "GlobalResponse [responseCode=" + responseCode + ", errorCode=" + errorCode + ", errorDesc=" + errorDesc
				+ "]";
	}
}