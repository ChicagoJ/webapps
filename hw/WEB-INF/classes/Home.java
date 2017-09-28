import java.io.*; 
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Home extends HttpServlet {
		 String PRODUCT = "<li class=\"\"><a href=\"./ProductServlet\">Products</a></li>";
	       String NOTLOGIN = "<li class=\"right\"><a href=\"./Login.html\">Login</a></li>";
      	 String NOTSIGNUP = "<li class=\"right\"><a href=\"./Registration.html\">Signup</a></li>";
		 String LOGOUT = "<li class=\"right\"><a href=\"./LogoutServlet\">Logout</a></li>";
		 String CONTENT = "<article><h2>Welcome to Smart Portables</h2></article><article class=\"expanded\">Happy shoping at Smart Portables!</article>";
		 private static Map<String, Item> items;


            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                        response.setContentType("text/html");
                        PrintWriter pw = response.getWriter();
                        HttpSession session = request.getSession();
                        //get user infor
                        User users = (User)session.getAttribute("users");
                        //set product infor
                        items = SaxPaserDataStore.getItems(request.getServletContext().getRealPath("/") + "ProductCatalog.xml");
				        for (Item item: items.values()) {
				        System.out.println("item #"+ item.getItemId() +":");
				        System.out.println("\t\t name: " + item.getItemName());
				        System.out.println("\t\t Price: " + item.getPrice());
				        System.out.println("\t\t Discount: " + item.getDiscount());
				        System.out.println("\t\t Rebates: " + item.getRebates());
				        System.out.println("\t\t Stock: " + item.getStock());
				        System.out.println("\t\t Accessories: " + item.getAccessories());
				        }
				        session.setAttribute("items",items);
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
                        // ct = ct.replace("##CONTENT##", CONTENT);
                    	ct += CONTENT;
                        pw.print(hd);
                        pw.print(ct);
                        pw.print(sb);
                        pw.print(ft);
            }

}