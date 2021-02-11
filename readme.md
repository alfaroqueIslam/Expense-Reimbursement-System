# Project 1: Expense Reimbursement System(ERS)

## Description
The Expense Reimbursement System (ERS) manages the process of reimbursing employees for expenses incurred while on company time. All employees can log in and submit requests for reimbursement and view their past tickets and pending requests. Managers can then log in and view all reimbursement requests and past history for all employees. These managers are authorized to approve and deny requests for expense reimbursements.

## Technologies Used
- Java 15
- HTML
- CSS
- JavaScript
- Servlets
- JDBC
- SQL
- RDS
- Maven
- AJAX
- Tomcat
- Git

## Features
### List of Features
- Employees can login, logout, submit reimbursement requests, view pending and resolved requests.
- Managers can login and logout but can also view all pending requests and approve or deny those requests.

### Servlet classes:

ErsServlet- Processes GET requests for reimbursement request data.

LoginServlet- Processes POST requests to authenticate user credentials.

ModifyServlet- Processes POST requests to modify reimbursement request status.

RequestServlet- Processes POST request to create new reimbursement request.

UserServlet- Processes GET requests for user data.

### Service Classes:
RequestService- Accesses the RequestDaos for the servlet classes.

UserService- Accesses the UserDaos for the servlet classes.

### Dao Classes:
UserDaos- Accesses user data from the database.

RequestDaos- Accesses reimbursement request data from the database.

### Model Classes:
User- This class holds user data.

Request- This class is used to hold reimbursement request data.

### Frontend:

Login- login.html and login.js

Home page- landing.html and landing.js

### Database:

![alt text](project1_db.PNG "ERS Data")


