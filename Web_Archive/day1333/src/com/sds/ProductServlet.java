package com.sds;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.frame.Biz;
import com.oreilly.servlet.MultipartRequest;
import com.product.ProductBiz;
import com.vo.Product;

@WebServlet({ "/ProductServlet", "/product" })
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Biz<Integer, Product> biz;

	public ProductServlet() {
		super();

		biz = new ProductBiz();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String nextPage = "";

		if (cmd.equals("") || cmd == null) {

		}

		else if (cmd.equals("add")) {
			nextPage = "product/add";
		}

		else if (cmd.equals("addimpl")) {
			MultipartRequest mr = new MultipartRequest(request, "C:\\LimJi\\day1333\\web\\img", 1024 * 1024 * 100,
					"UTF-8");

			String name = mr.getParameter("name");
			String price = mr.getParameter("price");
			String imgname = mr.getOriginalFileName("imgname");

			Product p = new Product(name, Integer.parseInt(price), imgname);

			try {
				biz.register(p);

				request.setAttribute("pname", name);
				nextPage = "product/pok";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				nextPage = "product/pfail";
			}
		}

		else if (cmd.equals("list")) {
			ArrayList<Product> ps = null;

			try {
				ps = biz.get();

				request.setAttribute("plist", ps);
				nextPage = "product/list";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if (cmd.equals("detail")) {
			String id = request.getParameter("id");
			Product p = null;
			
			try {
				p = biz.get(Integer.parseInt(id));
				
				request.setAttribute("pDetail", p);
				nextPage = "product/detail";
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if (cmd.equals("delete")) {
			String id = request.getParameter("id");
			
			try {
				biz.remove(Integer.parseInt(id));
				
				response.sendRedirect("ask?type=product&cmd=list");
				return;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if (cmd.equals("update")) {
			String id = request.getParameter("id");
			Product p = null;
			
			try {
				p = biz.get(Integer.parseInt(id));

				request.setAttribute("pUpdate", p);
				nextPage = "product/update";
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if (cmd.equals("updateimpl")) {
			MultipartRequest mr = new MultipartRequest(request, "C:\\LimJi\\day1333\\web\\img", 1024*1024*100, "UTF-8");
			
			String id = mr.getParameter("id");
			String name = mr.getParameter("name");
			String price = mr.getParameter("price");
			String imgname = mr.getOriginalFileName("newimgname");
			
			if (imgname == null) {
				imgname = mr.getParameter("imgname");
			}	
			
			Product p = new Product(Integer.parseInt(id), name, Double.parseDouble(price), imgname);
			
			try {
				biz.modify(p);
				
				response.sendRedirect("ask?type=product&cmd=detail&id=" + id);
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher(nextPage + ".jsp");
		rd.forward(request, response);
	}

}
