package com.helloweenvsfei.gzip;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GZipResponseWrapper extends HttpServletResponseWrapper {

	// Ĭ�ϵ� response
	private HttpServletResponse response;

	// �Զ���� outputStream, ִ��close()��ʱ������ѹ���������
	private GZipOutputStream gzipOutputStream;

	// �Զ��� printWriter������������� GZipOutputStream ��
	private PrintWriter writer;

	public GZipResponseWrapper(HttpServletResponse response) throws IOException {
		super(response);
		this.response = response;
	}

	public ServletOutputStream getOutputStream() throws IOException {
		if (gzipOutputStream == null)
			gzipOutputStream = new GZipOutputStream(response);
		return gzipOutputStream;
	}

	public PrintWriter getWriter() throws IOException {
		if (writer == null)
			writer = new PrintWriter(new OutputStreamWriter(
					new GZipOutputStream(response), "UTF-8"));
		return writer;
	}

	// ѹ������ݳ��Ȼᷢ��仯 ��˽��÷��������ÿ�
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
