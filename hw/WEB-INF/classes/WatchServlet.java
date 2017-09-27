import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class WatchServlet extends HttpServlet {

	private String TAGLOGIN = "<li class=\"right\"><a href=\"./LoginPage\">Login</a></li>";
      private String TAGSIGNUP = "<li class=\"right\"><a href=\"./SignupPage\">Signup</a></li>";
      private String SignupForm = Utilities.PrintSignupForm();
      String PRODUCT = "<li class=\"\"><a href=\"./ProductServlet\">Products</a></li>";
      String NOTLOGIN = "<li class=\"right\"><a href=\"./Login.html\">Login</a></li>";
      String NOTSIGNUP = "<li class=\"right\"><a href=\"./Registration.html\">Signup</a></li>";
      String LOGOUT = "<li class=\"right\"><a href=\"./LogoutServlet\">Logout</a></li>";



            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                        response.setContentType("text/html");
                        PrintWriter pw = response.getWriter();
                        HttpSession session = request.getSession();
                        Map<String, Item> items = (Map)session.getAttribute("items");
                        User users = (User)session.getAttribute("users");

                        //get HTML content
                        String hd = Utilities.PrintHeader();
                        String ct = Utilities.PrintContent();
                        String sb = Utilities.PrintSidebar();
                        String ft = Utilities.PrintFooter();
                        

                        //login or notlogin in
                        if (users!= null){
                              System.out.println("Products Page");
                              System.out.println(items);
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
                     //       //add signup and login tag
                              hd += NOTSIGNUP;
                              hd += NOTLOGIN;
                        }





                        pw.print(hd);
                        //Write the real content into content
                        String CONTENT = Utilities.PrintProductTable();
                        ct += CONTENT;
                        pw.println(ct);                     
                        for (Item item : items.values()) {
                              if (item.getItemId().charAt(0) == 'w') {
                                    pw.println("<tr>");
                                    pw.println("<td align=\"\">"+ item.getItemName() + "</td>");
                                    pw.println("<td align=\"\">" + item.getPrice() + "</td>");
                                    pw.println("<td align=\"\">"+ item.getDiscount() +"</td>");
                                    pw.println("<td align=\"\">"+item.getRebates() +"</td>");
                                    pw.println("<td align=\"\">"+item.getStock()+"</td></tr>");                         
                              }
                                                             
                         }
                        pw.println("</table></article>");
                        pw.println(sb);
                        pw.println(ft);
            }

}