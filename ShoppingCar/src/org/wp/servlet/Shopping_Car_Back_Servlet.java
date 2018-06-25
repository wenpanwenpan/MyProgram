package org.wp.servlet;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxh.smart.SmartUpload;
import org.lxh.smart.SmartUploadException;
import org.wp.dao.IProductDAO;
import org.wp.factory.DAOFactory;
import org.wp.factory.ServerFactory;
import org.wp.utils.IPTimeStamp;
import org.wp.vo.Product;

@WebServlet("/Shopping_Car_Back_Servlet")
public class Shopping_Car_Back_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String status = request.getParameter("status");
		if("list".equals(status)) {
			this.list(request, response);
		}
		if("updatepre_Product".equals(status)) {
			this.updatepre_Product(request, response);
			
		}
		if("update_Product".equals(status)) {
			this.update_Product(request, response);
		}
		if("delete_Product".equals(status)) {
			this.delete_Product(request, response);		
		}
		if("add_Productpre".equals(status)) {
			this.add_Productpre(request, response);		
		}
		if("add_Product".equals(status)) {
			this.add_Product(request, response);		
		}
		if("show".equals(status)) {
			this.show(request, response);
		}
		

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

//=======================================================================================================================
	
	protected void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {			//列出所有的商品信息
		String pages = "errors.jsp";							//若程序出错则默认跳转到错误的页面
		int currentPage = 1;								//为当前所在的页，默认在第一页
		int lineSize = 5;									//	每页显示的条数
		long allRecorders = 0 ; 							//	表示全部的记录数
		List<Product> all = null;
		String keyword = request.getParameter("kw"); //接收查询关键字
		if(keyword == null){
			keyword = "";
		}
		IProductDAO dao = DAOFactory.getIProductDAOInstance();					//产品代理类
		
		try {
			all = dao.findAll(keyword, currentPage, lineSize);	//取得所有的商品信息
			allRecorders = dao.getAllCount(keyword);							//商品的记录条数
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		request.setAttribute("ls", lineSize);
		request.setAttribute("cp", currentPage);
		request.setAttribute("allRecorders", allRecorders);
		request.setAttribute("all", all);
		request.getRequestDispatcher(pages).forward(request, response); 			//执行页面的跳转
	}
//=======================================================================================================================

	protected void update_Product(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {			//修改产品信息
		String pages = "errors.jsp";							//若程序出错则默认跳转到错误的页面
		String msg = "修改商品信息失败！";
		
		SmartUpload smart = new SmartUpload();			//实例化SmartUpload上传组件
		smart.initialize(this.getServletConfig(),request,response);					//初始化上传操作  参数不一样
		try {
			smart.upload();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}	
		
		boolean flag = false;	
		
		Product pro = new Product();
		pro.setPid(Integer.parseInt(smart.getRequest().getParameter("pid")));
		pro.setName(smart.getRequest().getParameter("name"));
		pro.setPrice(Double.parseDouble(smart.getRequest().getParameter("price")));
		pro.setAmount(Integer.parseInt(smart.getRequest().getParameter("amount")));
		pro.setNote(smart.getRequest().getParameter("note"));
		
		String fileName = null;
		String filePath = null;
		if(smart.getFiles().getSize() > 0){		//有文件上传
			IPTimeStamp its = new IPTimeStamp("192.168.1.1");		//IP地址时间戳随机数自动为上传文件命名
			fileName = its.getIPTimeRand() + "." + smart.getFiles().getFile(0).getFileExt();		//拼凑文件名称
			filePath = this.getServletContext().getRealPath("/") + "productimg" + 
			File.separator + fileName;		//设置上传的文件路径
			
			pro.setPhoto(fileName);			//将文件名称进行保存到数据库
			
		}else{
			pro.setPhoto("nophoto.jpg");
		}
		
		try {
			flag = DAOFactory.getIProductDAOInstance().doUpdate(pro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(flag){
			if(smart.getFiles().getSize() > 0){
				try {
					smart.getFiles().getFile(0).saveAs(filePath);	//文件上传到指定的文件夹中
				} catch (SmartUploadException e) {
					e.printStackTrace();
				}		
			}
			msg = "修改商品信息成功！";
			pages = "product_back/product_operate_do.jsp";		//需要修改
		}
		request.setAttribute("msg", msg);
		request.getRequestDispatcher(pages).forward(request, response); 			//执行页面的跳转
	}
//=======================================================================================================================

	protected void updatepre_Product(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {			//准备修改产品信息
		String pages = "errors.jsp";							//若程序出错则默认跳转到错误的页面
		int id = Integer.parseInt(request.getParameter("pid"));
		Product pro = null;
		try {
			pro = DAOFactory.getIProductDAOInstance().findById(id);//根据提交过来的ID找到商品信息
			pages = "product_back/update_product.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}				
		request.setAttribute("pro", pro);
		
		request.getRequestDispatcher(pages).forward(request, response); 			//执行页面的跳转
	}
//=======================================================================================================================
	protected void add_Productpre(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {			//添加一个新商品
		String pages = "errors.jsp";							//若程序出错则默认跳转到错误的页面
		pages = "product_back/add_product.jsp";
		request.getRequestDispatcher(pages).forward(request, response); 			//执行页面的跳转
	}
	
//=======================================================================================================================

	protected void add_Product(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {			//添加一个新商品
		String pages = "errors.jsp";							//若程序出错则默认跳转到错误的页面

		SmartUpload smart = new SmartUpload();			//实例化SmartUpload上传组件
		smart.initialize(this.getServletConfig(),request,response);					//初始化上传操作
		try {
			smart.upload();			//上传准备
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}									
		
		String name = smart.getRequest().getParameter("name");	
		String note = smart.getRequest().getParameter("note");
		double price = Double.parseDouble(smart.getRequest().getParameter("price"));
		int amount = Integer.parseInt(smart.getRequest().getParameter("amount"));

		String msg = "商品信息增加失败";
		Product pro = new Product();
		pro.setAmount(amount);
		pro.setName(name);
		pro.setPrice(price);
		pro.setNote(note);
	//=============================================================================================	
		String fileName = null;
		String filePath = null;
		if(smart.getFiles().getSize() > 0){		//有文件上传
			IPTimeStamp its = new IPTimeStamp("192.168.1.1");
			fileName = its.getIPTimeRand() + "." + smart.getFiles().getFile(0).getFileExt();		//拼凑文件名称
			filePath = this.getServletContext().getRealPath("/") + "productimg" + 
			File.separator + fileName;		//设置上传的文件路径
			pro.setPhoto(fileName);			//将文件名称进行保存到数据库
		}else{
			pro.setPhoto("nophoto.jpg");
		}

		try {
			if(DAOFactory.getIProductDAOInstance().doCreate(pro)){
				if(smart.getFiles().getSize() > 0){
					smart.getFiles().getFile(0).saveAs(filePath);		//文件上传到指定的文件夹中
				}
				msg = "商品增加成功！";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("msg", msg);
		pages = "product_back/add_operate_do.jsp";
		request.getRequestDispatcher(pages).forward(request, response); 			//执行页面的跳转
	}
//=======================================================================================================================

	protected void delete_Product(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {			//添加到购物车
		String pages = "errors.jsp";							//若程序出错则默认跳转到错误的页面
		String msg = "删除商品信息失败";
		boolean flag = false;
		if(request.getParameter("pid") != "" && request.getParameter("pid") != null){
		int pid = Integer.parseInt(request.getParameter("pid"));
		String pic = request.getParameter("pic");
		try {
			flag = DAOFactory.getIProductDAOInstance().doRemove(pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(flag){
			if(!"nophoto.jpg".equals(pic)){
				String filePath = this.getServletContext().getRealPath("/") + "productimg" + 
			java.io.File.separator + pic;
				java.io.File file = new  File(filePath);			//根据路径创建一个文件
				if(file.exists()){
					file.delete();
				}
			}
			msg = "删除商品信息成功！";
			pages = "product_back/product_operate_do.jsp";
		}
	}
		request.setAttribute("msg", msg);	
		request.getRequestDispatcher(pages).forward(request, response); 			//执行页面的跳转
	}
//=======================================================================================================================

	protected void show(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {			//显示一个商品的具体信息
		String pages = "errors.jsp";							//若程序出错则默认跳转到错误的页面
		
		int pid = 0;
		Product pro = null;
		try {
			pid = Integer.parseInt(request.getParameter("pid"));
			pro = ServerFactory.getIFrontServerDAOInstance().findProduct(pid,(Boolean)request.
					getSession().getAttribute(pid + "")==null);//查找到了该商品,防止恶意刷新点击量
			request.getSession().setAttribute(pid+"", false);		//增加之后变为false
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(pro != null) {
			pages = "product_back/product_show.jsp";
		}
		request.setAttribute("pro", pro);
		request.getRequestDispatcher(pages).forward(request, response); 			//执行页面的跳转
	}

}
