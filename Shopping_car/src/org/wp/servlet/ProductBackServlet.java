package org.wp.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wp.factory.DAOFactory;
import org.wp.factory.ServerFactory;
import org.wp.vo.Member;
import org.wp.vo.Product;

@WebServlet("/ProductBackServlet")
public class ProductBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getContextPath();				//得到当前目录，即根目录
		request.setAttribute("url", url);
		String pages = "errors.jsp";
		String status = request.getParameter("status");				//取得页面的提交状态，根据提交状态分别做出响应
		if(!(status == null || "".equals("status"))) {
			if("add_cars".equals(status)) {						//若提交的状态是加入购物车
				this.add_cars(request, response); 				//调用执行加入购物车操作
			}
			if("product_cars".equals(status)) {
				this.product_cars(request, response);
			}
			if("order_cars".equals(status)) {
				this.order_cars(request, response);
			}
			if("update_car".equals(status)) {
				this.update_car(request, response);
			}
			if("member_updatepre".equals(status)) {
				this.member_updatepre(request, response);
			}
			if("member_update".equals(status)) {
				this.member_update(request, response);
			}
			if("show".equals(status)) {
				this.show(request, response);
			}
			
		}else {
			request.getRequestDispatcher(pages).forward(request, response);		//跳转到出错页面
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
//============================================================================================================================	
	
	
	
	
	protected void add_cars(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pages = "errors.jsp";
		//要购买的商品编号						负责创建购买商品的集合，和对商品购买数量加一
		int pid = Integer.parseInt(request.getParameter("pid"));
		Map<Integer,Integer> cars = (Map<Integer,Integer>)request.getSession().getAttribute("allpid");	//在session范围内取得全部已经购买的商品
		//但是如果是第一次购买的话肯定取得不到
		if(cars == null){										//实例化一个新的Map对象用来存放购买的商品
			cars = new HashMap<Integer,Integer>();				//创建一个用于存放购买商品的集合
		}
		if(cars.get(pid) == null){						//还没有购买商品，取得商品对应pid的value值
			cars.put(pid, 1);							//此商品购买了一个
		}else{
			int val = cars.get(pid);					//通过点击后传过来的商品的pid（key）取得对应的购买数量（value）
			val++;										//将商品数量加1
			cars.put(pid, val);							//重新将pid放入map集合，覆盖已有内容
		}
		request.getSession().setAttribute("allpid",cars);		//将所购买的商品pid和数量保存在session属性范围内
		pages = "ProductBackServlet?status=product_cars";
		request.getRequestDispatcher(pages).forward(request, response);	
		
	}
	
	
	protected void member_updatepre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pages = "errors.jsp";
		String id = (String)request.getSession().getAttribute("mid");			//取得登录用户的登录ID
		try {
			Member member = DAOFactory.getMemberDAOInstance().findById(id);		//根据ID找到登录的用户信息
			request.setAttribute("member", member);
			pages = "product/member_update.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);		
	}
	
	protected void member_update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pages = "errors.jsp";			//执行修改顾客的登陆信息
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
			flag = DAOFactory.getMemberDAOInstance().doUpdate(mem);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		if(flag){
			msg = "修改用户信息成功！";
		}		
		pages = "product/product_operate_do.jsp";
		request.setAttribute("msg", msg);
		request.getRequestDispatcher(pages).forward(request, response);		
	}
	protected void product_cars(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pages = "errors.jsp";
		Map<Integer,Integer> cars = (Map<Integer,Integer>)request.getSession().getAttribute("allpid");		//取得所购买商品的map集合
		if(cars != null){
			try {
				Set<Integer> key = cars.keySet();		//取得全部的key  pid
				List<Product> all = DAOFactory.getIProductDAOInstance().findAll(key);		//根据pid取得已购买商品
				request.setAttribute("all", all);
				request.setAttribute("cars", cars);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pages = "product/product_cars.jsp";
		}
		
		request.getRequestDispatcher(pages).forward(request, response);		
	}
	protected void order_cars(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pages = "errors.jsp";			//结算购物车
		Map<Integer,Integer> cars = (Map<Integer,Integer>)request.getSession().getAttribute("allpid");		//取得购买的商品集合(保存购买商品的pid和购买数量)
		if(cars != null){
			try {
				double sum = 0;		//用于保存购买的商品的总价钱
				Set<Integer> key = cars.keySet();				//取得全部的key  pid
				String id = (String)request.getSession().getAttribute("mid");
				Map<String,Object> maps = ServerFactory.getIFrontServerDAOInstance().findOrder(id, key);	//取得保存用户信息和商品信息的集合
				Member member = (Member)maps.get("member");		//取得用户信息
				List<Product> pros = (List<Product>)maps.get("product") ;			//取得所有的商品信息
				Iterator<Product> iter = pros.iterator();
				while(iter.hasNext()){		//将查询到的已经购买的商品进行显示
					Product pro = iter.next();	
					sum = sum + pro.getPrice() * cars.get(pro.getPid());
					}
				request.setAttribute("member", member);								//将取得的信息保存在属性范围中
				request.setAttribute("product", pros);
				request.setAttribute("cars", cars); 								//将购物车的所有商品信息保存在属性范围之中，以便在结算页面进行显示购买的数量
				request.setAttribute("sum", sum);
				pages = "product/product_order_list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(pages).forward(request, response);		
	}
	
	
	protected void update_car(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pages = "errors.jsp";			//修改购物车信息
		
		//要购买的商品编号
		Enumeration allpid = request.getParameterNames();		//取得所有的提交数据，即所有的以商品编号为name的input框名字，返回为枚举类型
		Map<Integer,Integer> cars = (Map<Integer,Integer>)request.getSession().getAttribute("allpid");		//取得所有已经购买商品的pid和数量的map集合
		if(cars == null){										//实例化一个新的Map对象
			cars = new HashMap<Integer,Integer>();
		}	
		boolean flag = true;
		while(allpid.hasMoreElements()){	
			String paramName = (String) allpid.nextElement();
			if(flag) {		//因为所取得的参数多了一个status，所以要去除这个多余的参数
				paramName = (String)allpid.nextElement();
				flag = false;
			}
			try{
				int pid = Integer.parseInt(paramName);								//取得pid
				int val = Integer.parseInt(request.getParameter(paramName));		//得到购买的商品件数，即得到提交过来的input框的值
				cars.put(pid, val);													//重新放回集合，覆盖原来相同pid的值
				}catch(Exception e){
				e.printStackTrace();
			}
		}
		pages = "ProductBackServlet?status=product_cars";	
		request.getRequestDispatcher(pages).forward(request, response);		
	}
	
	
	protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pages = "errors.jsp";			//修改购物车信息
		int pid = 0;
		try{
			pid = Integer.parseInt(request.getParameter("pid"));
			Product pro = ServerFactory.getIFrontServerDAOInstance().
					findProduct(pid,(Boolean)request.getSession().getAttribute(pid + "")==null);		//查找到了该商品,防止恶意刷新点击量
			if(pro != null){
				request.getSession().setAttribute(pid+"", false);		//增加之后变为false
			}
			request.setAttribute("pro", pro);			//将查找到的商品保存到request属性范围
			pages = "product/product_show.jsp";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(pages).forward(request, response);		
	}

}
