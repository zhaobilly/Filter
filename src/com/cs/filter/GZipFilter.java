package com.cs.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspbook.GZIPResponseWrapper;

/**
 * Servlet Filter implementation class GZipFilter
 */
public class GZipFilter implements Filter {

    /**
     * Default constructor. 
     */
    public GZipFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		String acceptEncoding=req.getHeader("Accept-Encoding");
		System.out.println("Accept-Encoding: " + acceptEncoding);
		if(acceptEncoding!=null&&acceptEncoding.indexOf("gizp")!=-1){
			GZIPResponseWrapper gzipResponseWrapper=new GZIPResponseWrapper(res);
			chain.doFilter(req,gzipResponseWrapper);
			gzipResponseWrapper.finishResponse();
		}else{
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
