<html>
<head>
    <%@ page import="java.io.*,javax.servlet.*,javax.servlet.http.*,com.mongodb.*,java.util.*,java.text.*,org.bson.types.ObjectId" %>
    <%@ page import="User.*" %>
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
</head>
<body>

    <div id="container">
    <header>
    	<h1><a href="./">GameSpeed</a></h1>
        <h2>This is an assignment for CSP 595</h2>
    </header>
    <div class="cart">
		<a href="./cart.jsp">Cart</a>
	</div>
    <div class="sl">
		<a href="./signup.html">Sign Up</a> 
        <a href="./login.html">Log In</a>
	</div>
    <nav>
    	<ul>
        	<li class=""><a href="./">Home</a></li>
            <li class="start"><a href="./itemlist.jsp?productClass=XBox">XBox</a></li>
            <li class=""><a href="./itemlist.jsp?productClass=Play Station">Play Station</a></li>
            <li class=""><a href="./itemlist.jsp?productClass=Wii">Wii</a></li>
            <li class="end"><a href="./itemlist.jsp?productClass=Games">Games</a></li>
        </ul>
    </nav>


    <div id="body">	

	<section id="content">
		<a href="./newitem.html"><h1>new item</h1></a>
		<%
			MongoClient mongo;
			mongo = new MongoClient("localhost", 27017);
			try{
			

				User user;	

				synchronized(session){
					user = (User)session.getAttribute("user");
			
					if(user == null){
					%>
						<a href='./login.html'> pls log in first </a>
						<%
					}else if(user.getLevel() == 1){
				// if database doesn't exists, MongoDB will create it for you
				DB db = mongo.getDB("a1");
				
				DBCollection myItems = db.getCollection("myItems");
				
				// Find and display 
				BasicDBObject searchQuery = new BasicDBObject();
				
	
				DBCursor cursor = myItems.find(searchQuery);
				%>
				
				<h1>item List</h1>
				
				<table>
				
				
				</table>
				<br><br><hr>
				<%
				if(cursor.count() == 0){
				%>
					There are no item.
					<%
				}else{
				%>
					<table>
					<%
					String itemID = "";
					String itemName = "";
					String productClass = "";
					double itemPrice = 0.0;
					double discount = 0.0;
					String rebate = "";
					int stock = 0;
					
					while (cursor.hasNext()) {
						//out.println(cursor.next());
						BasicDBObject obj = (BasicDBObject) cursor.next();				
						
						itemID = obj.getString("itemID");
						productClass = obj.getString("productClass");
						itemName = obj.getString("itemName");
						itemPrice = Double.parseDouble(obj.getString("itemPrice"));
						discount = Double.parseDouble(obj.getString("discount"));
						rebate = obj.getString("rebate");
						stock = Integer.parseInt(obj.getString("stock"));
						%>
						<tr>
						<td> item id: </td>
						
						<td><%=itemID %></td>
						</tr>
						
						<tr>
						<td> itemName: </td>
						
						<td><%=itemName %></td>
						</tr>
						
						<tr>
							
						<td> productClass: </td>
						
						<td><%=productClass %></td>
						</tr>
							
						<td> itemPrice: </td>
						
						<td><%=itemPrice %></td>
						</tr>
						
						<tr>
						<td> discount: </td>
						
						<td><%=discount %></td>
						</tr>
						
						<tr>
						<td> rebate: </td>
						
						<td><%=rebate %></td>
						</tr>
	
						<tr>
						<td> stock: </td>
						
						<td><%=stock %></td>
						</tr>
						
						
						
						<tr><td>
						<form method="get" action="./edititems.jsp">
						<input type="hidden" name="itemID" value="<%=itemID  %>">
						<input type="submit" value="edit this item"></form>
						</td></tr>
						
						<tr>
						<td>------------<td>
						</tr>
						<%
					}
				}	
			}else{
			%>
				store manager use only
				<%
		}
		}
		} catch (MongoException e) {
				e.printStackTrace();
		}
			
			
			
	
		%>
		
		</table>
	</section>
   <aside class="sidebar">
	
            <ul>	
               <li>
                    <h4>Products</h4>
                    <ul>
                        <li><a href="./itemlist.jsp?productClass=XBox">XBox</a></li>
                        <li><a href="./itemlist.jsp?productClass=Play Station">Play Station</a></li>
                        <li><a href="./itemlist.jsp?productClass=Wii">Wii</a></li>
                        <li><a href="./itemlist.jsp?productClass=Games">Games</a></li>
                    
                    </ul>
                </li>
                
                <li>
                    <h4>About us</h4>
                    <ul>
                        <li class="text">
                        	<p style="margin: 0;">This is a sample website created to demonstrate a standard enterprise web page.</p>
                        </li>
                    </ul>
                </li>
                
                <li>
                	<h4>Search site</h4>
                    <ul>
                    	<li class="text">
                            <form method="get" class="searchform" action="#" >
                                <p>
                                    <input type="text" size="25" value="" name="s" class="s" />
                                    
                                </p>
                            </form>	
						</li>
					</ul>
                </li>
                
            </ul>
		
    </aside>
    
	<div class="clear"></div>
	</div>
    
	<footer>
	
        <div class="footer-content">
            <ul>
            	<li><h4>Dummy Link Section 1</h4></li>
                <li><a href="#">Dummy Link 1</a></li>
			</ul>
           
        <div class="clear"></div>
        </div>
		
        <div class="footer-bottom">
            <p>CSP 595 - Enterprise Web Application - assignment 1 by Ran SU</p>
        </div>
		
    </footer>
</div>

</body>

</html>

