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
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.Calendar;

public class My extends HttpServlet {
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
			
				if(user == null){
					out.println("<a href='./login.html'> pls log in first </a>");
				}else{
				// if database doesn't exists, MongoDB will create it for you
				DB db = mongo.getDB("a1");
			
				DBCollection myOrders = db.getCollection("myOrders");
				String username = user.getUserName();
			
				// Find and display 
				BasicDBObject searchQuery = new BasicDBObject();
				searchQuery.put("username", username);

				DBCursor cursor = myOrders.find(searchQuery);
			
				
				out.println("<html>");
				out.println("<head> </head>");
				out.println("<body>");
				out.println("<h1>Hello " + username + "</h1>");
				out.println("Order List");
				if(cursor.count() == 0){
				out.println("<br>There are no order.");
				}else{
			
				out.println("<table>");
				
				String _id = "";
				String bag = "";
				String total = "";
				String unixTime = "";
				String fullName = "";
				String fullAddress = "";
				
				while (cursor.hasNext()) {
					//out.println(cursor.next());
					BasicDBObject obj = (BasicDBObject) cursor.next();				
					
					out.println("<tr>");
					out.println("<td> order _id: </td>");
					_id = obj.getString("_id");
					out.println("<td>" +_id+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> bag: </td>");
					bag = obj.getString("bag");
					out.println("<td>" +bag+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> total: </td>");
					total = obj.getString("total");
					out.println("<td>" +total+ "</td>");
					out.println("</tr>");
					
					//if canceled
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
					out.println("<td>" +fullName+ "</td>");
					out.println("</tr>");

					out.println("<tr>");
					out.println("<td> fullAddress: </td>");
					fullAddress = obj.getString("fullAddress");
					out.println("<td>" +fullAddress+ "</td>");
					out.println("</tr>");
					
					//if can cancel
					long curTime = System.currentTimeMillis();
					if(curTime < (Long.parseLong(unixTime, 10) * 1000 + 777600000)){
					out.println("<tr><form method=\"post\" action=\"./DoCancel\">");
					out.println("<input type=\"hidden\" name=\"oid\" value=\"" + _id + "\">");
					out.println("<td><input type=\"submit\" value=\"cancel this\"></td></form></tr>");
				}
					out.println("<tr>");
					out.println("<td>--------------<td>");
					out.println("</tr>");
				}
			}	
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
				}
			} 
		}catch (MongoException e) {
				e.printStackTrace();
		
	}
}
}