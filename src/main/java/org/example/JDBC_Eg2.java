package org.example;
import java.sql.*;
import java.util.*;

public class JDBC_Eg2 {

    // database connection
    public Connection getConnection()throws SQLException{
        String url = "jdbc:mysql://localhost:3306/company";
        String user = "root";
        String password = "shraddha#19";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return DriverManager.getConnection(url,user,password);
    }

    // table create
    public void createTable(){
        String sqlQuery = "CREATE TABLE IF NOT EXISTS employee(id INT PRIMARY KEY, name VARCHAR(50), age INT)";
        try(Connection connection = getConnection();Statement statement = connection.createStatement()){
            statement.executeUpdate(sqlQuery);
            System.out.println("Table created successfuly");
        }catch (SQLException e){
            System.out.println("Table not created ");
            e.printStackTrace();
        }
    }

    // insert data in database
    public void insertData(Employee employee){
        String sqlQuery = "insert into employee(id,name,age) values(?,?,?)";

        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){

            preparedStatement.setInt(1,employee.getId());
            preparedStatement.setString(2,employee.getName());
            preparedStatement.setInt(3,employee.getAge());

            preparedStatement.executeUpdate();
            System.out.println("data inserted successfully");
        }catch (SQLException ex){
            System.out.println("data not inserted");
            ex.printStackTrace();
        }
    }

    // Update Name in database
    public void updateName(Employee employee) {
        String sqlQuery = "UPDATE employee SET name = ? WHERE id = ?";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getId());

            preparedStatement.executeUpdate(); // Use executeUpdate for updating data
       } catch (SQLException ex) {
            System.out.println("Data not updated...");
            ex.printStackTrace();
        }
    }

    // Updating age in Database
    public void Age(Employee employee) {
        String sqlQuery = "UPDATE employee SET age = ? WHERE id = ?";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, employee.getAge());
            preparedStatement.setInt(2, employee.getId());

            preparedStatement.executeUpdate(); // Use executeUpdate for updating data
        } catch (SQLException ex) {
            System.out.println("Data not updated");
            ex.printStackTrace();
        }
    }

    // for deleting the entry
    public void deleteData(int id) {
        String sqlQuery = "DELETE FROM employee WHERE id = ?";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate(); // Use executeUpdate for deleting data

        } catch (SQLException ex) {
            System.out.println("Data not deleted successfully");
            ex.printStackTrace();
        }
    }

    public void readData(){
        List<Employee> employeeList = new ArrayList<Employee>();
        String sqlQuery = "SELECT * FROM employee";

        try(Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)){

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getNString("name");
                int age = resultSet.getInt("age");

                Employee employee = new Employee(id,name,age);
                employeeList.add(employee);
                System.out.println("id :" +id + " name :" + name + " age :" + age);
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }

    }

}