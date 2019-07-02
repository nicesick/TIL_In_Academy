package web.component;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.frame.Biz;
import com.user.UserBiz;
import com.vo.User;

@WebServlet({ "/LoginServlet", "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Biz<String, User> biz;
	
    public LoginServlet() {
        super();
        biz = new UserBiz();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String next = "index.jsp";
		
		if (session != null) {
			session.invalidate();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(next);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("inputId");
		String pwd = request.getParameter("inputPassword");
		
		String next = "index.jsp";
		
		User user = null;
		
		try {
			user = biz.get(id);
			
			if (user.getPwd().equals(pwd)) {
				HttpSession session = request.getSession();
				session.setAttribute("loginuser", user);
				
				session.setMaxInactiveInterval(1000);
			}
			
			else {
				request.setAttribute("center", "404");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("center", "404");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(next);
		rd.forward(request, response);
	}
}
