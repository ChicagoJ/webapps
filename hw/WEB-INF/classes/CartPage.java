

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;


public class CartPage extends HttpServlet {
  

  public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
    HttpSession session = request.getSession();
    ShoppingCart cart;
    synchronized(session) {
      cart = (ShoppingCart)session.getAttribute("shoppingCart");
      // New visitors get a fresh shopping cart.
      // Previous visitors keep using their existing cart.
      if (cart == null) {
        cart = new ShoppingCart();
        session.setAttribute("shoppingCart", cart);
      }
      String itemID = request.getParameter("ItemId");
      System.out.println("Name is " + request.getParameter("ItemName") + "\nid is " + itemID);
      if (itemID != null) {
        String numItemsString =
          request.getParameter("numItems");
          System.out.println("There are " + numItemsString + "in the stock");
        if (numItemsString == null) {
          // If request specified an ID but no number,
          // then customers came here via an "Add Item to Cart"
          // button on a catalog page.
          cart.addItem(itemID);
        } else {
          // If request specified an ID and number, then
          // customers came here via an "Update Order" button
          // after changing the number of items in order.
          // Note that specifying a number of 0 results
          // in item being deleted from cart.1
          int numItems;
          try {
            numItems = Integer.parseInt(numItemsString);
          } catch(NumberFormatException nfe) {
            numItems = 1;
          }
          System.out.println("the real # is " + numItems);
          cart.setNumOrdered(itemID, numItems);
          System.out.println(cart);
        }
      }
    }
    // Whether or not the customer changed the order, show
    // order status.
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String title = "Status of Your Order";
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=\"CENTER\">" + title + "</H1>");
    synchronized(session) {
      List itemsOrdered = cart.getItemsOrdered();
      System.out.println("itemsOrdered is " + itemsOrdered);
      if (itemsOrdered.size() == 0) {
        out.println("<H2><I>No items in your cart...</I></H2>");
      } else {
        // If there is at least one item in cart, show table
        // of items ordered.
        out.println
          ("<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +
           "<TR BGCOLOR=\"#FFAD00\">\n" +
           "  <TH>Name<TH>Discount\n" +
           "  <TH>Unit Cost<TH>Number<TH>Total Cost");
        ItemOrder order;
        // Rounds to two decimal places, inserts dollar
        // sign (or other currency symbol), etc., as
        // appropriate in current Locale.
        NumberFormat formatter =
          NumberFormat.getCurrencyInstance();
        // For each entry in shopping cart, make
        // table row showing ID, description, per-item
        // cost, number ordered, and total cost.
        // Put number ordered in textfield that user
        // can change, with "Update Order" button next
        // to it, which resubmits to this same page
        // but specifying a different number of items.
        for(int i=0; i<itemsOrdered.size(); i++) {
          order = (ItemOrder)itemsOrdered.get(i);
          out.println
            ("<TR>\n" +
             "  <TD>" + order.getItemName() + "\n" +
             "  <TD>" + order.getDiscount() + "\n" +
             "  <TD>" +
             formatter.format(order.getCost()) + "\n" +
             "  <TD>" +
             "<FORM action=\"./CartPage\">\n" +  // Submit to current URL
             "<INPUT TYPE=\"HIDDEN\" NAME=\"ItemId\"\n" +
             "       VALUE=\"" + order.getItemId() + "\">\n" +
             "<INPUT TYPE=\"TEXT\" NAME=\"numItems\"\n" +
             "       SIZE=3 VALUE=\"" + 
             order.getNumItems() + "\">\n" +
             "<SMALL>\n" +
             "<INPUT TYPE=\"SUBMIT\"\n "+
             "       VALUE=\"Update Order\">\n" +
             "</SMALL>\n" +
             "</FORM>\n" +
             "  <TD>" +
             formatter.format(order.getTotalCost()));
            System.out.println(order.getNumItems());
            System.out.println(order.getTotalCost());
        }
        String checkoutURL =
          response.encodeURL("/csj/Checkout.htm");
        // "Proceed to Checkout" button below table
        out.println
          ("</TABLE>\n" +
           "<li><FORM ACTION=\"" + checkoutURL + "\">\n" +
           "<BIG><CENTER>\n" +
           "<INPUT TYPE=\"SUBMIT\"\n" +
           "       VALUE=\"Proceed to Checkout\">\n" +
           "</CENTER></BIG></FORM>"+
           "<a href=\"./WatchServlet\">\n" +
           "<BIG><CENTER>\n" +
           "<INPUT TYPE=\"SUBMIT\"\n" +
           "VALUE=\"Continute shopping\">\n" +
           "</a></CENTER></BIG></li>"
           +
           "");
      }
      out.println("</BODY></HTML>");
    }
  }
}
