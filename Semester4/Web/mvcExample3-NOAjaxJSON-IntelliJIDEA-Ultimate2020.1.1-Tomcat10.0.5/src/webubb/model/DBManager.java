package webubb.model;

import webubb.domain.Order;
import webubb.domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//POST - ADD / UPDATE / DELETE
//GET - LIST

public class DBManager {

    public List<Order> productsToBuy = new ArrayList<>();
    public Statement statement;

    public DBManager() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/arhivaproducts-orders", "root", "");
            statement = con.createStatement();
        } catch (Exception ex) {
            System.err.println("Connection error:" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public boolean add(Product product) {
        int i = 0;
        try {
            i = statement.executeUpdate("insert into product values(" + product.getId() + ", '" +
                    product.getName() + "','" + product.getDescription() + "')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (i > 0);
    }

    public boolean delete(int id) {
        int i = 0;
        try {
            i = statement.executeUpdate("delete from product where id=" + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (i > 0);
    }

    public boolean update(Product product) {
        int i = 0;
        try {
            i = statement.executeUpdate("update product set name='" + product.getName() + "', description='"
                    + product.getDescription() + "' where id=" + product.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return (i > 0);
    }

    public List<Product> list(String toStart) {
        List<Product> products = new ArrayList<>();
        ResultSet rs;
        try {
            rs = statement.executeQuery("select * from product");
            while (rs.next()) {
                //contains() if we want to check in the whole string
                if (rs.getString("description").startsWith(toStart)) {
                    products.add(new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description")
                    ));
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void buyProduct(Order order) {
        productsToBuy.add(order);
    }

    public boolean finalizeCommand() {
        int j = 0;
        for (int i = 0; i < productsToBuy.size(); i++) {
            Order order = productsToBuy.get(i);
            try {
                j = statement.executeUpdate("insert into porder values (" + order.getId() + " ,'" + order.getUsername() +
                        "', " + order.getProductId() + ", " + order.getQuantity() + ")");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        cancelCommand();
        return (j > 0);
    }

    public void cancelCommand() {
        productsToBuy.clear();
    }

    public int getMaxIdFromOrders() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT id from porder ORDER BY id DESC LIMIT 1");
            resultSet.next();
            return resultSet.getInt("id");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
