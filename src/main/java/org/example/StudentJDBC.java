package org.example;
import java.sql.*;
import java.util.*;

public class StudentJDBC {

    public Connection getConnection()throws SQLException{
        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String password = "shraddha#19";

        return DriverManager.getConnection(url,user,password);
    }

    public void createTable(){
        String sqlQuery = "CREATE TABLE IF NOT EXISTS studentInfo(rollNo INT PRIMARY KEY ,name VARCHAR(20),marks INT)";

        try(Connection connection = getConnection(); Statement statement = connection.createStatement()){
            statement.executeUpdate(sqlQuery);
            System.out.println("table successfully created");
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void insertData(Student student){
        String sqlQuery = "INSERT INTO studentInfo(rollNo,name,marks) VALUES(?,?,?)";

        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){

            preparedStatement.setInt(1,student.getRollNo());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3,student.marks);

            preparedStatement.executeUpdate();

            System.out.println("Data inserted successfully");
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void updateData(Student student){
        String sqlQuery = "UPDATE studentInfo SET name = ?, marks = ? WHERE rollNo = ?";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){

            preparedStatement.setString(1,student.getName());
            preparedStatement.setInt(2,student.getMarks());
            preparedStatement.setInt(3,student.getRollNo());

            preparedStatement.executeUpdate();

        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void deleteData(int rollNo){
        String sqlQuery = "DELETE FROM studentInfo WHERE rollNo = ?";

        try(Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){

            preparedStatement.setInt(1,rollNo);
            preparedStatement.executeUpdate();

        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void  readStudentInfo(){
        String sqlQuery = "SELECT * FROM studentInfo";
        //List<Student> studentList = new ArrayList<>();

        try(Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)
        ){
            while (resultSet.next()){
                int rollNo = resultSet.getInt("rollNo");
                String name = resultSet.getString("name");
                int marks = resultSet.getInt("marks");

                System.out.println("rollNo :" + rollNo + " name :" + name + " marks :" + marks);
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}
