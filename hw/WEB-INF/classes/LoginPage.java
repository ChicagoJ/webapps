import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class LoginPage extends HttpServlet {

	private String TAGLOGIN = "<li class=\"right\"><a href=\"./LoginPage\">Login</a></li>";
      private String TAGSIGNUP = "<li class=\"right\"><a href=\"#\">Signup</a></li>";
      private String LoginForm = Utilities.PrintLoginForm();



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
                        ct = ct.replace("##Content", LoginForm);

                        pw.print(hd);
                        pw.print(ct);
                        pw.print(sb);
                        pw.print(ft);
            }

}