package web.component;

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
import com.vo.User;

@WebServlet({ "/ProductServlet", "/product" })
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Biz<Integer, Product> biz;
	
    public ProductServlet() {
        super();
        biz = new ProductBiz();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String next = "index.jsp";
		
		if (cmd.equals("add")) {
			request.setAttribute("center", "product/add");
		}
		
		else if (cmd.equals("addimpl")) {
			MultipartRequest mr = new MultipartRequest(request, "C:\\Users\\student\\web\\day144\\web\\img", 1024 * 1024 * 100, "UTF-8");
			
			String name = mr.getParameter("inputName");
			String price = mr.getParameter("inputPrice");
			String img = mr.getOriginalFileName("inputImg");
			
			Product p = new Product(name, Double.parseDouble(price), img);
			
			try {
				biz.register(p);
				request.setAttribute("center", "main");
			} catch (Exception e) {
				e.printStackTrace();
				
				request.setAttribute("center", "404");
			}
		}
		
		else if (cmd.equals("list")) {
			ArrayList<Product> ps = null;
			
			try {
				ps = biz.get();
				
				request.setAttribute("plist", ps);
				request.setAttribute("center", "product/list");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				request.setAttribute("center", "404");
			}
		}
		
		else if (cmd.equals("login")) {
			request.setAttribute("center", "login");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(next);
		rd.forward(request, response);
	}
}
