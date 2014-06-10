package com.github.vdemeester.springtypesafeconfig;

import org.springframework.core.env.PropertySource;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;

/**
 * Created by vincent on 10/06/14.
 */
public class ConfigPropertySource extends PropertySource<Config> {

	public ConfigPropertySource(String name, Config config) {
		super(name, config);
	}

	@Override
	public Object getProperty(String name) {
		try {
			return getSource().getAnyRef(name);
		} catch (ConfigException e) {
			return null;
		}
	}
}
