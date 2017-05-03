package com.cs.filter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.helloweenvsfei.gzip.GZipOutputStream;

public class GZipResponseWrapper extends HttpServletResponseWrapper {
	private HttpServletResponse response;
	private GZipOutputStream gzipOutputStream;
	private PrintWriter writer;
	
	public GZipResponseWrapper(HttpServletResponse response) {
		super(response);
		this.response=response;
	}
	
	public ServletOutputStream getOutputStream(){
		if(gzipOutputStream==null)
			try {
				gzipOutputStream=new GZipOutputStream(response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return gzipOutputStream;
	}
	
	public PrintWriter getWriter() throws IOException {
		if (writer == null)
			writer = new PrintWriter(new OutputStreamWriter(
					new GZipOutputStream(response), "UTF-8"));
		return writer;
	}
	
	public void setContentLength(int contentLength) {
	}

	public void flushBuffer() throws IOException {
		gzipOutputStream.flush();
	}

	public void finishResponse() throws IOException {
		if (gzipOutputStream != null)
			gzipOutputStream.close();
		if (writer != null)
			writer.close();
	}
}
