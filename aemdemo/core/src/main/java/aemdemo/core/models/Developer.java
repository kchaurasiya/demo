package aemdemo.core.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;

import aemdemo.core.interfaces.MyService;

public class Developer extends WCMUsePojo {

	Logger logger = LoggerFactory.getLogger(Developer.class);
	protected String detail;

	@Override
	public void activate() throws Exception {
		MyService service = getSlingScriptHelper().getService(MyService.class);
		detail = service.getDeveloperName();
	}

	public String getDetails() {
		return this.detail;
	}
}
