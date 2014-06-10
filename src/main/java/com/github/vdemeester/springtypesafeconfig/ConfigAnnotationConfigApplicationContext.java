package com.github.vdemeester.springtypesafeconfig;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.PropertySource;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Created by vincent on 10/06/14.
 */
public class ConfigAnnotationConfigApplicationContext extends AnnotationConfigApplicationContext {

	public ConfigAnnotationConfigApplicationContext() {
		super();
		addPropertySource();
	}

	public ConfigAnnotationConfigApplicationContext(DefaultListableBeanFactory beanFactory) {
		super(beanFactory);
		addPropertySource();
	}

	public ConfigAnnotationConfigApplicationContext(Class<?>... annotatedClasses) {
		super();
		addPropertySource();
		register(annotatedClasses);
		refresh();
	}

	public ConfigAnnotationConfigApplicationContext(String... basePackages) {
		super();
		addPropertySource();
		scan(basePackages);
		refresh();
	}

	private void addPropertySource() {
		getEnvironment().getPropertySources().addFirst(getConfigPropertySource());
	}

	private PropertySource<Config> getConfigPropertySource() {
		return new ConfigPropertySource("test", ConfigFactory.load());
	}
}
