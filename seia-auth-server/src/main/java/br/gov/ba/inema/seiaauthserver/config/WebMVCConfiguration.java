package br.gov.ba.inema.seiaauthserver.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.ControllerAdviceBean;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

@EnableWebMvc
@Configuration
public class WebMVCConfiguration extends WebMvcConfigurationSupport {
	
	@Bean
	public ExceptionHandlerExceptionResolver handlerExceptionResolver() {
		CustomExceptionHandlerExceptionResolver exceptionResolver = new CustomExceptionHandlerExceptionResolver();
		exceptionResolver.setOrder(0);
		exceptionResolver.setMessageConverters(messageConverters());
		return exceptionResolver;
	}

	private MappingJackson2HttpMessageConverter jsonHttpMessageConverter() {
		return new MappingJackson2HttpMessageConverter();
	}

	private List<HttpMessageConverter<?>> messageConverters() {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(jsonHttpMessageConverter());
		return messageConverters;
	}
}

/**
 * Seta o ControllerAdvicer como alta prioridade na resolução de exceptions
 * @author tulio
 *
 */
class CustomExceptionHandlerExceptionResolver extends ExceptionHandlerExceptionResolver {
	private Map<ControllerAdviceBean, ExceptionHandlerMethodResolver> exceptionHandlerAdviceCache = null;

	@Override
	protected ServletInvocableHandlerMethod getExceptionHandlerMethod(HandlerMethod handlerMethod,
			Exception exception) {
		Class<?> handlerType = (handlerMethod != null ? handlerMethod.getBeanType() : null);
		List<ControllerAdviceBean> adviceBeans = ControllerAdviceBean.findAnnotatedBeans(getApplicationContext());
		if (exceptionHandlerAdviceCache == null) {
			exceptionHandlerAdviceCache = new LinkedHashMap<ControllerAdviceBean, ExceptionHandlerMethodResolver>();
			for (ControllerAdviceBean adviceBean : adviceBeans) {
				ExceptionHandlerMethodResolver resolver = new ExceptionHandlerMethodResolver(adviceBean.getBeanType());
				exceptionHandlerAdviceCache.put(adviceBean, resolver);
			}
		}
		for (Map.Entry<ControllerAdviceBean, ExceptionHandlerMethodResolver> entry : this.exceptionHandlerAdviceCache
				.entrySet()) {
			if (entry.getKey().isApplicableToBeanType(handlerType)) {
				ExceptionHandlerMethodResolver resolver = entry.getValue();
				Method method = resolver.resolveMethod(exception);
				if (method != null) {
					return new ServletInvocableHandlerMethod(entry.getKey().resolveBean(), method);
				}
			}
		}
		return null;
	}
}