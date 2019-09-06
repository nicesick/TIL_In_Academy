

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

@WebServlet({ "/HchartServlet", "/hchart" })
public class HchartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		[{
//	        name: 'Tokyo',
//	        data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
//	    }, {
//	        name: 'London',
//	        data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
//	    }]
	    		
	    response.setCharacterEncoding("UTF-8");
	    PrintWriter out = response.getWriter();
	    
	    JSONArray result = new JSONArray();
	    
	    JSONObject data1 = new JSONObject();
	    
	    JSONArray temperature = new JSONArray();
	    Random r = new Random();
	    
	    for (int i = 0; i < 12 ; i++) {
	    	temperature.put(Math.round(r.nextFloat() * 100) % 30.0);
	    }
	    
	    data1.put("data", temperature);
	    data1.put("name", "Tokyo");
	    
	    result.put(data1);
	    
	    JSONObject data2 = new JSONObject();
	    JSONArray temperature1 = new JSONArray();
	    
	    for (int i = 0; i < 12 ; i++) {
	    	temperature1.put(Math.round(r.nextFloat() * 100) % 30.0);
	    }
	    
	    data2.put("data", temperature1);
	    data2.put("name", "Seoul");
	    
	    result.put(data2);
	    
	    out.print(result);
	    out.close();
	}

}
