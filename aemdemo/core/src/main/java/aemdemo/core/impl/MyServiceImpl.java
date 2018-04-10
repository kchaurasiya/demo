package aemdemo.core.impl;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aemdemo.core.interfaces.MyService;

@Component(service=MyService.class)
public class MyServiceImpl implements MyService {
	Logger logger = LoggerFactory.getLogger(MyService.class);

	@Override
	public String getDeveloperName() {
		return "Neetu";
	}
}
