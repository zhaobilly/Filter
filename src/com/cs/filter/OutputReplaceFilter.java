package com.cs.filter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class OutputReplaceFilter
 */
public class OutputReplaceFilter implements Filter {

		private Properties pp = new Properties();
		    public OutputReplaceFilter() {
		        // TODO Auto-generated constructor stub
		    }
			public void destroy() {
				// TODO Auto-generated method stub
			}
			public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
				HttpCharacterResponseWrapper response = new HttpCharacterResponseWrapper((HttpServletResponse) res);
				chain.doFilter(req, response);
				String output=response.getCharArrayWriter().toString();
				for(Object obj:pp.keySet()){
					String key=(String)obj;
					output=output.replaceAll(key, pp.getProperty(key));
				}
				PrintWriter out=res.getWriter();
				out.write(output);
				out.println("<!-- Generated at " + new java.util.Date() + " -->");
			}
			public void init(FilterConfig fConfig) throws ServletException {
				String file = fConfig.getInitParameter("file");
				String realPath = fConfig.getServletContext().getRealPath(file);
				try {
					pp.load(new FileInputStream(realPath));
				} catch (IOException e) {
				}
			}

}
