package com.sfass.bsamonitoring.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice(basePackages = "com.sfass")
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {
	private static final Logger log = LoggerFactory.getLogger(GlobalResponseAdvice.class);

	@Override
	public boolean supports(
		MethodParameter returnType,
		Class<? extends HttpMessageConverter<?>> converterType
	) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(
		Object body,
		MethodParameter returnType,
		MediaType selectedContentType,
		Class<? extends HttpMessageConverter<?>> selectedConverterType,
		ServerHttpRequest request,
		ServerHttpResponse response
	) {
		ServletServerHttpResponse servletServerHttpResponse = (ServletServerHttpResponse)response;
		HttpServletResponse httpServletResponse = servletServerHttpResponse.getServletResponse();
		int status = httpServletResponse.getStatus();
		HttpStatus resolve = HttpStatus.resolve(status);

		if (resolve == null || body instanceof String) {
			return body;
		}
		if (resolve.is2xxSuccessful()) {
			return GlobalResponse.ok(body);
		}
		return body;
	}
}
