package org.wp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/product/*") 
public class AdminLoginFilter implements Filter {			//������������
	@Override
	public void destroy() {	

	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		if(request.getSession().getAttribute("admin") != null) {		//�Ѿ���¼����
			arg2.doFilter(arg0, arg1);
		}else {
			request.setAttribute("msg", "����û�е�¼�����ȵ�¼��");
			request.getRequestDispatcher("forward.jsp").forward(arg0, arg1);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
