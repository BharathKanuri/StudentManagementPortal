<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.Student"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Student List</title>
        <link rel="stylesheet" href="./styles.css">
    </head>
    <body>
        <header>
            <h1>Student List</h1>
            <form action="StudentServlet" method="get">
                <input type="hidden" name="action" value="search">
                <input type="text" name="rollNo" placeholder="Search by Roll No" required minlength="10" >
                <button type="submit" style="width: 178px; font-size: 15px">Search</button>
            </form>
            <br/>
            <a href="StudentServlet?action=new">Enroll New Student</a>
        </header>
        <main>
            <div class="container" style="max-width: auto">
                <table>
                    <thead>
                        <tr>
                            <th>Roll No</th>
                            <th>Name</th>
                            <th>Contact</th>
                            <th>Email</th>
                            <th>Year</th>
                            <th>Branch</th>
                            <th>Section</th>
                            <th colspan="2">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            List<Student> listStudent = (List<Student>) request.getAttribute("listStudent");
                            if (listStudent != null && !listStudent.isEmpty()) {
                                for (Student student : listStudent) {
                        %>
                                <tr>
                                    <td><%= student.getRollNo() %></td>
                                    <td><%= student.getName() %></td>
                                    <td><%= student.getContact() %></td>
                                    <td><%= student.getEmail() %></td>
                                    <td><%= student.getYear() %></td>
                                    <td><%= student.getBranch() %></td>
                                    <td><%= student.getSection() %></td>
                                    <td><a href="StudentServlet?action=edit&rollNo=<%= student.getRollNo() %>" style="background-color: gold">Edit</a></td>
                                    <td><a href="StudentServlet?action=delete&rollNo=<%= student.getRollNo() %>" style="background-color: #f44336;color:white" onclick="return confirm('Are you sure you want to delete this student?')">Delete</a></td>
                                </tr>
                        <% 
                                }
                            } else {
                        %>
                                <tr>
                                    <td colspan="9">No Students Found</td>
                                </tr>
                        <% 
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </main>
    </body>
</html>