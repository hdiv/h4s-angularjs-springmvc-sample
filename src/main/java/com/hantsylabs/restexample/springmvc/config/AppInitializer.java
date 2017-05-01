package com.hantsylabs.restexample.springmvc.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.hdiv.filter.ValidatorFilter;
import org.hdiv.listener.InitListener;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Order(0)
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class, //
				DataSourceConfig.class, //
				JpaConfig.class, //
				DataJpaConfig.class, //
				SecurityConfig.class, //
				Jackson2ObjectMapperConfig.class, //
				MessageSourceConfig.class, DelegateConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class, //
				SwaggerConfig.class //
		};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);

		return new Filter[] { encodingFilter };
	}

	@Override
	public void onStartup(final ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);

		servletContext.addFilter("ValidatorFilter", ValidatorFilter.class).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),
				false, "/*");

		servletContext.addListener(new InitListener());

		servletContext.addListener(new RequestContextListener());
	}
}
