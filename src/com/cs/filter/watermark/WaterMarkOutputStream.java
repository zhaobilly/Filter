package com.cs.filter.watermark;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;

public class WaterMarkOutputStream extends ServletOutputStream{
	private ByteArrayOutputStream byteArrayOutputStream;
	
	public WaterMarkOutputStream(){
		byteArrayOutputStream=new ByteArrayOutputStream();
	}
	
	
	@Override
	public void write(int b) throws IOException {
		byteArrayOutputStream.write(b);
	}
	
	public void close() throws IOException{
		byteArrayOutputStream.close();
	}
	
	public void flush() throws IOException{
		byteArrayOutputStream.flush();
	}
	
	public void write(byte[]b,int off,int len){
		byteArrayOutputStream.write(b, off, len);
	}
	public void write(byte[]b) throws IOException{
		byteArrayOutputStream.write(b);
	}
	
	public ByteArrayOutputStream getByteArrayOutputStream(){
		return byteArrayOutputStream;
	}
	
	
}
