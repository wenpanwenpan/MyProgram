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
import org.wp.vo.Admin;
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	String pages = "login/login.jsp";				//Ҫ��ת��ҳ��
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
				Admin admin = new Admin();
				admin.setAdminid(mid);
				admin.setPassword(password);			
				if(DAOFactory.getIAdminDAOInstance().findLogin(admin)) {		//������û����������ݿ�
					request.getSession().setAttribute("admin", admin); 			//�����Ѿ���¼��admin����
					request.getSession().setAttribute("mid", mid); 				//�����¼�û���mid
					pages = "product/welcome.jsp";
				}else {
					errors.add("��¼�û������������");
					pages = "login/login.jsp";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("errors", errors);
		request.getRequestDispatcher(pages).forward(request, response);	//ִ����ת		
		
	}
}
