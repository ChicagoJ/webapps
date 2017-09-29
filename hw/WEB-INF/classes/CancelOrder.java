
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CancelOrder extends HttpServlet {
    String CANCELED = "Order Canceled!";
    String NOTCANCELED = "ERROR:Order not Canceled, Please check your confirmation ID again";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        HttpSession session = request.getSession();
        String orderId = request.getParameter("orderId");

        User users = (User)session.getAttribute("users");
        String cId = request.getParameter("cId");
        String realCId = users.getOrdersMap().get(orderId).getConformationId(); 
        if(!cId.equals(realCId)){
            showPage(response,NOTCANCELED);
        } else{
            users.cancelOrder(orderId);
            showPage(response, CANCELED);
        }

    } 
    

    protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Check out</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println(message);
        out.println("<CENTER><a href=\"./Home\">Home</a><CENTER>");
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