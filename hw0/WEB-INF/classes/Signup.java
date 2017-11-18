import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

public class Signup extends HttpServlet {
	
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, java.io.IOException {
			processRequest(request, response);
		}
		
	protected void processRequest(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, java.io.IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String repwd = req.getParameter("repwd");
		
		if(username != "" && (username.trim() != "")){
			username = username.trim();
		}
		if(password != "" && (password.trim() != "")){
			password = password.trim();
		}
		//check if already have same username
		
		// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("a1");
			
			DBCollection customers = db.getCollection("customers");
			
			// Find and display 
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("username", username);

			DBCursor cursor = customers.find(searchQuery);
		if(password.equals(repwd) && cursor.count() == 0 && username != "" && password != ""){
			//add to DB
			long unixTime = System.currentTimeMillis() / 1000L;
			BasicDBObject doc = new BasicDBObject("title", "customers").
				append("username", username).
				append("password", password).
				append("since", unixTime).
				append("level", 9);
			customers.insert(doc);
			showPage(res, "Congrats, now you can log in.");
		}else{
			showPage(res, "wrong input, try again.(try change a username)");
		}
	}
	
	protected void showPage(HttpServletResponse response, String message)
	throws ServletException, java.io.IOException{
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>GameSpeed</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>" + message + "</h2>");
		out.println("<a href='index.html'> Index </a>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}