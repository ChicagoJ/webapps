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
import org.bson.types.ObjectId;

public class DoUpdate extends HttpServlet {
	
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			HttpSession session = request.getSession();
			User user;
		try{
			//perpare bag
			user = (User)session.getAttribute("user");
			String oid = request.getParameter("oid");
										
			// If database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("a1");
				
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myOrders = db.getCollection("myOrders");
			System.out.println("Collection myOrders selected successfully");
			
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("_id", new ObjectId(oid));

			DBCursor cursor = myOrders.find(searchQuery);
			BasicDBObject obj = (BasicDBObject) cursor.next();

			//perpare stuff

			String username = request.getParameter("username");
			String bag = request.getParameter("bag");
			String total = request.getParameter("total");
			String unixTime = obj.getString("unixTime");
			String fullName = request.getParameter("fullName");
			String fullAddress = request.getParameter("fullAddress");
			String nameOnCard = request.getParameter("nameOnCard");
			String cardNumber = request.getParameter("cardNumber");
			String expireDate = request.getParameter("expireDate");
			String scurityCode = request.getParameter("scurityCode");
			String billingAddress = request.getParameter("billingAddress");
				
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
					
			myOrders.update(searchQuery,doc);
			//String idString = doc.get("_id").toString();
				
			System.out.println("Document inserted successfully");
			
			//Send the response back to the JSP
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<head> </head>");
			out.println("<body>");
			out.println("<h1>Order "+ oid + " Is Updated</h1>");
			
			out.println("<table>");
			
			out.println("<tr>");
			out.println("<td>");
			out.println("<a href='index.html'> Index </a>");
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