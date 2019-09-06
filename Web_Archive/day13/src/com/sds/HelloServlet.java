package com.sds;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/HelloServlet", "/hello" })
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Item> list2 = new ArrayList<Item>();
		
		list2.add(new Item("k1",1000));
		list2.add(new Item("k2",500));
		list2.add(new Item("k3",300));
		list2.add(new Item("k4",100));
		
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		
		list1.add(5);
		list1.add(3);
		list1.add(8);
		list1.add(7);
		
		System.out.println("service Start...");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		request.setAttribute("list2", list2);
		request.setAttribute("list1", list1);
		request.setAttribute("cnt", 7);
		request.setAttribute("id", id);
		RequestDispatcher rd = request.getRequestDispatcher("ok.jsp");
		rd.forward(request, response);
	}
}
