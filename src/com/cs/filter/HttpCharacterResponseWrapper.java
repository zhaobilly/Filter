package com.cs.filter;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Servlet Filter implementation class HttpCharacterResponseWrapper
 */
public class HttpCharacterResponseWrapper extends HttpServletResponseWrapper {
private CharArrayWriter charArrayWriter=new CharArrayWriter();
	
	public HttpCharacterResponseWrapper(HttpServletResponse response) {
		super(response);
	}
	
	public PrintWriter getWriter() throws IOException{
		return new PrintWriter(charArrayWriter);
	}
	
	public CharArrayWriter getCharArrayWriter(){
		return charArrayWriter;
	}
}
