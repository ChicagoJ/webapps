
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CheckoutPage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        System.out.println((Double)session.getAttribute("TotalPrice"));
		String MESSAGE = Utilities.PrintCheckoutForm();
        showPage(response,MESSAGE);
        java.io.PrintWriter out = response.getWriter();
        out.println("<div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\">"
            + "<ol class=\"carousel-indicators\">"
            + "<li data-target=\"#myCarousel\" data-slide-to=\"0\" class=\"active\"></li>"
            + "<li data-target=\"#myCarousel\" data-slide-to=\"1\"></li>" 
            + "<li data-target=\"#myCarousel\" data-slide-to=\"2\"></li>" 
            + "<li data-target=\"#myCarousel\" data-slide-to=\"3\"></li></ol>" 
            + "<div class=\"carousel-inner\" role=\"listbox\">" 
            + "<div class=\"item active\"><img src=\"./images/image.jpg\" alt=\"Chania\" width=\"460\" height=\"345\">" 
            + "<div class=\"carousel-caption\"><h3>A</h3></div></div>" 
            + "<div class=\"item\"><img src=\"./images/image.jpg\" alt=\"Chania\" width=\"460\" height=\"345\">" 
            + "<div class=\"carousel-caption\"><h3>B</h3></div></div>" 
            + "<div class=\"item\"><img src=\"./images/image.jpg\" alt=\"Chania\" width=\"460\" height=\"345\">" 
            + "<div class=\"carousel-caption\"><h3>C</h3></div></div>" 
            + "<div class=\"item\"><img src=\"./images/image.jpg\" alt=\"Chania\" width=\"460\" height=\"345\">" 
            + "<div class=\"carousel-caption\"><h3>D</h3></div></div>" 
            + "<a class=\"left carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"prev\">" 
            + "<span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span>"
            + "<span class=\"sr-only\">Previous</span></a>" 
            + "<a class=\"right carousel-control\" href=\"#myCarousel\" role=\"button\" data-slide=\"next\">"
            + "<span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span>" 
            + "<span class=\"sr-only\">Next</span></a></div></div>");
        out.println("</body>");
        out.println("</html>");
        out.close();

    } 
    

    protected void showPage(HttpServletResponse response, String message)
    throws ServletException, java.io.IOException {
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Check out</title>");  
        out.println("<meta charset=\"utf-8\">"
            + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
            + "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"
            + "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>"
            + "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>" 
            + "<style>.carousel-inner > .item > img,.carousel-inner > .item > a > img { width: 70%;margin: auto;}</style>");
        out.println("</head>");
        out.println("<body>");
        out.println(message);
        out.println("<CENTER><a href=\"./Home\">Home</a><CENTER>");
        // last two line printed in processRequest
        // out.println("</body>");
        // out.println("</html>");
        // out.close();
 
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