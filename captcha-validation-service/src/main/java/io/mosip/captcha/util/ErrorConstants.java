package io.mosip.captcha.util;

import java.util.HashMap;
import java.util.Map;

public class ErrorConstants {
	public static  final String INVALID_CAPTCHA_CODE = "KER-CVS-001";
	public static final String INVALID_CAPTCHA_REQUEST = "KER-CVS-002";
	public static final String CAPTCHA_VALIDATION_FAILED = "KER-CVS-003";
	private static final Map<String, String> errorMessages = new HashMap<>();
	static {
		errorMessages.put(INVALID_CAPTCHA_CODE, "Invalid Captcha entered");
		errorMessages.put(INVALID_CAPTCHA_REQUEST, "Invalid request , Captcha token can't be null or empty");
		errorMessages.put(CAPTCHA_VALIDATION_FAILED, "Captcha validation failed");
	}
	public static String getErrorMessage(String errorCode) {
		return errorMessages.get(errorCode);
	}
}