package br.org.fgp.core;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextConfig {

	private static final ClassPathXmlApplicationContext CONTEXT = new ClassPathXmlApplicationContext("META-INF/spring-config.xml");

	public ApplicationContextConfig() {
	}
	public static ApplicationContext getContext(){
		Locale.setDefault(new Locale("pt","BR"));
		return CONTEXT;
	}
	
}
