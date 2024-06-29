<%@ page language="java" contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Enroll New Student</title>
        <link rel="stylesheet" href="./styles.css">
        <script src="formValidator.js"></script>
    </head>
    <body>
        <header>
            <h1>Enroll New Student</h1>
        </header>
        <main>
            <div class="container" style="max-width: 800px">
                <form name="addStudentForm" onsubmit="return validateForm('addStudentForm')" action="StudentServlet?action=insert" method="post">
                    <div class="form-group">
                        <label for="rollNo">Roll No:</label>
                        <input type="text" id="rollNo" name="rollNo" placeholder="21071A1223, 21071A12G8,..." required>
                        <p id="addStudentForm-rollNoError" class="error">${errorMessages[0] == 'Roll No. already exists' ? errorMessages[0] : ''}</p>
                    </div>
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" id="name" name="name" placeholder="John Doe" required>
                        <p id="addStudentForm-nameError" class="error"></p>
                    </div>
                    <div class="form-group">
                        <label for="contact">Contact:</label>
                        <input type="text" id="contact" name="contact" placeholder="7842565155" required>
                        <p id="addStudentForm-contactError" class="error">${errorMessages[1] == 'Contact No. already exists' ? errorMessages[1] : ''}</p>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" placeholder="john-doe.2004@gmail.com" required>
                        <p id="addStudentForm-emailError" class="error">${errorMessages[2] == 'Email already exists' ? errorMessages[2] : ''}</p>
                    </div>
                    <div class="form-group">
                        <label for="year">Year:</label>
                        <select id="year" name="year" required>
                            <option value="" selected disabled>Select Year of Study</option>
                            <option value="1">I</option>
                            <option value="2">II</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="branch">Branch:</label>
                        <select id="branch" name="branch" required>
                            <option value="" selected disabled>Select Branch</option>
                            <option value="H&S">H & S</option>
                            <option value="CSE">CSE</option>
                            <option value="IT">IT</option>
                            <option value="CSBS">CSBS</option>
                            <option value="CS-AIML">CS-AIML</option>
                            <option value="CS-IoT">CS-IoT</option>
                            <option value="CS-CyS">CS-CyS</option>
                            <option value="CS-DS">CS-DS</option>
                            <option value="CS-AIDS">CS-AIDS</option>
                            <option value="ECE">ECE</option>
                            <option value="EEE">EEE</option>
                            <option value="EIE">EIE</option>
                            <option value="CE">CE</option>
                            <option value="ME">ME</option>
                            <option value="AE">AE</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="section">Section:</label>
                        <select id="section" name="section" required>
                            <option value="" selected disabled>Select Section</option>
                            <option value="A">A</option>
                            <option value="B">B</option>
                            <option value="C">C</option>
                        </select>
                    </div>
                    <button type="submit" style="width: 182px; font-size: 15px">Add Student</button>
                </form>
                <br/>
                <a href="StudentServlet?action=list">Back to Student List</a>
            </div>
        </main>
    </body>
</html>