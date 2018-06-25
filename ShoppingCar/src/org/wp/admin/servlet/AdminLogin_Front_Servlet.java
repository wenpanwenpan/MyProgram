package org.wp.admin.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wp.factory.DAOFactory;
import org.wp.vo.Member;
@WebServlet("/AdminLogin_Front_Servlet")
public class AdminLogin_Front_Servlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	String pages = "login/front_login.jsp";				//Ҫ��ת��ҳ��
	boolean flag = false;				//�ж��Ƿ��¼�ɹ�
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");				//ȡ�õ�¼ID
		String password = request.getParameter("password");		//ȡ�õ�¼����
		List<String> errors = new ArrayList<String>();
		if(mid == null || "".equals(mid)) {
			errors.add("��¼ID����Ϊ�գ�");
		}
		if(password == null || "".equals(password)) {
			errors.add("��¼���벻��Ϊ�գ�");
		}
		if(errors.size() == 0) {			//��֤��¼
			try {
				Member mem = new Member();		//��¼���û�
				mem.setMid(mid);
				mem.setPassword(password);
				if(DAOFactory.getMemberDAOInstance().findLogin(mem)) {		//������û����������ݿ� member ��
					request.getSession().setAttribute("admin", mem); 			//�����Ѿ���¼��member����
					request.getSession().setAttribute("mid", mid); 				//�����¼�û���mid
					pages = "product_front/welcome.jsp";
				}else {
					errors.add("��¼�û������������");
					pages = "login/front_login.jsp";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("errors", errors);
		request.getRequestDispatcher(pages).forward(request, response);	//ִ����ת		
		
	}
}
