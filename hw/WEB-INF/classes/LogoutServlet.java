
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {
   
    protected Map<String, User> users = new HashMap();
 
    public void init() {
                User test = new User("test","TEST",0);
                users.put("test", test);
                User test1 = new User("test1","TEST",0);
                users.put("test1", test1);    
            }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {


        HttpSession session = request.getSession();
        session.removeAttribute("users");
        showPage(response, "You've Logged out!");
        // if(userid != null && userid.length() != 0) {
        //     userid = userid.trim();
        // }
        // if(password != null && password.length() != 0) {
        //     password = password.trim();
        // }
        // if(userid != null &&
        //     password != null) {
        //         String realpassword = (String)users.get(userid).getPassword();
        //         if(realpassword != null &&
        //             realpassword.equals(password)) {
        //             showPage(response, "Login Success!");
        //             session.setAttribute("users",users.get(userid));
        //         } else {
        //             showPage(response, "Login Failure! Username or password is incorrect");
        //         }
        // }  else {
        //     showPage(response, "Login Failure!  You must supply a username and password");
        // }
    } 
    

    protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login Servlet Result</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + message + "</h2>");
        out.println("<li><a href=\"./Home\">Home</a></li>");
        out.println("</body>");
        out.println("</html>");
        out.close();
 
    }
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}
