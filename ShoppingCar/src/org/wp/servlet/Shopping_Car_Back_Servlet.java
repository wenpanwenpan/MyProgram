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
			throws ServletException, IOException {			//�г����е���Ʒ��Ϣ
		String pages = "errors.jsp";							//�����������Ĭ����ת�������ҳ��
		int currentPage = 1;								//Ϊ��ǰ���ڵ�ҳ��Ĭ���ڵ�һҳ
		int lineSize = 5;									//	ÿҳ��ʾ������
		long allRecorders = 0 ; 							//	��ʾȫ���ļ�¼��
		List<Product> all = null;
		String keyword = request.getParameter("kw"); //���ղ�ѯ�ؼ���
		if(keyword == null){
			keyword = "";
		}
		IProductDAO dao = DAOFactory.getIProductDAOInstance();					//��Ʒ������
		
		try {
			all = dao.findAll(keyword, currentPage, lineSize);	//ȡ�����е���Ʒ��Ϣ
			allRecorders = dao.getAllCount(keyword);							//��Ʒ�ļ�¼����
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		request.setAttribute("ls", lineSize);
		request.setAttribute("cp", currentPage);
		request.setAttribute("allRecorders", allRecorders);
		request.setAttribute("all", all);
		request.getRequestDispatcher(pages).forward(request, response); 			//ִ��ҳ�����ת
	}
//=======================================================================================================================

	protected void update_Product(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {			//�޸Ĳ�Ʒ��Ϣ
		String pages = "errors.jsp";							//�����������Ĭ����ת�������ҳ��
		String msg = "�޸���Ʒ��Ϣʧ�ܣ�";
		
		SmartUpload smart = new SmartUpload();			//ʵ����SmartUpload�ϴ����
		smart.initialize(this.getServletConfig(),request,response);					//��ʼ���ϴ�����  ������һ��
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
		if(smart.getFiles().getSize() > 0){		//���ļ��ϴ�
			IPTimeStamp its = new IPTimeStamp("192.168.1.1");		//IP��ַʱ���������Զ�Ϊ�ϴ��ļ�����
			fileName = its.getIPTimeRand() + "." + smart.getFiles().getFile(0).getFileExt();		//ƴ���ļ�����
			filePath = this.getServletContext().getRealPath("/") + "productimg" + 
			File.separator + fileName;		//�����ϴ����ļ�·��
			
			pro.setPhoto(fileName);			//���ļ����ƽ��б��浽���ݿ�
			
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
					smart.getFiles().getFile(0).saveAs(filePath);	//�ļ��ϴ���ָ�����ļ�����
				} catch (SmartUploadException e) {
					e.printStackTrace();
				}		
			}
			msg = "�޸���Ʒ��Ϣ�ɹ���";
			pages = "product_back/product_operate_do.jsp";		//��Ҫ�޸�
		}
		request.setAttribute("msg", msg);
		request.getRequestDispatcher(pages).forward(request, response); 			//ִ��ҳ�����ת
	}
//=======================================================================================================================

	protected void updatepre_Product(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {			//׼���޸Ĳ�Ʒ��Ϣ
		String pages = "errors.jsp";							//�����������Ĭ����ת�������ҳ��
		int id = Integer.parseInt(request.getParameter("pid"));
		Product pro = null;
		try {
			pro = DAOFactory.getIProductDAOInstance().findById(id);//�����ύ������ID�ҵ���Ʒ��Ϣ
			pages = "product_back/update_product.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}				
		request.setAttribute("pro", pro);
		
		request.getRequestDispatcher(pages).forward(request, response); 			//ִ��ҳ�����ת
	}
//=======================================================================================================================
	protected void add_Productpre(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {			//���һ������Ʒ
		String pages = "errors.jsp";							//�����������Ĭ����ת�������ҳ��
		pages = "product_back/add_product.jsp";
		request.getRequestDispatcher(pages).forward(request, response); 			//ִ��ҳ�����ת
	}
	
//=======================================================================================================================

	protected void add_Product(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {			//���һ������Ʒ
		String pages = "errors.jsp";							//�����������Ĭ����ת�������ҳ��

		SmartUpload smart = new SmartUpload();			//ʵ����SmartUpload�ϴ����
		smart.initialize(this.getServletConfig(),request,response);					//��ʼ���ϴ�����
		try {
			smart.upload();			//�ϴ�׼��
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}									
		
		String name = smart.getRequest().getParameter("name");	
		String note = smart.getRequest().getParameter("note");
		double price = Double.parseDouble(smart.getRequest().getParameter("price"));
		int amount = Integer.parseInt(smart.getRequest().getParameter("amount"));

		String msg = "��Ʒ��Ϣ����ʧ��";
		Product pro = new Product();
		pro.setAmount(amount);
		pro.setName(name);
		pro.setPrice(price);
		pro.setNote(note);
	//=============================================================================================	
		String fileName = null;
		String filePath = null;
		if(smart.getFiles().getSize() > 0){		//���ļ��ϴ�
			IPTimeStamp its = new IPTimeStamp("192.168.1.1");
			fileName = its.getIPTimeRand() + "." + smart.getFiles().getFile(0).getFileExt();		//ƴ���ļ�����
			filePath = this.getServletContext().getRealPath("/") + "productimg" + 
			File.separator + fileName;		//�����ϴ����ļ�·��
			pro.setPhoto(fileName);			//���ļ����ƽ��б��浽���ݿ�
		}else{
			pro.setPhoto("nophoto.jpg");
		}

		try {
			if(DAOFactory.getIProductDAOInstance().doCreate(pro)){
				if(smart.getFiles().getSize() > 0){
					smart.getFiles().getFile(0).saveAs(filePath);		//�ļ��ϴ���ָ�����ļ�����
				}
				msg = "��Ʒ���ӳɹ���";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("msg", msg);
		pages = "product_back/add_operate_do.jsp";
		request.getRequestDispatcher(pages).forward(request, response); 			//ִ��ҳ�����ת
	}
//=======================================================================================================================

	protected void delete_Product(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {			//��ӵ����ﳵ
		String pages = "errors.jsp";							//�����������Ĭ����ת�������ҳ��
		String msg = "ɾ����Ʒ��Ϣʧ��";
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
				java.io.File file = new  File(filePath);			//����·������һ���ļ�
				if(file.exists()){
					file.delete();
				}
			}
			msg = "ɾ����Ʒ��Ϣ�ɹ���";
			pages = "product_back/product_operate_do.jsp";
		}
	}
		request.setAttribute("msg", msg);	
		request.getRequestDispatcher(pages).forward(request, response); 			//ִ��ҳ�����ת
	}
//=======================================================================================================================

	protected void show(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {			//��ʾһ����Ʒ�ľ�����Ϣ
		String pages = "errors.jsp";							//�����������Ĭ����ת�������ҳ��
		
		int pid = 0;
		Product pro = null;
		try {
			pid = Integer.parseInt(request.getParameter("pid"));
			pro = ServerFactory.getIFrontServerDAOInstance().findProduct(pid,(Boolean)request.
					getSession().getAttribute(pid + "")==null);//���ҵ��˸���Ʒ,��ֹ����ˢ�µ����
			request.getSession().setAttribute(pid+"", false);		//����֮���Ϊfalse
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(pro != null) {
			pages = "product_back/product_show.jsp";
		}
		request.setAttribute("pro", pro);
		request.getRequestDispatcher(pages).forward(request, response); 			//ִ��ҳ�����ת
	}

}
