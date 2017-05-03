package com.cs.filter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class PriviledgerFilter
 */
public class PriviledgerFilter implements Filter {
	private Properties pp=new Properties();
	
    public PriviledgerFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		pp=null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String requestURI=request.getRequestURI().replaceAll(request.getContextPath()+"/", "");
		String action =req.getParameter("action");
		action=action==null?"":action;
		String uri=requestURI+"?action="+action;
		String role=(String)request.getSession(true).getAttribute("role");
		role=role==null?"guest":role;
		boolean authentificated=false;
		for(Object obj:pp.keySet()){
			String key=((String)obj);
			if(uri.matches(key.replace("?", "\\?").replace(".", "\\.").replace("*", "."))){
				if(role.equals(pp.get(key))){
					authentificated=true;
					break;
				}
			}
		}
		if(!authentificated){
			throw new RuntimeException(new AccountException("您无权访问该页面，请登录后查看"));
		}
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		String file =fConfig.getInitParameter("file");
		String realPath=fConfig.getServletContext().getRealPath(file);
		try{
			pp.load(new FileInputStream(realPath));
		}catch(Exception e){
			fConfig.getServletContext().log("读取权限控制文件失败。", e);
		}
	}

}
