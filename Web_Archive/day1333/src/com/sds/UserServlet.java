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

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		String nextPage = "";

		if (cmd.equals("") || cmd == null) {
			
		}

		else if (cmd.equals("add")) {
			nextPage = "user/add";
		}

		else if (cmd.equals("addimpl")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			
			try {
				biz.register(new User(id, name, pwd));
				
				request.setAttribute("rid", id);
				nextPage = "user/rok";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				nextPage = "user/rfail";
			}
		}
		
		else if (cmd.equals("list")) {
			nextPage = "user/list";
			
			try {
				ArrayList<User> users = biz.get();
				request.setAttribute("users", users);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if (cmd.equals("detail")) {
			String id = request.getParameter("id");
			User user = null;
			
			try {
				user = biz.get(id);
				request.setAttribute("userDetail", user);
				nextPage = "user/detail";
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if (cmd.equals("delete")) {
			String id = request.getParameter("id");
			
			try {
				biz.remove(id);
				response.sendRedirect("ask?type=user&cmd=list");
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if (cmd.equals("update")) {
			String id = request.getParameter("id");
			User user = null;
			
			try {
				user = biz.get(id);
				request.setAttribute("userUpdate", user);
				nextPage = "user/update";
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if (cmd.equals("updateimpl")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			
			User user = new User(id, name, pwd);
			
			try {
				biz.modify(user);
				
				response.sendRedirect("ask?type=user&cmd=detail&id=" + id);
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				nextPage = "user/rfail";
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage + ".jsp");
		rd.forward(request, response);
	}
}

