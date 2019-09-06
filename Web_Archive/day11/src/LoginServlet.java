

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet({ "/LoginServlet", "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        new UserInfo();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> users = UserInfo.getUsers();
		Writer writer = response.getWriter();
		
		String target = request.getParameter("target");
		JSONArray ja = new JSONArray();
		
		if (!target.equals("")) {
			for (int i = 0 ; i < users.size(); i++) {
				String userId = users.get(i).getId();
				
				if (userId.contains(target)) {
					JSONObject jo = new JSONObject();
					jo.put("id", userId);
					
					ja.put(jo);
				}
			}
			
			writer.write(ja.toString());
		}

		writer.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		ArrayList<User> users = UserInfo.getUsers();
		
		for (int i = 0 ; i < users.size(); i++) {
			if (users.get(i).getId().equals(id) && users.get(i).getPwd().equals(pwd)) {
				response.sendRedirect("main.html");
				return;
			}
		}
		
		response.sendRedirect("index.html");
	}
}
