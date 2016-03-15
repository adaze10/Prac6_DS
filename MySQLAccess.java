import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLAccess {
       
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  final private String host = "localhost";
  final private String user = "root";
  final private String passwd = "xequebo";
  final private String database = "prac6_db";
 
  public void connectToDB() throws Exception {
      try {
              // This will load the MySQL driver, each DB has its own driver
              Class.forName("com.mysql.jdbc.Driver");

              // Setup the connection with the DB
              connect = DriverManager.getConnection("jdbc:mysql://" + host + "/"
                              + database + "?" + "user=" + user + "&password=" + passwd);

      } catch (Exception e) {
              throw e;
      }
  }

  public void readClients() throws Exception {
      try {
              statement = connect.createStatement();
              resultSet = statement
                              .executeQuery("select * from " + database + ".clients");
              while (resultSet.next()) {
                      int client_id = resultSet.getInt("client_id");
                      String client_name = resultSet.getString("client_name");
                      String client_email = resultSet.getString("client_email");

                      System.out.println(String.format(
                                      "Client ID: %d name: %5s  email: %5s", client_id,
                                      client_name, client_email));
              }
      } catch (Exception e) {
              throw e;
      }
  }
  
  public void readSales() throws Exception {
      try {
              statement = connect.createStatement();
              resultSet = statement
                              .executeQuery("select * from " + database + ".sales");
              while (resultSet.next()) {
                      int sale_id = resultSet.getInt("sale_id");
                      String client_id = resultSet.getString("client_id");
                      int sale_value = resultSet.getInt("sale_value");

                      System.out.println(String.format(
                                      "Sale ID: %d name: %5s  email: %5s", sale_id,
                                      client_id, sale_value));
              }
      } catch (Exception e) {
              throw e;
      }
  }
  
  public void writeSales(int client_id, int sale_value) throws Exception {
      try {
              preparedStatement = connect.prepareStatement("INSERT INTO " + database +
            		  ".sales VALUES (default,?,?)");
              preparedStatement.setInt(1, client_id);
              preparedStatement.setInt(2, sale_value);
              preparedStatement.executeUpdate();
      } catch (Exception e) {
              throw e;
      }
  }
  
  // You need to close the resultSet
  public void close() {
          try {
                  if (resultSet != null) {
                          resultSet.close();
                  }

                  if (statement != null) {
                          statement.close();
                  }

                  if (connect != null) {
                          connect.close();
                  }
          } catch (Exception e) {

          }
  }


}

