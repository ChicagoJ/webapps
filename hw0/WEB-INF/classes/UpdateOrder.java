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
import org.bson.types.ObjectId;

public class UpdateOrder extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			User user;	
			synchronized(session){
				user = (User)session.getAttribute("user");
				PrintWriter out = response.getWriter();
				DB db = mongo.getDB("a1");
			
				DBCollection myOrders = db.getCollection("myOrders");
				
				String oID = request.getParameter("oid");
				
			
				if(user == null){
					out.println("<a href='./login.html'> pls log in first </a>");
				}else if(user.getLevel() == 2){
					// Find and display 
					BasicDBObject searchQuery = new BasicDBObject();
					searchQuery.put("_id", new ObjectId(oID));

					DBCursor cursor = myOrders.find(searchQuery);
					//out.println(cursor);
					out.println("<html>");
					out.println("<head> </head>");
					out.println("<body>");
					out.println("<h1>Update Order " + oID + "</h1>");
					out.println("<br><br><hr>");
					//updating
			if(cursor.count() == 0){
				out.println("There are no order.");
			}else{
				out.println("<form method=\"post\" action=\"./DoUpdate\">");
				out.println("<td><input type=\"hidden\" name=\"oid\" value=\"" + oID + "\"></td>");
				out.println("<table>");
				
				String _id = "";
				String username = "";
				String bag = "";
				String total = "";
				String unixTime = "";
				String fullName = "";
				String fullAddress = "";
				String nameOnCard = "";
				String cardNumber = "";
				String expireDate = "";
				String scurityCode = "";
				String billingAddress = "";
				
				while (cursor.hasNext()) {
					//out.println(cursor.next());
					BasicDBObject obj = (BasicDBObject) cursor.next();				
					
					out.println("<tr>");
					out.println("<td> order _id: </td>");
					_id = obj.getString("_id");
					out.println("<td>" +_id+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> username: </td>");
					username = obj.getString("username");
					out.println("<td><input type=\"text\" name=\"username\" value=\"" +username+ "\"></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> bag: </td>");
					bag = obj.getString("bag");
					out.println("<td><input type=\"text\" name=\"bag\" value=\"" +bag+ "\"></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> total: </td>");
					total = obj.getString("total");
					out.println("<td><input type=\"text\" name=\"total\" value=\"" +total+ "\"></td>");
					out.println("</tr>");
					
					unixTime = obj.getString("unixTime");
					if(unixTime.equals("0")){
						out.println("<tr><td>canceled</td></tr>");
					}else{
					out.println("<tr>");
					out.println("<td> unixTime: </td>");
					out.println("<td>" +unixTime+ "</td>");
					out.println("</tr>");
					}
					
					out.println("<tr>");
					out.println("<td> fullName: </td>");
					fullName = obj.getString("fullName");
					out.println("<td><input type=\"text\" name=\"fullName\" value=\"" +fullName+ "\"></td>");
					out.println("</tr>");

					out.println("<tr>");
					out.println("<td> fullAddress: </td>");
					fullAddress = obj.getString("fullAddress");
					out.println("<td><input type=\"text\" name=\"fullAddress\" value=\"" +fullAddress+ "\"></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> nameOnCard: </td>");
					nameOnCard = obj.getString("nameOnCard");
					out.println("<td><input type=\"text\" name=\"nameOnCard\" value=\"" +nameOnCard+ "\"></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> cardNumber: </td>");
					cardNumber = obj.getString("cardNumber");
					out.println("<td><input type=\"text\" name=\"cardNumber\" value=\"" +cardNumber+ "\"></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> expireDate: </td>");
					expireDate = obj.getString("expireDate");
					out.println("<td><input type=\"text\" name=\"expireDate\" value=\"" +expireDate+ "\"></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> scurityCode: </td>");
					scurityCode = obj.getString("scurityCode");
					out.println("<td><input type=\"text\" name=\"scurityCode\" value=\"" +scurityCode+ "\"></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> billingAddress: </td>");
					billingAddress = obj.getString("billingAddress");
					out.println("<td><input type=\"text\" name=\"billingAddress\" value=\"" +billingAddress+ "\"></td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td><input type=\"submit\" name=\"Submit\"><td>");
					out.println("</tr>");
				}
				}
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
			
		
		}else{
				out.println("<a href='./login.html'> pls log in as salesman </a>");
		}
	}

} catch (MongoException e) {
				e.printStackTrace();
}
	}}
				