<html>
<head>
    <%@ page import="java.io.*,javax.servlet.*,javax.servlet.http.*,com.mongodb.*,java.util.*" %>
    <title><%= request.getParameter("productClass") %></title>
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

	    <article>

			<h2><%= request.getParameter("productClass") %></h2>
			
		</article>
	
		<article class="expanded">

						
            <table>
               <%
                    MongoClient mongo;
                    mongo = new MongoClient("localhost", 27017);
                    
                    try{
                    String searchField = "productClass";
                    String searchParameter = "";
			        if (request.getParameter("productClass") != null){
			             searchParameter = request.getParameter("productClass");
			        }
                    
                    DB db = mongo.getDB("a1");
                    
                    DBCollection myItems = db.getCollection("myItems");
                    
                    BasicDBObject searchQuery = new BasicDBObject();
                    searchQuery.put(searchField, searchParameter);
        
                    DBCursor cursor = myItems.find(searchQuery);
                    
                    if (cursor.count() == 0){
                    %>
                        <tr>
				            <td>
					           <p> Nothing Found </p>
				            </td>
                        </tr>
                    <%
                    }
                    

                    while (cursor.hasNext())
                        {
                            BasicDBObject obj = (BasicDBObject) cursor.next();
               %>
               <tr>
				<td>
					<p> <%= obj.getString("itemName") %> </p>
				<td>
				<td>
					<form action="./Cart">
                        <input type="hidden" name="itemID" value="<%= obj.getString("itemID") %>">
                        <input class = "submit-button" type="submit" value="add to cart">
                    </form>
					<form class = "submit-button" action = "./submitareview.jsp">
                        <input type="hidden" name="productName" value="<%= obj.getString("itemName")%>">
						<input class = "submit-button" type = "submit" value = "Write Review">
					</form>
					<form class = "submit-button" method = "get" action = "./viewreview.jsp"">
                        <input type="hidden" name="productName" value="<%= obj.getString("itemName")%>">
						<input class = "submit-button" type = "submit" value = "View Reviews">
					</form>
				</td>
			   </tr>
               <%
                    }
                    } catch (MongoException e) {
				        e.printStackTrace();
		            }
               %>
               </table>
			
		</article>
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