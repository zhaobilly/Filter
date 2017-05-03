package com.helloweenvsfei.gzip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class GZipOutputStream extends ServletOutputStream {

	private HttpServletResponse response;

	// JDK �Դ��ѹ����ݵ���
	private GZIPOutputStream gzipOutputStream;

	// ��ѹ�������ݴ�ŵ� ByteArrayOutputStream ������
	private ByteArrayOutputStream byteArrayOutputStream;

	public GZipOutputStream(HttpServletResponse response) throws IOException {
		this.response = response;
		byteArrayOutputStream = new ByteArrayOutputStream();
		gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
	}

	public void write(int b) throws IOException {
		gzipOutputStream.write(b);
	}

	public void close() throws IOException {

		// ѹ����� һ��Ҫ���ø÷���
		gzipOutputStream.finish();

		// ��ѹ��������������ͻ���
		byte[] content = byteArrayOutputStream.toByteArray();

		// �趨ѹ����ʽΪ GZIP, �ͻ�����������Զ�����ݽ�ѹ
		response.addHeader("Content-Encoding", "gzip");
		response.addHeader("Content-Length", Integer.toString(content.length));

		// ���
		ServletOutputStream out = response.getOutputStream();
		out.write(content);
		out.close();
	}

	public void flush() throws IOException {
		gzipOutputStream.flush();
	}

	public void write(byte[] b, int off, int len) throws IOException {
		gzipOutputStream.write(b, off, len);
	}

	public void write(byte[] b) throws IOException {
		gzipOutputStream.write(b);
	}
}
