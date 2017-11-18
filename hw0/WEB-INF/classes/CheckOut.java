import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.*;
import java.text.*;


public class CheckOut extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			HttpSession session = request.getSession();
			ShoppingCart cart;
			User user;
		try{
			//perpare bag
			cart = (ShoppingCart)session.getAttribute("shoppingCart");
			user = (User)session.getAttribute("user");
			String bag = "";
			double total = 0.0;
			synchronized(session) {
      			List itemsOrdered = cart.getItemsAdded();
        		CartItem order;
        		NumberFormat formatter = NumberFormat.getCurrencyInstance();
				for(int i=0; i<itemsOrdered.size(); i++) {
          			order = (CartItem)itemsOrdered.get(i);
          			bag = bag +
             		">>" + order.getItemID() + 
             		">" + order.getItemName() + 
             		">" + formatter.format(order.getCost()) + 
             		">" + order.getAmount() + 
             		">" + formatter.format(order.getTotalCost()) + "<<";
					total = total + order.getTotalCost();
        		}
			}
			
			//perpare stuff
			long unixTime = System.currentTimeMillis() / 1000L;
			String fullName = request.getParameter("FullName");
			String fullAddress = request.getParameter("FullAddress");
			String nameOnCard = request.getParameter("NameOnCard");
			String cardNumber = request.getParameter("CardNumber");
			String expireDate = request.getParameter("ExpireDate");
			String scurityCode = request.getParameter("ScurityCode");
			String billingAddress = request.getParameter("BillingAddress");
			String username = user.getUserName();
										
			// If database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("a1");
				
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myOrders = db.getCollection("myOrders");
			System.out.println("Collection myOrders selected successfully");
				
			BasicDBObject doc = new BasicDBObject("title", "myOrders").
				append("username", username).
				append("bag", bag).
				append("total", total).
				append("unixTime", unixTime).
				append("fullName", fullName).
				append("fullAddress", fullAddress).
				append("nameOnCard", nameOnCard).
				append("cardNumber", cardNumber).
				append("expireDate", expireDate).
				append("scurityCode", scurityCode).
				append("billingAddress", billingAddress);
					
			myOrders.insert(doc);
			String idString = doc.get("_id").toString();
				
			System.out.println("Document inserted successfully");
			
			//Send the response back to the JSP
			PrintWriter out = response.getWriter();
			
			//delivery date
			Calendar now = Calendar.getInstance();
			now.add(Calendar.DATE, 14);
			
			
			out.println("<html>");
			out.println("<head> </head>");
			out.println("<body>");
			out.println("<h1> Order placed. Your Order Id is: "+ idString + "</h1>");
			out.println("You can get your package in :" + (now.get(Calendar.MONTH) + 1) + "-"
        + now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR));
			out.println("<table>");
			
			out.println("<tr>");
			out.println("<td>");
			out.println("<a href='./'> Index </a>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("</table>");
			
			out.println("</body>");
			out.println("</html>");
			
			
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	
}