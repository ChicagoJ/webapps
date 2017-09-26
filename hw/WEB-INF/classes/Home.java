import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Home extends HttpServlet {

	    private String TAGLOGIN = "<li class=\"right\"><a href=\"./LoginPage\">Login</a></li>";
        private String TAGSIGNUP = "<li class=\"right\"><a href=\"#\">Signup</a></li>";
		private String CONTENT =   "<article><h2>Welcome to Smart Portables</h2></article>";
		private String str1 = "Happy shoping at Smart Portables!123";


            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                        response.setContentType("text/html");
                        PrintWriter pw = response.getWriter();
                       

                        //get HTML content
                        String hd = Utilities.PrintHeader();
                        String ct = Utilities.PrintContent();
                        String sb = Utilities.PrintSidebar();
                        String ft = Utilities.PrintFooter();
                        //add signup and login tag
                        hd += TAGSIGNUP;
                        hd += TAGLOGIN;

                        //Write the real content into content
                        ct += CONTENT;
                        ct = ct.replace("##Content", str1);

                        pw.print(hd);
                        pw.print(ct);
                        pw.print(sb);
                        pw.print(ft);
            }

}