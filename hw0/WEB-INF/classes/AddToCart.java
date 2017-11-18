import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class AddToCart extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		ShoppingCart cart;
		synchronized(session){
			cart = (ShoppingCart)session.getAttribute("shoppingCart");
			if(cart == null){
				cart = new ShoppingCart();
				session.setAttribute("shoppingCart", cart);
			}
			String itemID = request.getParameter("itemID");
			if(itemID != null){
				String numItemsString = request.getParameter("numItems");
				if(numItemsString == null){
					cart.addItem(itemID);
				}else{
					int numItems;
					try{
						numItems = Integer.parseInt(numItemsString);
					}catch(NumberFormatException nfe){
						numItems = 1;
					}
					cart.setNumAdded(itemID, numItems);
				}
			}
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    	out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>Item Added</TITLE></HEAD>\n" +
                "<H1>Item Added</H1>" +
				"<a href='./'> Keep Shopping </a></br>" +
				"<a href='./Cart'> Go To Cart </a>");
		out.println("</BODY></HTML>");
	}
}