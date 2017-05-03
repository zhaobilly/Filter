package com.cs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
	private String characterEncoding;
	private boolean enabled;
	@Override
	public void destroy() {
		characterEncoding=null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		if(enabled||characterEncoding!=null){
			req.setCharacterEncoding(characterEncoding);
			resp.setCharacterEncoding(characterEncoding);
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		characterEncoding =config.getInitParameter("characterEncoding");
		enabled="true".equals(config.getInitParameter("enabled").trim());
	}

}
