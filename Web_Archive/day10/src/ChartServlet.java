

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class ChartServlet
 */
@WebServlet({ "/ChartServlet", "/chart" })
public class ChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		JSONArray ja = new JSONArray();
		JSONObject jo;
		
		Random r = new Random();
		
		for (int i = 0 ; i < 5 ; i ++) {
			int val = r.nextInt(10) + 1;
			
			jo = new JSONObject();
			
			jo.put("rank", i + 1);
			jo.put("keyword", "임지훈" + val);
			
			if (i % 2 == 0) {
				jo.put("type", "up");				
			}

			else {
				jo.put("type", "down");
			}
			
			jo.put("cnt", 22);
			
			ja.put(jo);
		}
		
		out.write(ja.toString());
		out.close();
	}
}
