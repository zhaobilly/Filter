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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet Filter implementation class LogFilter
 */
public class LogFilter implements Filter {

	private Log log=LogFactory.getLog(this.getClass());
	private String filterName;
    /**
     * Default constructor. 
     */
    public LogFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		log.info("关闭filter:"+filterName);
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)resp;
		long startTime=System.currentTimeMillis();
		String requestURI=request.getRequestURI();
		requestURI=request.getQueryString()==null?requestURI:(requestURI+"?"+request.getQueryString());
		
		chain.doFilter(request, response);
		
		long endTime=System.currentTimeMillis();
		log.info(request.getRemoteAddr()+"访问了"+requestURI+",总用时"+(endTime-startTime)+"毫秒.");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		filterName=fConfig.getFilterName();
		log.info("启动 Filter:"+filterName);
	}

}
