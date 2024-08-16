package org.example;
import java.sql.*;

public class JDBC_Eg {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String password = "shraddha#19";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user,password);
            System.out.println("Connection is Successful to the database "+ url);
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
