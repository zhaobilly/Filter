package com.cs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class ExceptionHandlerFilter
 */
public class ExceptionHandlerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ExceptionHandlerFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try{
			chain.doFilter(request, response);
		}catch(Exception e){
			Throwable rootCause=e;
			while(rootCause.getCause()!=null){
				rootCause=rootCause.getCause();
			}
			
			String message=rootCause.getMessage();
			message=message==null?"异常:"+rootCause.getClass().getName():message;
			
			request.setAttribute("message", message);
			request.setAttribute("e", e);
			if(rootCause instanceof AccountException){
				request.getRequestDispatcher("/accountException.jsp").forward(request, response);
			}else if(rootCause instanceof BusinessException){
				request.getRequestDispatcher("/businessException.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/exception.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
