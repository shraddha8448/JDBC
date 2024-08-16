package org.example;

import  java.io.*;

public class Student {
    int rollNo;
    String name;
    int marks;

    Student(int rollNo, String name, int marks){
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    int getRollNo(){
        return this.rollNo;
    }

    String getName(){
        return  this.name;
    }

    int getMarks(){
        return this.marks;
    }

    static  void  addData(StudentJDBC jdbcObj)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter the rollNo of student");
        int rollNo = Integer.parseInt(br.readLine());

        System.out.println("Enter the name of the student");
        String name = br.readLine();

        System.out.println("Enter the total marks of student");
        int marks = Integer.parseInt(br.readLine());

        jdbcObj.insertData(new Student(rollNo,name,marks));
    }

    static  void updateStudent(StudentJDBC jdbcObj)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the student rollNo for update info");
        int rollNo = Integer.parseInt(br.readLine());

        System.out.println("Enter the update name and marks of the student ");
        String name = br.readLine();
        int marks = Integer.parseInt(br.readLine());
        jdbcObj.updateData(new Student(rollNo,name,marks));

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StudentJDBC jdbcObj = new StudentJDBC();
        jdbcObj.createTable();
        char ch;
        do{
            System.out.println("1.createTable");
            System.out.println("2.insertData");
            System.out.println("3.updateData");
            System.out.println("4.deleteData");
            System.out.println("5.read Student Info");

            System.out.println("Enter the number");
            int num = Integer.parseInt(br.readLine());
            switch (num){
                case 1: jdbcObj.createTable();
                        break;

                case 2: addData(jdbcObj);
                        break;

                case 3:
                        updateStudent(jdbcObj);
                        break;

                case 4: {
                            System.out.println("Enter the rollNo of student for Delete the data entry");
                            int rollNo = Integer.parseInt(br.readLine());
                            jdbcObj.deleteData(rollNo);
                        }
                        break;

                case 5:
                        jdbcObj.readStudentInfo();
                        break;

                default:
                        System.out.println("Invalid input");
            }

            System.out.println("Do you want to continue...?");
            ch = br.readLine().charAt(0);
        }while (ch=='y' || ch=='Y');
    }
}
