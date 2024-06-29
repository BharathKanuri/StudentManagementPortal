package com;

import java.util.List;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class StudentServlet extends HttpServlet{
    // Declaring StudentDAO Object
    private StudentDAO studentDAO;
    public void init(){
        String jdbcURL=getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername=getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword=getServletContext().getInitParameter("jdbcPassword");
        studentDAO=new StudentDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String action=request.getParameter("action");
        try{
            if(action==null)
                action="list";
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertStudent(request, response);
                    break;
                case "delete":
                    deleteStudent(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateStudent(request, response);
                    break;
                case "search":
                    searchStudent(request, response);
                    break;
                default:
                    listStudent(request, response);
                    break;
            }
        }
        catch(SQLException ex){
            throw new ServletException(ex);
        }
    }
    // Handler for Listing all Students
    private void listStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        List<Student>listStudent=studentDAO.listAllStudents();
        request.setAttribute("listStudent", listStudent);
        RequestDispatcher dispatcher=request.getRequestDispatcher("students.jsp");
        dispatcher.forward(request, response);
    }
    // Handler to Display New Form for Student Enrollment
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher=request.getRequestDispatcher("addStudent.jsp");
        dispatcher.forward(request, response);
    }
    // Handler to Display Edit Form for Student Details Updation
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException{
        String rollNo=request.getParameter("rollNo");
        Student existingStudent=studentDAO.searchStudentByRollNo(rollNo).get(0); // Assuming rollNo is unique
        RequestDispatcher dispatcher=request.getRequestDispatcher("editStudent.jsp");
        request.setAttribute("student", existingStudent);
        dispatcher.forward(request, response);
    }
    // Handler to Enroll New Student
    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException{
        String rollNo=request.getParameter("rollNo");
        String name=request.getParameter("name").toUpperCase();
        String contact=request.getParameter("contact");
        String email=request.getParameter("email");
        int year=Integer.parseInt(request.getParameter("year"));
        String branch=request.getParameter("branch");
        String section=request.getParameter("section");
        Student newStudent=new Student(rollNo, name, contact, email, year, branch, section);
        String[] errorMessages=new String[3];
        errorMessages=studentDAO.checkForDuplicates(newStudent,false);
        if(errorMessages[0]==null && errorMessages[1]==null && errorMessages[2]==null){
            boolean success=studentDAO.insertStudent(newStudent);
            if(success){
                request.setAttribute("message", "Student Enrolled Successfully");
                response.sendRedirect("StudentServlet?action=list");
            } 
            else{
                request.setAttribute("message", "Student Enrollment Unsuccessful\nTry again...");
                request.setAttribute("student", newStudent);
                RequestDispatcher dispatcher=request.getRequestDispatcher("addStudent.jsp");
                dispatcher.forward(request, response);
            }
        }
        else{
            request.setAttribute("errorMessages", errorMessages);
            request.setAttribute("student", newStudent);
            RequestDispatcher dispatcher=request.getRequestDispatcher("addStudent.jsp");
            dispatcher.forward(request, response);
        }
    }
    // Handler to Update existing Details of Student
    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        String rollNo=request.getParameter("rollNo");
        String name=request.getParameter("name").toUpperCase();
        String contact=request.getParameter("contact");
        String email=request.getParameter("email");
        int year=Integer.parseInt(request.getParameter("year"));
        String branch=request.getParameter("branch");
        String section=request.getParameter("section");
        Student student=new Student(rollNo, name, contact, email, year, branch, section);
        String[] errorMessages=new String[3];
        errorMessages=studentDAO.checkForDuplicates(student,true);
        if(errorMessages[0]==null && errorMessages[1]==null && errorMessages[2]==null){
            boolean success=studentDAO.updateStudent(student);
            if(success){
                request.setAttribute("message", "Student Details Updated Successfully");
                response.sendRedirect("StudentServlet?action=list");
            }
            else{
                request.setAttribute("message", "Student Details Updation Unsuccessful\nTry again...");
                request.setAttribute("student", student);
                RequestDispatcher dispatcher=request.getRequestDispatcher("editStudent.jsp");
                dispatcher.forward(request, response);
            }
        }
        else{
            request.setAttribute("errorMessages", errorMessages);
            request.setAttribute("student", student);
            RequestDispatcher dispatcher=request.getRequestDispatcher("editStudent.jsp");
            dispatcher.forward(request, response);
        }
    }
    // Handler to Delete Student Record based on Roll Number
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException{
        String rollNo=request.getParameter("rollNo");
        studentDAO.deleteStudent(rollNo);
        response.sendRedirect("StudentServlet?action=list");
    }
    // Handler to Search Student Record based on Roll Number
    private void searchStudent(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException{
            String rollNo=request.getParameter("rollNo");
            List<Student>listStudent=studentDAO.searchStudentByRollNo(rollNo);
            request.setAttribute("listStudent", listStudent);
            RequestDispatcher dispatcher=request.getRequestDispatcher("students.jsp");
            dispatcher.forward(request, response);
    }
    // Main Method
    public static void main(String[] args){

    }
}