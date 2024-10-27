package org.nic.bug_tracker_system.config;

import java.awt.image.BufferedImage;
import java.util.List;

import org.nic.bug_tracker_system.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@Autowired
	AppConfigService appConfigServ;
	
	@Bean(name="categories")
	List<?> getCategory() {
		return appConfigServ.categories();
	}
	
	@Bean(name="priorities")
	List<?> getPriority() {
		return appConfigServ.priorities();
	}
	
	@Bean(name="reproducibilites")
	List<?> getReproducibility() {
		return appConfigServ.reproducibilites();
	}
	
	@Bean(name="severities")
	List<?> getSeverity() {
		return appConfigServ.severities();
	}
	
	@Bean(name="assignTo")
	List<?> getAssignTo(){
		return appConfigServ.assignTo();
	}
	
	@Bean
	public RestTemplate testTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
		return new BufferedImageHttpMessageConverter();
	}
}
