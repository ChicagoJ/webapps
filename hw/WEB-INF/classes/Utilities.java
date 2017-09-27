import java.lang.*;
import java.util.*;
import java.io.*; 
import javax.servlet.*;
import javax.servlet.http.*;


public class Utilities {
    public static final String TOMCAT_HOME = "/Users/junyipeng/apache-tomcat-7.0.34/webapps/hw/";
    // private HttpServletRequest request;
    // private HttpServletResponse response;
    // PrintWriter out = response.getWriter();;

    public static String printHtml(String file) {
        String result = HtmlToString(file);
        
        // if(file == "Header.html"){
        //     if(session.getAttribute("username")!=null){
        //         String username = session.getAttribute("username").toString();
        //         username = Character.toUpperCase(username.charAt(0))+username.substring(1);
        //         result = result
        //                 + "<li><a>Hello,"+username+"</a></li>"
        //                 + "<li><a href = 'Account'>Account</a></li>"
        //                 +"<li><a href= 'Logout'>Logout</a></li>";
        //     }
        //     else
        //         result = result + "<li><a href= 'Login'><Login></a></li>";
        //     result = result 
        //             + "<li><a href = 'Cart'>Cart("+CartCount()+")</a></li></ul></div></div><div id='page'>"
        //     pw.print(result);
        // }else
            // out.print(result);
        if (file == "Content.html"){
            result = result.replace("##CONTENT##","HelloWorld");
            System.out.println("worked");
        }
        return result;
    }

    
    public static String HtmlToString(String file) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(TOMCAT_HOME + file));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str + "\r\n");
            }
            in.close();
        } catch (Exception e) {
            return "Error: unable to read HTML file " + file;
        }
        
        return contentBuilder.toString();
    }
    
    public static String PrintHeader(){
        String str = "";
        str += "<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
        str += "<title>Welcome to Smart Portables</title>";
        str += "<link rel=\"stylesheet\" href=\"styles.css\" type=\"text/css\" />";
        str += "<meta name=\"viewport\" content=\"width=device-width, minimum-scale=1.0, maximum-scale=1.0\" /></head>";
        str += "<body><div id=\"container\">";
        str += "<header><div class=\"width\"><h1><a href=\"./Home\">Smart<span>Portables</span></a></h1></div></header>";
        str += "<nav><div class=\"width\"><ul><li class=\"start selected\"><a href=\"./Home\">Home</a></li>";
        str += "<li class=\"\"><a href=\"#\">Products</a></li>";
        str += "<li class=\"end\"><a href=\"#\">Contact</a></li>";

    
        return str;

    }

    public static String PrintContent(){
        String str = "";
        str += "</ul></div></nav>";
        str += "<div id=\"body\" class=\"width\">";
        str += "<section id=\"content\">";
        // str += "<article><h2>Welcome to Smart Portables</h2></article>";
        // str += "<article>##Content</article></section>";
        str += "##CONTENT##";
        str += "</section>";
        return str;
    }

    public static String PrintSidebar(){
        String str = "";
        str += "<aside class=\"sidebar\">";
        str += "<ul>";
        //categories
        str += "<li><h4>Categories</h4><ul>";
        str += "<li><a href=\"#\">Phone</a></li>";
        str += "<li><a href=\"#\">Laptop</a></li>";
        str += "<li><a href=\"#\">HeadPhone</a></li>";
        str += "<li><a href=\"#\">SmartWatch</a></li>";
        str += "<li><a href=\"#\">Speakers</a></li>";
        str += "<li><a href=\"#\">External Storage</a></li></ul></li>";
        //search site
        str += "<li><h4>Search site</h4><ul>";
        str += "<li class=\"text\">";
        str += "<form method=\"get\" class=\"searchform\" action=\"#\">";
        str += "<p><input type=\"text\" size=\"31\" value=\"\" name=\"s\" class=\"s\"/>";
        str += "</p></form></li></ul></li>";  
        str += "</ul></aside><div class=\"clear\"></div></div>";
        return str;
    }

    public static String PrintFooter(){
        String str = "";
        str += "<footer><div class=\"footer-content width\">";
        str += "<ul><li><h4>About us</h4></li>";
        str += "<li><a href=\"#\">CSP584 Page</a></li>";
        str += "<li><a href=\"https://www.pengjunyi.com/\">AmazingJunyi</a></li></ul>";
        
        str += "<ul><li><h4>Support</h4></li>";
        str += "<li><a href=\"#\">Online suppoty</a></li>";
        str += "<li><a href=\"#\">Email</a></li></ul>";
        
        str += "<ul class=\"endfooter\"><li><h4>Location</h4></li>";
        str += "<li><a href=\"#\">Find a Storage</a></li>";
        str += "<li><a href=\"#\">Web map</a></li></ul>";

        str += "<div class=\"clear\"></div></div>";
        str += "<div class=\"footer-bottom\">";
        str += "<p>&copy; CSP584 by Peng Junyi</p></div>";
        str += "</footer></div></body></html>";
        return str;

    }

    public static String PrintLoginForm(){
        String str = "";
        str += "<article><h4>Login</h4>";
        str += "<center><form method=\"post\" action=\"./LoginServlet\">";
        str += "<table cellpadding='2' cellspacing='1'>";
        str += "<tr><td>User ID</td><td><input type=\"TEXT\" size=\"15\" name=\"userid\"></input></td></tr>";
        str += "\n<tr><td>Password</td><td><input type\"password\" size=\"15\" name=\"password\"/></td></tr>";
        str += "<tr><td colspan='2'><center><input type=\"submit\" value=\"Login\" /></center></td></tr>";
        str += "</table></form></center></article>";
        return str;
    }

    public static String PrintSignupForm(){
        String str = "";
        str += "<h4>Registration</h4>";
        str += "<center><form method=\"post\" action=\"./SignupServlet\">";

        str += "<table cellpadding='2' cellspacing='1'>";
        str += "<tr><td>User ID</td><td><input type=\"TEXT\" size=\"15\" name=\"userid\"></input></td></tr>";
        str += "\n<tr><td>Password</td><td><input type\"PASSWORD\" size=\"15\" name=\"password\"/></td></tr>";
        str += "<tr><td>Re-enter Password</td><td><input type\"PASSWORD\" size=\"15\" name=\"repassword\"/></td></tr>";
        str += "<tr><td colspan='2'><center><input type=\"submit\" value=\"Signup\" /></center></td></tr>";
        str += "</table></form></center>";
        return str;
    }


}