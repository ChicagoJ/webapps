import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.*;
public class Startup extends HttpServlet{
	private static Map<String, Item> items;

	public void init(HttpServletRequest request, HttpServletResponse response) throws ServletException{
            HttpSession session = request.getSession();
            items = SaxPaserDataStore.getItems(request.getServletContext().getRealPath("/") + "ProductCatalog.xml");
           	session.setAttribute("items",items);

	}
}