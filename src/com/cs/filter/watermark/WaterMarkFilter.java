package com.cs.filter.watermark;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class WaterMarkFilter
 */
public class WaterMarkFilter implements Filter {

	private String waterMarkFile;
    public WaterMarkFilter() {
        // TODO Auto-generated constructor stub
    }
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request =(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		
		WaterMarkResponseWrapper waterMarkRes=new WaterMarkResponseWrapper(response,waterMarkFile);
		
		chain.doFilter(request, waterMarkRes);
		waterMarkRes.finishResponse();
	}
	public void init(FilterConfig fConfig) throws ServletException {
		String file=fConfig.getInitParameter("waterMarkFile");
		waterMarkFile=fConfig.getServletContext().getRealPath(file);
	}
}
