import java.io.*;
import java.util.*;
import java.util.regex.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CheckoutServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String cNum = request.getParameter("cardnum");
		String ads = request.getParameter("address");

		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();

		User users = (User)session.getAttribute("users");
		//set Order ID
		String OrderId = Math.random()+ "";
		Random random = new Random(System.currentTimeMillis());
		String conformationId = Math.abs(random.nextInt()) + "";
		//get Order time
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Calendar orderTime = Calendar.getInstance();
		System.out.println(df.format(orderTime.getTime()));		
		//get cart item
        ShoppingCart cart = (ShoppingCart)session.getAttribute("shoppingCart");
		// if (cart != null) {
	    List itemsOrdered = cart.getItemsOrdered();
	        // ItemOrder order;
	        //   for(int i=0; i<itemsOrdered.size(); i++) {
	        //     order = (ItemOrder)itemsOrdered.get(i);
	        // 	System.out.println("item id: " + order.getItemId());
	        // 	System.out.println("item Name: " + order.getItemName());
	        // 	System.out.println("item number: " + order.getNumItems());
        	// }
		// }
        Order order = new Order(OrderId, userId, orderTime, conformationId);
        order.setItemsOrdered(itemsOrdered);
        double totalcost = 0;
        ItemOrder iorder;
        for(int i=0; i<itemsOrdered.size(); i++) {
            iorder = (ItemOrder)itemsOrdered.get(i);
            totalcost += iorder.getTotalCost();
        }
        System.out.println("total cost is :" + totalcost);
        order.setPrice(totalcost);
        users.addToOrders(order);
        // cart.clearCart();
		pw.println("<html><head><meta charset=\"UTF-8\"><title>Order successfully placed! </title></head>");
		pw.println("<body>");
		pw.println("<h2>ATTENTION: Please keep this number for your record.</h2><br>Your order's confirmation id is: <b>"+ conformationId + "<b><br>");
		pw.println("<br><br>You package will arrive in abpw 14 days.<br><br>");
		orderTime.add(Calendar.DATE, 14);
		pw.println("<table><tr><td>Estimated Delivery</td><td>" + (orderTime.get(Calendar.MONTH) + 1) + "/" + orderTime.get(Calendar.DATE) + "/" + orderTime.get(Calendar.YEAR) + "</td></tr>");
		
		orderTime.add(Calendar.DATE, -5);
		order.setEndCancelTime(orderTime);
		pw.println("<tr><td>Last day to cancel order</td><td>" + (orderTime.get(Calendar.MONTH) + 1) + "/" + orderTime.get(Calendar.DATE) + "/" + orderTime.get(Calendar.YEAR) + "</td></tr></table>");	
		pw.println("<a href=\"./Home\">Continue shopping</a><br>");
		// pw.println("<a href=\"./OrdersServlet\">View your orders</a>");
		String homeURL = "./OrdersServlet";
		pw.println("<FORM ACTION=\"" + homeURL + "\">\n" +
               	   "<INPUT TYPE=\"HIDDEN\" NAME=\"orderId\"\n" +
                   "       VALUE=\"" + OrderId + "\">\n" +
                   "<INPUT TYPE=\"SUBMIT\"\n" +
                   "       VALUE=\"View your orders\">\n" +
             	   "</FORM>");
		pw.println("</body></html");
        cart.clearCart();
	}

}