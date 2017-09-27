import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Home extends HttpServlet {

	    private String NOTLOGIN = "<li class=\"right\"><a href=\"./Login.html\">Login</a></li>";
      	private String NOTSIGNUP = "<li class=\"right\"><a href=\"./Registration.html\">Signup</a></li>";
		private String LOGIN = "<li class=\"right\"><a href=\"#\">Hi ";
		private String LOGOUT = "<li class=\"right\"><a href=\"./LogoutServlet\">Logout</a></li>";
		private String CONTENT = "<article><h2>Welcome to Smart Portables</h2></article><article class=\"expanded\">Happy shoping at Smart Portables!</article>";


            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                        response.setContentType("text/html");
                        PrintWriter pw = response.getWriter();
                        HttpSession session = request.getSession();
                        User users = (User)session.getAttribute("users");

                        //get HTML content
                        String hd = Utilities.PrintHeader();
                        String ct = Utilities.PrintContent();
                        String sb = Utilities.PrintSidebar();
                        String ft = Utilities.PrintFooter();
                        

                        //login or notlogin in
                        if (users!= null){
                        	System.out.println(users.getUserId() + "Login");
                        	LOGIN += (String)users.getUserId();
                        	hd += LOGIN;
                        	hd += LOGOUT;
                        } else {
                     //    	//add signup and login tag
	                        hd += NOTSIGNUP;
	                        hd += NOTLOGIN;
                    	}
                        //Write the real content into content
                        ct = ct.replace("##CONTENT##", CONTENT);

                        pw.print(hd);
                        pw.print(ct);
                        pw.print(sb);
                        pw.print(ft);
            }

}