
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
   
    private Map<String, User> users = Utilities.getAllUsers();
 
    public void init() {
                User test = new User("test","TEST",2);
                users.put("test", test);
                User test1 = new User("test1","TEST",0);
                users.put("test1", test1);
                User sales = new User("sales","111",1);
                users.put("sales",sales);   
            }


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        if(userid != null && userid.length() != 0) {
            userid = userid.trim();
        }
        if(password != null && password.length() != 0) {
            password = password.trim();
        }
        if(userid != null &&
            password != null) {
                if(users.get(userid)!=null){
                    String realpassword = (String)users.get(userid).getPassword();
                    System.out.println("the realpassword is " + realpassword + " the real userid is " + userid);

                    if(realpassword != null &&
                        realpassword.equals(password)) {
                        showPage(response, "Login Success!");
                        session.setAttribute("users",users.get(userid));
                    } else {
                        showPage(response, "Login Failure! Username or password is incorrect");
                    }
                }
                else {
                    showPage(response,"Login Failure! You must regist first!");
                }

        }  
        else {
            showPage(response, "Login Failure!  You must supply a username and password");
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
