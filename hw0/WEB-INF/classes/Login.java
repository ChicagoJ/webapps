import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;

public class Login extends HttpServlet {
	
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String realPwd = "";
		PrintWriter out = response.getWriter();
		
		// if database doesn't exists, MongoDB will create it for you
		DB db = mongo.getDB("a1");
			
		DBCollection customers = db.getCollection("customers");
			
		// Find and display 
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("username", username);

		DBCursor cursor = customers.find(searchQuery);
		
			
		synchronized(session){
			user = (User)session.getAttribute("user");
			if(cursor.count() != 0){
				BasicDBObject obj = (BasicDBObject) cursor.next();
				realPwd = obj.getString("password");
				int realLvl;
				realLvl = obj.getInt("level");
				int lvl = (realLvl == 0) ? 9 : realLvl;
				if(password.equals(realPwd)){
					user = new User(username, "n/a", lvl);
					session.setAttribute("user", user);
					out.println("<html>");
					out.println("<head>");
					out.println("<title>GameSpeed</title>");
					out.println("</head>");
					out.println("<body>");
					out.println("now you logged in<br>");
					out.println("<a href='./index.html'> Index </a><br>");
					out.println("<a href='./My'> My Order List </a><br>");
					out.println("<a href='./Cart'> My Cart </a><br>");
				}else{
					out.println("<html>");
					out.println("<head>");
					out.println("<title>GameSpeed</title>");
					out.println("</head>");
					out.println("<body>");
					out.println("wrong input, try again<br>");
					out.println("<a href='./login.html'> log in </a>");
				}
			}else{
				out.println("<html>");
				out.println("<head>");
				out.println("<title>GameSpeed</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("not there, try again<br>");
				out.println("<a href='./login.html'> log in </a>");
			}
		}
	}
}