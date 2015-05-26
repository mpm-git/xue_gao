package per.cz.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import per.cz.util.http.HttpUtil;


public class Dispatcher extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		Map<String, String> map = HttpUtil.getParameters(request, null);
		String dispatcher = map.get("dispatcher");
		if(dispatcher==null||dispatcher.trim().equals(""))
		{
//			dispatcher="/index.jsp";
			response.sendRedirect("index.jsp");
		}
		else
		{
			dispatcher="/WEB-INF/pages/jsp/"+dispatcher.trim();
//			getServletContext().getRequestDispatcher("/WEB-INF/").forward(request, response);
			request.getRequestDispatcher(dispatcher).forward(request, response);
		}
		
    }
}
