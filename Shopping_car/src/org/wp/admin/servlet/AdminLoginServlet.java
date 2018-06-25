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
	
	String pages = "login/login.jsp";				//要跳转的页面
	boolean flag = false;				//判断是否登录成功
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");				//取得登录ID
		String password = request.getParameter("password");		//取得登录密码
		List<String> errors = new ArrayList<String>();
		if(mid == null || "".equals(mid)) {
			errors.add("登录ID不能为空！");
		}
		if(password == null || "".equals(password)) {
			errors.add("登录密码不能为空！");
		}
		if(errors.size() == 0) {			//验证登录
			try {
				Admin admin = new Admin();
				admin.setAdminid(mid);
				admin.setPassword(password);			
				if(DAOFactory.getIAdminDAOInstance().findLogin(admin)) {		//如果该用户存在于数据库
					request.getSession().setAttribute("admin", admin); 			//保存已经登录的admin对象
					request.getSession().setAttribute("mid", mid); 				//保存登录用户的mid
					pages = "product/welcome.jsp";
				}else {
					errors.add("登录用户名或密码错误");
					pages = "login/login.jsp";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("errors", errors);
		request.getRequestDispatcher(pages).forward(request, response);	//执行跳转		
		
	}
}
