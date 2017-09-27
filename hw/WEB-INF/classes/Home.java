import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Home extends HttpServlet {
		 String PRODUCT = "<li class=\"\"><a href=\"#\">Products</a></li>";
	     String NOTLOGIN = "<li class=\"right\"><a href=\"./Login.html\">Login</a></li>";
      	 String NOTSIGNUP = "<li class=\"right\"><a href=\"./Registration.html\">Signup</a></li>";
		 String LOGOUT = "<li class=\"right\"><a href=\"./LogoutServlet\">Logout</a></li>";
		 String CONTENT = "<article><h2>Welcome to Smart Portables</h2></article><article class=\"expanded\">Happy shoping at Smart Portables!</article>";


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
                        	hd += "<li style=\"float:right\"><a href=\"#\">Hi ";
                        	hd += users.getUserId();
                        	if (users.getLevel() >= 1){
                        		hd += PRODUCT;
                        	}
                        	if (users.getLevel() == 1){
                        		hd += "<li class=\"right\"><a href=\"./Registration.html\">creat account</a></li>";
                        	}
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