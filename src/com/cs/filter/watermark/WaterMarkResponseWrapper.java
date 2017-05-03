package com.cs.filter.watermark;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class WaterMarkResponseWrapper extends HttpServletResponseWrapper{
	private String waterMarkFile;
	private HttpServletResponse response;
	private WaterMarkOutputStream waterMarkOutputStream;
	public WaterMarkResponseWrapper(HttpServletResponse response,String waterMarkFile) {
		super(response);
		this.response=response;
		this.waterMarkFile=waterMarkFile;
		this.waterMarkOutputStream=new WaterMarkOutputStream();
	}
	public ServletOutputStream getOutputStream(){
		return waterMarkOutputStream;
	}
	
	public void flushBuffer() throws IOException{
		waterMarkOutputStream.flush();
	}
	
	public void finishResponse() throws IOException{
		byte[] imageData=waterMarkOutputStream.getByteArrayOutputStream().toByteArray();
		byte[] image=ImageUtil.waterMark(imageData, waterMarkFile);
		
		response.setContentLength(image.length);
		response.getOutputStream().write(image);
		waterMarkOutputStream.close();
	}
}
