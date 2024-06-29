<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Student Details</title>
        <link rel="stylesheet" href="./styles.css">
        <script src="formValidator.js"></script>
    </head>
    <body>
        <header>
            <h1>Edit Student Details</h1>
        </header>
        <main>
            <div class="container" style="max-width: 800px">
                <form name="editStudentForm" onsubmit="return validateForm('editStudentForm')" action="StudentServlet?action=update" method="post">
                    <input type="hidden" id="rollNo" name="rollNo" value="${student.rollNo}">
                    <p id="editStudentForm-rollNoError" class="error"></p>
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" id="name" name="name" value="${student.name}" required>
                        <p id="editStudentForm-nameError" class="error"></p>
                    </div>
                    <div class="form-group">
                        <label for="contact">Contact:</label>
                        <input type="text" id="contact" name="contact" value="${student.contact}" required>
                        <p id="editStudentForm-contactError" class="error">${errorMessages[1] == 'Contact No. already exists' ? errorMessages[1] : ''}</p>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" value="${student.email}" required>
                        <p id="editStudentForm-emailError" class="error">${errorMessages[2] == 'Email already exists' ? errorMessages[2] : ''}</p>
                    </div>
                    <div class="form-group">
                        <label for="year">Year:</label>
                        <select id="year" name="year" required>
                            <option value="1" ${student.year == 1 ? 'selected="selected"' : ''}>I</option>
                            <option value="2" ${student.year == 2 ? 'selected="selected"' : ''}>II</option>
                            <option value="3" ${student.year == 3 ? 'selected="selected"' : ''}>III</option>
                            <option value="4" ${student.year == 4 ? 'selected="selected"' : ''}>IV</option>
                        </select>
                    </div>
                    <input type="hidden" id="branch" name="branch" value="${student.branch}">
                    <input type="hidden" id="section" name="section" value="${student.section}">
                    <button type="submit" style="width: 182px; font-size: 15px">Update Student</button>
                </form>
                <br/>
                <a href="StudentServlet?action=list">Back to Student List</a>
            </div>
        </main>
    </body>
</html>