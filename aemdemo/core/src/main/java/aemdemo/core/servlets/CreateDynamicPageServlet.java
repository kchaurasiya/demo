package aemdemo.core.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.jcr.ItemExistsException;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=Dynamic page servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/aemdemo",
        "sling.servlet.extensions=" + "html"
})
public class CreateDynamicPageServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;
	//private Logger log = (Logger) LoggerFactory.getLogger(CreateDynamicPageServlet.class);
	private ResourceResolver resourceResolver;
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doGet(request, response);
		//log.info("Calling Servlet ++++++++++++++++");
		String path ="/content/poc";
		String pageName="samplePage";
		String pageTitle ="Sample Page";
		String template ="/apps/aemdemo/templates/page-content";
		//String template = "/apps/geometrixx/templates/homepage";
		// String renderer = "/apps/aemdemo/components/structure/page-hsbc";
		
		resourceResolver = request.getResourceResolver();
		
		// Create Page

        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        
		 try {
			 Page pageNode = null;
			pageNode = pageManager.create(path, pageName, template, pageTitle);
			//log.info("Page created..!!!"+pageNode);
			Node jcrNode=null;
			jcrNode = pageNode.getContentResource().adaptTo(Node.class);
			//log.info("jcrNode"+jcrNode);
			Node parNode = jcrNode.addNode("par");
			parNode.setProperty("sling:resourceType", "/foundation/components/parsys");
			Node textNode = parNode.addNode("text");
			textNode.setProperty("sling:resourceType", "/foundation/components/text");

            textNode.setProperty("textIsRich", "true");

            textNode.setProperty("text", "Welcome to the dynamic page ");
			
		} catch (WCMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PathNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VersionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
       
       // log.info("Page created");
       // Node jcrNode = null;
		
	}
	
	
}
