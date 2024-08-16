package org.example;

public class Employee {
    int id;
    String name;
    int age;

    Employee(int id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    int getId(){
        return this.id;
    }

    String getName(){
        return this.name;
    }

    int getAge(){
        return  this.age;
    }

    public static void main(String[] args) {
        Employee obj = new Employee(1,"seema",22);
        JDBC_Eg2 objJDBC = new JDBC_Eg2();
        objJDBC.createTable();
        objJDBC.insertData(obj);
        obj.name = "hema";
        objJDBC.updateName(obj);

        obj = new Employee(2,"sham", 23);
        objJDBC.insertData(obj);
        objJDBC.insertData(new Employee(3,"Rama", 22));
        objJDBC.readData();
        //objJDBC.deleteData(obj.id);
    }
}
