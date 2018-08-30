package br.gov.ba.inema.seiaauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class SeiaAuthServerApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SeiaAuthServerApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SeiaAuthServerApplication.class);
	}
}
