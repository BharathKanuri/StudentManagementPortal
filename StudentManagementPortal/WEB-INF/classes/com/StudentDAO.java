package com;

import java.sql.*;
import java.util.*;

public class StudentDAO{
    // Declaring Database Variables
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
    // Constructor
    public StudentDAO(String jdbcURL, String jdbcUsername, String jdbcPassword){
        this.jdbcURL=jdbcURL;
        this.jdbcUsername=jdbcUsername;
        this.jdbcPassword=jdbcPassword;
    }
    // Loading the Driver Class
    protected void connect() throws SQLException{
        if(jdbcConnection==null || jdbcConnection.isClosed()){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            } 
            catch (ClassNotFoundException e){
                throw new SQLException(e);
            }
            jdbcConnection=DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
    // Closing the Connection with Database 
    protected void disconnect() throws SQLException{
        if(jdbcConnection!=null && !jdbcConnection.isClosed())
            jdbcConnection.close();
    }
    // Inserting New Student Record
    public boolean insertStudent(Student student) throws SQLException{
        String sql="INSERT INTO Students (RollNo, Name, Contact, Email, Year, Branch, Section) VALUES (?, ?, ?, ?, ?, ?, ?)";
        connect();
        PreparedStatement statement=jdbcConnection.prepareStatement(sql);
        statement.setString(1, student.getRollNo());
        statement.setString(2, student.getName());
        statement.setString(3, student.getContact());
        statement.setString(4, student.getEmail());
        statement.setInt(5, student.getYear());
        statement.setString(6, student.getBranch());
        statement.setString(7, student.getSection());
        boolean rowInserted=statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    // Listing all Student Records
    public List<Student>listAllStudents() throws SQLException{
        List<Student>listStudent=new ArrayList<>();
        String sql="SELECT * FROM Students";
        connect();
        Statement statement=jdbcConnection.createStatement();
        ResultSet resultSet=statement.executeQuery(sql);
        while(resultSet.next()){
            String rollNo=resultSet.getString("RollNo");
            String name=resultSet.getString("Name");
            String contact=resultSet.getString("Contact");
            String email=resultSet.getString("Email");
            int year=resultSet.getInt("Year");
            String branch=resultSet.getString("Branch");
            String section=resultSet.getString("Section");
            Student student=new Student(rollNo, name, contact, email, year, branch, section);
            listStudent.add(student);
        }
        resultSet.close();
        statement.close();
        disconnect();
        return listStudent;
    }
    // Deleting Student Record
    public boolean deleteStudent(String rollNo) throws SQLException{
        String sql="DELETE FROM Students where RollNo = ?";
        connect();
        PreparedStatement statement=jdbcConnection.prepareStatement(sql);
        statement.setString(1, rollNo);
        boolean rowDeleted=statement.executeUpdate()>0;
        statement.close();
        disconnect();
        return rowDeleted;
    }
    // Updating the Details of Student
    public boolean updateStudent(Student student) throws SQLException{
        String sql="UPDATE Students SET Name = ?, Contact = ?, Email = ?, Year = ?, Branch = ?, Section = ? WHERE RollNo = ?";
        connect();
        PreparedStatement statement=jdbcConnection.prepareStatement(sql);
        statement.setString(1, student.getName());
        statement.setString(2, student.getContact());
        statement.setString(3, student.getEmail());
        statement.setInt(4, student.getYear());
        statement.setString(5, student.getBranch());
        statement.setString(6, student.getSection());
        statement.setString(7, student.getRollNo());
        boolean rowUpdated=statement.executeUpdate()>0;
        statement.close();
        disconnect();
        return rowUpdated;
    }
    // Searching the Student based on Roll Number
    public List<Student>searchStudentByRollNo(String rollNo) throws SQLException{
        List<Student>listStudent=new ArrayList<>();
        String sql="SELECT * FROM Students WHERE RollNo = ?";
        connect();
        PreparedStatement statement=jdbcConnection.prepareStatement(sql);
        statement.setString(1, rollNo);
        ResultSet resultSet=statement.executeQuery();
        while(resultSet.next()){
            String name=resultSet.getString("Name");
            String contact=resultSet.getString("Contact");
            String email=resultSet.getString("Email");
            int year=resultSet.getInt("Year");
            String branch=resultSet.getString("Branch");
            String section=resultSet.getString("Section");
            Student student=new Student(rollNo, name, contact, email, year, branch, section);
            listStudent.add(student);
        }
        resultSet.close();
        statement.close();
        disconnect();
        return listStudent;
    }
    // Preventing the User from entering Duplicate Roll Number, Contact, Email
    public String[] checkForDuplicates(Student student, boolean isUpdate) throws SQLException{
        String sql;
        if(isUpdate)
            sql="SELECT RollNo, Contact, Email FROM Students WHERE (Contact = ? OR Email = ?) AND RollNo <> ?";
        else
            sql="SELECT RollNo, Contact, Email FROM Students WHERE RollNo = ? OR Contact = ? OR Email = ?";
        connect();
        PreparedStatement statement=jdbcConnection.prepareStatement(sql);
        if(isUpdate){
            statement.setString(1, student.getContact());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getRollNo());
        }
        else{
            statement.setString(1, student.getRollNo());
            statement.setString(2, student.getContact());
            statement.setString(3, student.getEmail());
        }
        ResultSet resultSet=statement.executeQuery();
        String[] errorMessages=new String[3];
        if(resultSet.next()){
            String existingRollNo=resultSet.getString("RollNo");
            String existingContact=resultSet.getString("Contact");
            String existingEmail=resultSet.getString("Email");
            if(existingRollNo.equals(student.getRollNo()))
                errorMessages[0]="Roll No. already exists";
            if(existingContact.equals(student.getContact()))
                errorMessages[1]="Contact No. already exists";
            if(existingEmail.equals(student.getEmail()))
                errorMessages[2]="Email already exists";
        }
        resultSet.close();
        statement.close();
        disconnect();
        return errorMessages;
    }
    // Main Method
    public static void main(String[] args){

    }
}