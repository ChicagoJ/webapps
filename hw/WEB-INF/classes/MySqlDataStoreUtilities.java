import java.sql.*;
import java.lang.*;
import java.util.*;

public class MySqlDataStoreUtilities {

    public static Connection getDafaultConnection() {
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = null;

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "nyit");
            System.out.println("\n Driver is connected Successfully");

            return conn;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
//
    public static void insertUser(String username, String password, String usertype){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");


        String insertIntoCustomerRegisterQuery = "INSERT INTO Registration("
                                                + "username, password,usertype)"
                                                + "VALUES(?,?,?);";
        PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
        pst.setString(1,username);
        pst.setString(2,password);
        pst.setString(3,usertype);
        pst.execute();
        System.out.println("User inserted");
        }
        catch(Exception e){
            System.out.println("user not inserted");
        }
    }

    public static void insertOrder(String uId, String oId, String itemId, int number, String cId){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");


        String insertIntoOrdersQuery = "INSERT INTO Orders("
                                                + "uId, oId, itemName, itemNumber, cId)"
                                                + "VALUES(?,?,?,?,?);";
        PreparedStatement pst = conn.prepareStatement(insertIntoOrdersQuery);
        pst.setString(1,uId);
        pst.setString(2,oId);
        pst.setString(3,itemId);
        pst.setInt(4,number);
        pst.setString(5,cId);
        pst.execute();
        System.out.println("Orders inserted");
        }
        catch(Exception e){
            System.out.println("orders not inserted");
        }    
    }
   
    public static Map<String, User> selectUser(){
        Map<String, User> users = new HashMap<String, User>();
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");
            String selectUserQuery = "SELECT * FROM registration;";
//            System.out.println("1");
            PreparedStatement pst = conn.prepareStatement(selectUserQuery);
//            System.out.println("2");
            ResultSet rs = pst.executeQuery();
//            System.out.println("3");
            System.out.println(rs);
            while(rs.next()){
//                int lvl =  Integer.parseInt(rs.getString("usertype"));
                User user =
                        new User(rs.getString("username"), rs.getString("password"),0);
                users.put(rs.getString(("username")),user);
            }
            System.out.println(users.values());
            System.out.println("user selected");
//            return users;
        }catch (Exception e){
            System.out.println("error");
        }
        return users;
    }

    public static List<Order> getOrder(String uId){
        // Map<String, User> users = new HashMap<String, User>();
        List<Order> orders = new ArrayList<Order>();
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");
            String selectOrderQuery = "SELECT * FROM Orders where uId =\"" + uId +"\" ;";
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            System.out.println("pst ok");
            ResultSet rs = pst.executeQuery();
            System.out.println("rs ok");
            while(rs.next()){
                Order order = new Order(rs.getString("uId"), rs.getString("oId"),
                                        rs.getString("itemName"),rs.getInt("itemNumber"),
                                        rs.getString("cId"));
                // users.put(rs.getString(("username")),user);
                orders.add(order);
            }
            // System.out.println(users.values());
            System.out.println("order selected");
//            return users;
        }catch (Exception e){
            System.out.println("error");
        }
        return orders;    
    }

    public static Map<String, Item> getItems(){
        Map<String, Item> items = new HashMap<String, Item>();
        try{
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");
            String getItemsQuery = "SELECT * FROM PRODUCTS";
            PreparedStatement pst = conn.prepareStatement(getItemsQuery);
            ResultSet rs = pst.executeQuery();
            List<String> accessories = new ArrayList<String>();
            String s = "";
            while(rs.next()){
                s = rs.getString("accessories");
                accessories = new ArrayList<String>(Arrays.asList(s.split(",")));
                Item item = new Item(rs.getString("itemID"), rs.getString("itemName"), 
                    rs.getDouble("price"), rs.getDouble("discount"), rs.getDouble("rebates"), 
                    rs.getInt("stocks"), accessories);
                items.put(rs.getString("itemID"), item);
            }
            // System.out.println(items.values());
            System.out.println("item got!");

        }catch (Exception e){
            System.out.println("didnt get items");
        }
        return items;
    }

    public static void ReadDataToDB(){
        Map<String, Item> items = new HashMap<String, Item>();
        items = SaxPaserDataStore.getItems("./ProductCatalog.xml");
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/SP",
                    "root", "nyit");
            String accessories = "";
            for (Item item: items.values()) {
                String insertIntoProductsQuery = "INSERT INTO Products("
                                                        + "itemID, itemName, price, discount, rebates, stocks, accessories)"
                                                        + "VALUES(?,?,?,?,?,?,?);";
                PreparedStatement pst = conn.prepareStatement(insertIntoProductsQuery);
                pst.setString(1,item.getItemId());
                pst.setString(2,item.getItemName());
                pst.setDouble(3,item.getPrice());
                pst.setDouble(4,item.getDiscount());
                pst.setDouble(5,item.getRebates());
                pst.setInt(6,item.getStock());
                accessories = String.join(",", item.getAccessories());
                pst.setString(7,accessories);
                pst.execute();            
            }            
                System.out.println("products inserted");
        }
        catch(Exception e){
            System.out.println("products not inserted");
        }
    }



    // public static void main(String args[]){
    //     // String username = "test1";
    //     // String password = "111";
    //     // String usertype = "customer";
    //     // insertUser(username,password,usertype);
    //     // ReadDataToDB();
    //     Map<String, Item> items = new HashMap<String, Item>();
    //     items = getItems();
    //     System.out.println(items);
    // }
}

