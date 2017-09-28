import java.io.*;
import java.util.*;
import java.util.regex.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class CheckoutServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String cNum = request.getParameter("cardnum");
		String ads = request.getParameter("address");

		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();

		User users = session.getAttribute("users");


        }

}