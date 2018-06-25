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
		String url = request.getContextPath();				//�õ���ǰĿ¼������Ŀ¼
		request.setAttribute("url", url);
		String pages = "errors.jsp";
		String status = request.getParameter("status");				//ȡ��ҳ����ύ״̬�������ύ״̬�ֱ�������Ӧ
		if(!(status == null || "".equals("status"))) {
			if("add_cars".equals(status)) {						//���ύ��״̬�Ǽ��빺�ﳵ
				this.add_cars(request, response); 				//����ִ�м��빺�ﳵ����
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
			request.getRequestDispatcher(pages).forward(request, response);		//��ת������ҳ��
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
//============================================================================================================================	
	
	
	
	
	protected void add_cars(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pages = "errors.jsp";
		//Ҫ�������Ʒ���						���𴴽�������Ʒ�ļ��ϣ��Ͷ���Ʒ����������һ
		int pid = Integer.parseInt(request.getParameter("pid"));
		Map<Integer,Integer> cars = (Map<Integer,Integer>)request.getSession().getAttribute("allpid");	//��session��Χ��ȡ��ȫ���Ѿ��������Ʒ
		//��������ǵ�һ�ι���Ļ��϶�ȡ�ò���
		if(cars == null){										//ʵ����һ���µ�Map����������Ź������Ʒ
			cars = new HashMap<Integer,Integer>();				//����һ�����ڴ�Ź�����Ʒ�ļ���
		}
		if(cars.get(pid) == null){						//��û�й�����Ʒ��ȡ����Ʒ��Ӧpid��valueֵ
			cars.put(pid, 1);							//����Ʒ������һ��
		}else{
			int val = cars.get(pid);					//ͨ������󴫹�������Ʒ��pid��key��ȡ�ö�Ӧ�Ĺ���������value��
			val++;										//����Ʒ������1
			cars.put(pid, val);							//���½�pid����map���ϣ�������������
		}
		request.getSession().setAttribute("allpid",cars);		//�����������Ʒpid������������session���Է�Χ��
		pages = "ProductBackServlet?status=product_cars";
		request.getRequestDispatcher(pages).forward(request, response);	
		
	}
	
	
	protected void member_updatepre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pages = "errors.jsp";
		String id = (String)request.getSession().getAttribute("mid");			//ȡ�õ�¼�û��ĵ�¼ID
		try {
			Member member = DAOFactory.getMemberDAOInstance().findById(id);		//����ID�ҵ���¼���û���Ϣ
			request.setAttribute("member", member);
			pages = "product/member_update.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);		
	}
	
	protected void member_update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pages = "errors.jsp";			//ִ���޸Ĺ˿͵ĵ�½��Ϣ
		String msg = "�޸��û���Ϣʧ�ܣ�";
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
			msg = "�޸��û���Ϣ�ɹ���";
		}		
		pages = "product/product_operate_do.jsp";
		request.setAttribute("msg", msg);
		request.getRequestDispatcher(pages).forward(request, response);		
	}
	protected void product_cars(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pages = "errors.jsp";
		Map<Integer,Integer> cars = (Map<Integer,Integer>)request.getSession().getAttribute("allpid");		//ȡ����������Ʒ��map����
		if(cars != null){
			try {
				Set<Integer> key = cars.keySet();		//ȡ��ȫ����key  pid
				List<Product> all = DAOFactory.getIProductDAOInstance().findAll(key);		//����pidȡ���ѹ�����Ʒ
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
		String pages = "errors.jsp";			//���㹺�ﳵ
		Map<Integer,Integer> cars = (Map<Integer,Integer>)request.getSession().getAttribute("allpid");		//ȡ�ù������Ʒ����(���湺����Ʒ��pid�͹�������)
		if(cars != null){
			try {
				double sum = 0;		//���ڱ��湺�����Ʒ���ܼ�Ǯ
				Set<Integer> key = cars.keySet();				//ȡ��ȫ����key  pid
				String id = (String)request.getSession().getAttribute("mid");
				Map<String,Object> maps = ServerFactory.getIFrontServerDAOInstance().findOrder(id, key);	//ȡ�ñ����û���Ϣ����Ʒ��Ϣ�ļ���
				Member member = (Member)maps.get("member");		//ȡ���û���Ϣ
				List<Product> pros = (List<Product>)maps.get("product") ;			//ȡ�����е���Ʒ��Ϣ
				Iterator<Product> iter = pros.iterator();
				while(iter.hasNext()){		//����ѯ�����Ѿ��������Ʒ������ʾ
					Product pro = iter.next();	
					sum = sum + pro.getPrice() * cars.get(pro.getPid());
					}
				request.setAttribute("member", member);								//��ȡ�õ���Ϣ���������Է�Χ��
				request.setAttribute("product", pros);
				request.setAttribute("cars", cars); 								//�����ﳵ��������Ʒ��Ϣ���������Է�Χ֮�У��Ա��ڽ���ҳ�������ʾ���������
				request.setAttribute("sum", sum);
				pages = "product/product_order_list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher(pages).forward(request, response);		
	}
	
	
	protected void update_car(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pages = "errors.jsp";			//�޸Ĺ��ﳵ��Ϣ
		
		//Ҫ�������Ʒ���
		Enumeration allpid = request.getParameterNames();		//ȡ�����е��ύ���ݣ������е�����Ʒ���Ϊname��input�����֣�����Ϊö������
		Map<Integer,Integer> cars = (Map<Integer,Integer>)request.getSession().getAttribute("allpid");		//ȡ�������Ѿ�������Ʒ��pid��������map����
		if(cars == null){										//ʵ����һ���µ�Map����
			cars = new HashMap<Integer,Integer>();
		}	
		boolean flag = true;
		while(allpid.hasMoreElements()){	
			String paramName = (String) allpid.nextElement();
			if(flag) {		//��Ϊ��ȡ�õĲ�������һ��status������Ҫȥ���������Ĳ���
				paramName = (String)allpid.nextElement();
				flag = false;
			}
			try{
				int pid = Integer.parseInt(paramName);								//ȡ��pid
				int val = Integer.parseInt(request.getParameter(paramName));		//�õ��������Ʒ���������õ��ύ������input���ֵ
				cars.put(pid, val);													//���·Żؼ��ϣ�����ԭ����ͬpid��ֵ
				}catch(Exception e){
				e.printStackTrace();
			}
		}
		pages = "ProductBackServlet?status=product_cars";	
		request.getRequestDispatcher(pages).forward(request, response);		
	}
	
	
	protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pages = "errors.jsp";			//�޸Ĺ��ﳵ��Ϣ
		int pid = 0;
		try{
			pid = Integer.parseInt(request.getParameter("pid"));
			Product pro = ServerFactory.getIFrontServerDAOInstance().
					findProduct(pid,(Boolean)request.getSession().getAttribute(pid + "")==null);		//���ҵ��˸���Ʒ,��ֹ����ˢ�µ����
			if(pro != null){
				request.getSession().setAttribute(pid+"", false);		//����֮���Ϊfalse
			}
			request.setAttribute("pro", pro);			//�����ҵ�����Ʒ���浽request���Է�Χ
			pages = "product/product_show.jsp";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(pages).forward(request, response);		
	}

}
