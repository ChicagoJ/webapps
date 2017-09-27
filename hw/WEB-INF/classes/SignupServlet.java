
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SignupServlet extends HttpServlet {
   
    protected Map<String, User> users = new HashMap();
 
    // public void init() {
    //             User test = new User("test","TEST",0);
    //             users.put("test", test);
    //             User test1 = new User("test1","TEST",0);
    //             users.put("test1", test1);    
    //         }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
                String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");

        HttpSession session = request.getSession();

        if(userid != null && userid.length() != 0) {
            userid = userid.trim();
        }
        if(password != null && password.length() != 0) {
            password = password.trim();
        }
        if(repassword != null && repassword.length() != 0) {
            repassword = repassword.trim();
        }        


        if(userid != null &&
            password != null && repassword != null) {
            if (!password.equals(repassword)){
                showPage(response,"Sign up Failure! Twice passwords are not the same!");
            }
            else {
                showPage(response,"Sign up Successed! Enjoy Your time at Smart Portables!");
                User newUser = new User(userid, password, 0);
                users.put(userid,newUser);
                session.setAttribute("users", newUser);
            }
        }

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
        out.println("<li><a href=\"./Home#\">Home</a></li>");
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
