package org.wp.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

public class EncodingFilter implements Filter {
	private String charSet;
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(charSet);		//���ñ�������
		chain.doFilter(request, response);			//���ݹ���
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.charSet = fConfig.getInitParameter("charset");		//ȡ�������ļ��еĲ���
	}

}
