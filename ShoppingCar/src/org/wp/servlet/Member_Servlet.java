package org.wp.servlet;

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

@WebServlet("/Member_Servlet")
public class Member_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		if("member_updatepre".equals(status)) {
			this.member_Updatepre(request, response);
		}
		if("member_update".equals(status)) {
			this.member_Update(request, response);
		}
		if("register".equals(status)) {		//进行注册
			this.register(request, response);
		}
		
		if("registerpre".equals(status)) {	//准备注册
			this.registerpre(request, response);
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
//=================================================================================================================	
	
	protected void member_Updatepre(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		//准备修改管理员信息	后台修改
		String pages = "errors.jsp";
		String id = (String)request.getSession().getAttribute("mid");
		try {
			Member mem = DAOFactory.getMemberDAOInstance().findById(id);		//查询到登录人的信息
			request.setAttribute("mem", mem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pages = "product_back/member_update.jsp";
		request.getRequestDispatcher(pages).forward(request, response);
	}
	protected void member_Update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		//修改管理员信息
		String pages = "errors.jsp";
		
		String msg = "修改用户信息失败！";
		
		boolean flag = false;
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telphone");
		String zipcode = request.getParameter("zipcode");
		String id = (String)request.getSession().getAttribute("mid");
		
		Member mem = new Member();
		mem.setName(name);
		mem.setAddress(address);
		mem.setTelephone(telephone);
		mem.setZipcode(zipcode);
		mem.setMid(id);
		
		try {
			flag = DAOFactory.getMemberDAOInstance().doUpdate(mem);		//执行修改
		} catch (Exception e) {
			e.printStackTrace();
		}	
		if(flag){
			msg = "修改用户信息成功！";
		}
		pages = "product_back/member_operate_do.jsp";
		request.setAttribute("msg", msg);
		request.getRequestDispatcher(pages).forward(request, response);
	}
	
	protected void registerpre(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		//准备注册
		String pages = "errors.jsp";

		pages = "login/register.jsp";
		request.getRequestDispatcher(pages).forward(request, response);
	}
	
	protected void register(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		//进行注册  注册到member表 不是admin表，所以注册了也无法登录，需要修改
		String pages = "errors.jsp";
		boolean flag = false;
		
		Member member = new Member();
		member.setMid(request.getParameter("mid"));
		member.setPassword(request.getParameter("password"));
		member.setName(request.getParameter("name"));
		member.setTelephone(request.getParameter("telephone"));
		member.setZipcode(request.getParameter("zipcode"));
		member.setAddress(request.getParameter("address"));
		member.setLastdate(new java.util.Date());
		List<String> errors = new ArrayList<String>();
		try {
			flag = DAOFactory.getMemberDAOInstance().doCreate(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(flag){
			pages = "login/front_login.jsp";
			errors.add("注册成功!请登录");
		}else {
			pages = "login/register.jsp";
			request.setAttribute("msg", "注册失败,请重新注册！");
		}
		request.setAttribute("errors", errors);
		request.getRequestDispatcher(pages).forward(request, response);
	}

}
