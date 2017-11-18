import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;

public class WhoAmI extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user;
		PrintWriter out = response.getWriter();
		
		
		synchronized(session){
			user = (User)session.getAttribute("user");
			
			if(user == null){
				out.println("no one");
			}else{
				out.println("your username: " + user.getUserName());
				out.println("your level: " + user.getLevel());
			}
		}
	}
}