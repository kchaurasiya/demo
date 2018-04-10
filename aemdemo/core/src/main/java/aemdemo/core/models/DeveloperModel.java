package aemdemo.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import aemdemo.core.interfaces.MyService;

@Model(adaptables=Resource.class)
public class DeveloperModel {

	private String message;
	
	@Inject
	private MyService myService;
	
	@PostConstruct
	protected void init() {
		message = myService.getDeveloperName();
	}

	public String getMessage() {
		return message;
	}	
	
}