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
@WebServlet("/AdminLogout_Front_Servlet")
public class AdminLogout_Front_Servlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pages = "login/front_login.jsp";
		request.getSession().invalidate();
		request.getRequestDispatcher(pages).forward(request, response);
	}
}
