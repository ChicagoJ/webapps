import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;

public class Cart extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ShoppingCart cart;
        Stock stock;
		synchronized(session){
            stock = new Stock();
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
		//~!~!@~!~
		response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    out.println("Items In Your Cart");
    synchronized(session) {
      List itemsOrdered = cart.getItemsAdded();
      if (itemsOrdered.size() == 0) {
        out.println("<br>No items");
      } else {
        out.println
          ("<table>\n" +
           "<tr>\n" +
           "  <th>Item ID<th>Item Name\n" +
           "  <th>Unit Price<th>Discount<th>Rebates<th>Amount<th>Total Cost<th>Stock");
        CartItem order;
        NumberFormat formatter =
        NumberFormat.getCurrencyInstance();
        for(int i=0; i<itemsOrdered.size(); i++) {
          order = (CartItem)itemsOrdered.get(i);
          out.println
            ("<tr>\n" +
             "  <td>" + order.getItemID() + "\n" +
             "  <td>" + order.getItemName() + "\n" +
             "  <td>" +
             formatter.format(order.getCost()) + "\n" +
             "  <td>" + order.getDiscount() + "\n" +
             "  <td>" + order.getRebates() + "\n" +
             "  <td>" +
             "<form>\n" +  // Submit to current URL
             "<input type=\"HIDDEN\" NAME=\"itemID\"\n" +
             "       VALUE=\"" + order.getItemID() + "\">\n" +
             "<input type=\"TEXT\" NAME=\"numItems\"\n" +
             "       SIZE=3 VALUE=\"" + 
             order.getAmount() + "\">\n" +
             "<SMALL>\n" +
             "<input type=\"Submit\"\n "+
             "       VALUE=\"Update\">\n" +
             "</SMALL>\n" +
             "</FORM>\n" +
             "  <td>" +
             formatter.format(order.getTotalCost()) +
             "  <td>" + stock.getStock(order.getItemID()));
        }
        
        //check if logged in
        
		User user;	
		synchronized(session){
			user = (User)session.getAttribute("user");
			
			if(user == null){
				out.println("</table>\n<a href='./login.html'> pls log in first </a>");
			}else{
        String checkoutURL =
          response.encodeURL("./checkout.html");
        // "Proceed to Checkout" button below table
        out.println
          ("</table>\n" +
           "<form action=\"" + checkoutURL + "\">\n" +
           "<input type=\"Submit\"\n" +
           "       VALUE=\"Proceed to Checkout\">\n" +
           "</form>");
            }
        }
      }
	}
    }
}