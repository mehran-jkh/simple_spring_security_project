package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class dispatcherservletconfig extends AbstractAnnotationConfigDispatcherServletInitializer
{
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {mainconfig.class, security_config.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[0];
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
}
