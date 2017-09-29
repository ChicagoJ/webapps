
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UpdateItemServlet extends HttpServlet {
   
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        String ItemId = request.getParameter("ItemId");
        String ItemName = request.getParameter("ItemName");
        String pricestr = request.getParameter("price");
        String discountstr = request.getParameter("discount");
        String rebatestr = request.getParameter("rebate");
        String stockstr = request.getParameter("stock");

        double price = Double.parseDouble(pricestr);
        double discount = Double.parseDouble(discountstr);
        double rebate = Double.parseDouble(rebatestr);
        int stock = Integer.parseInt(stockstr);

        Item item = new Item(ItemId, ItemName, price, discount, rebate, stock);
        if(SaxPaserDataStore.UpdateItem(item)){
            response.sendRedirect(request.getContextPath()+"/ProductServlet");
        }
    } 
    


    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}
