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
import com.user.UserBiz;
import com.vo.User;

@WebServlet({ "/UserServlet", "/user" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Biz<String, User> biz;
	
    public UserServlet() {
        super();
        biz = new UserBiz();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String next = "index.jsp";
		
		if (cmd.equals("add")) {
			request.setAttribute("center", "user/add");
		}
		
		else if (cmd.equals("addimpl")) {
			String id = request.getParameter("inputId");
			String name = request.getParameter("inputName");
			String pwd = request.getParameter("inputPassword");
			
			User user = new User(id,name,pwd);
			
			try {
				biz.register(user);
				request.setAttribute("center", "main");
			} catch (Exception e) {
				e.printStackTrace();
				
				request.setAttribute("center", "404");
			}
		}
		
		else if (cmd.equals("list")) {
			ArrayList<User> users = null;
			
			try {
				users = biz.get();
				
				request.setAttribute("ulist", users);
				request.setAttribute("center", "user/list");
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
