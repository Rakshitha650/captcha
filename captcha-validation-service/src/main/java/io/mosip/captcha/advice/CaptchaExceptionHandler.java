package io.mosip.captcha.advice;

import java.util.ArrayList;

import io.mosip.captcha.util.CaptchaErrorCode;
import io.mosip.captcha.exception.CaptchaException;
import io.mosip.captcha.util.CaptchaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.mosip.captcha.dto.ExceptionJSONInfoDTO;
import io.mosip.captcha.dto.MainResponseDTO;
import io.mosip.captcha.exception.InvalidRequestCaptchaException;


@RestControllerAdvice
public class CaptchaExceptionHandler {

	@Autowired
	protected Environment env;

	@Value("${mosip.captcha.api.id}")
	public String captchaApiId;

	@Value("${mosip.captcha.api.version}")
	private String captchaApiVersion;

	@ExceptionHandler(InvalidRequestCaptchaException.class)
	public MainResponseDTO<?> handleInvalidCaptchaRequest(InvalidRequestCaptchaException ex) {
		MainResponseDTO<?> response = new MainResponseDTO<>();
		response.setId(captchaApiId);
		response.setVersion(captchaApiVersion);
		response.setResponsetime(CaptchaUtils.getCurrentResponseTime());
		response.setResponse(null);
		ArrayList<ExceptionJSONInfoDTO> errors = new ArrayList<ExceptionJSONInfoDTO>();
		ExceptionJSONInfoDTO errorDetails = new ExceptionJSONInfoDTO(ex.getErrorCode(), ex.getErrorMessage());
		errors.add(errorDetails);
		response.setErrors(errors);
		return response;
	}

	@ExceptionHandler(CaptchaException.class)
	public MainResponseDTO<?> handleCaptchaException(CaptchaException ex) {
		MainResponseDTO<?> response = new MainResponseDTO<>();
		response.setId(captchaApiId);
		response.setVersion(captchaApiVersion);
		response.setResponsetime(CaptchaUtils.getCurrentResponseTime());
		response.setResponse(null);
		ArrayList<ExceptionJSONInfoDTO> errors = new ArrayList<ExceptionJSONInfoDTO>();
		ExceptionJSONInfoDTO errorDetails = new ExceptionJSONInfoDTO(ex.getErrorCode(), ex.getErrorMessage());
		errors.add(errorDetails);
		response.setErrors(errors);
		return response;
	}
	
	@ExceptionHandler(Exception.class)
	public MainResponseDTO<?> handleException(Exception ex) {
		MainResponseDTO<?> response = new MainResponseDTO<>();
		response.setId(captchaApiId);
		response.setVersion(captchaApiVersion);
		response.setResponsetime(CaptchaUtils.getCurrentResponseTime());
		response.setResponse(null);
		ArrayList<ExceptionJSONInfoDTO> errors = new ArrayList<ExceptionJSONInfoDTO>();
		ExceptionJSONInfoDTO errorDetails = new ExceptionJSONInfoDTO(CaptchaErrorCode.CAPTCHA_VALIDATION_FAILED.getErrorCode(), ex.getMessage());
		errors.add(errorDetails);
		response.setErrors(errors);
		return response;
	}

}
