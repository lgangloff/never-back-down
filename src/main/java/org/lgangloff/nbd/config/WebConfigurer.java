package org.lgangloff.nbd.config;

import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer, ServletContextInitializer {

    private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    @Autowired
    private Environment env;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        log.info("Web application configuration, using profiles: {}", Arrays.toString(env.getActiveProfiles()));
        servletContext.addListener(RequestContextListener.class);
        log.info("Web application fully configured");
    }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/app/**").addResourceLocations("classpath://app/");
        registry.addResourceHandler("/*.bundle.js").addResourceLocations("classpath://app/");
	}
    
	@Override
    public void addViewControllers(ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/index.html" );
        registry.addViewController( "/app/" ).setViewName( "forward:/app/index.html" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
    }
    

}
